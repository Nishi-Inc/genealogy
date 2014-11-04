package com.nishionline.genealogy.helper;

import com.google.gson.JsonObject;
import com.nishionline.genealogy.model.MongoObjectId;
import com.nishionline.genealogy.model.PersistentObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author Alok
 * @since 26-07-2014
 */
public final class GenealogyUtils {

    public static Set<Class<PersistentObject>> findAllPersistentObjects() {
        return GenealogyUtils.findAllSubclasses(PersistentObject.class);
    }

    @SuppressWarnings(GenealogyConstants.UNCHECKED)
    public static <T> Set<Class<T>> findAllSubclasses(Class<T> clazz) {
        Set<Class<T>> subClasses = new HashSet<>();
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(clazz));

        // scan in in.co.innosols
        Set<BeanDefinition> components = provider.findCandidateComponents("com/nishionline/genealogy");
        for (BeanDefinition component : components) {
            try {
                Class cls = Class.forName(component.getBeanClassName());
                subClasses.add(cls);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return subClasses;
    }

    /**
     * Generate a random code<br/>
     * It's a bit expensive method
     *
     * @return String Alpha-numeric code
     */
    public static String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32) + System.currentTimeMillis();
    }

    public static int getLesser(int number1, int number2) {
        return number1<=number2 ? number1 : number2;
    }

    public static int getGreater(int number1, int number2) {
        return number1>=number2 ? number1 : number2;
    }

    public static <T> T getObjectFromParamsUsingJson(MultivaluedMap<String,String> params, Class<T> clazz) {
        JsonObject paramsJson = new JsonObject();
        for(String key : params.keySet()) {
            paramsJson.addProperty(key, params.getFirst(key));
        }
        return GsonUtils.gson.fromJson(paramsJson, clazz);
    }

    @SuppressWarnings(GenealogyConstants.UNCHECKED)
    public static <T> T getObjectFromParams(MultivaluedMap<String,String> params, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            for(String key : params.keySet()) {
                Field field;
                boolean compositeField = false;
                if(key.contains(GenealogyConstants.DOT)) {
                    field = GenealogyUtils.getField(key.split(GenealogyConstants.DOT)[0], clazz);
                    compositeField = true;
                } else {
                    field = GenealogyUtils.getField(key, clazz);
                }
                field.setAccessible(true);

                if(compositeField) {
                    field.set(t, GenealogyUtils.getObjectFromParams(GenealogyUtils.extractParams(key.split(GenealogyConstants.DOT)[0], params), field.getType()));
                } else {
                    try {
                        field.set(t, params.getFirst(key));
                    } catch (IllegalArgumentException e) {
                        if(Collection.class.isAssignableFrom(field.getType())) {
                            String[] values = params.getFirst(key).split(GenealogyConstants.COMMA);
//                            Collection collection = new ArrayList();
                            Collection collection;
                            Class<? extends Collection> collectionClass = (Class<? extends Collection>) field.getType();
                            if(collectionClass.isInterface()) {
                                collection = new ArrayList<String>();
                            } else {
                                collection = (Collection) field.getType().newInstance();
                            }
                            for(String value : values) {
                                collection.add(value.trim());
                            }
                            field.set(t, collection);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> Field getField(String fieldName, Class<T> clazz) {
        if(clazz == Object.class) {
            return null;
        }

        Field field;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = getField(fieldName, clazz.getSuperclass());
        }

        return field;
    }

    public static MultivaluedMap<String, String> extractParams(String startsWith, MultivaluedMap<String, String> params) {
        MultivaluedMap<String, String> extractedParams = new MultivaluedHashMap<>();
        for(Map.Entry<String, List<String>> entry : params.entrySet()) {
            if(entry.getKey().startsWith(startsWith)) {
                extractedParams.put(entry.getKey(), entry.getValue());
            }
        }
        return extractedParams;
    }

    public static String encryptPassword(String password, MongoObjectId userId) {
        try {
            MessageDigest md = MessageDigest.getInstance(GenealogyConstants.MD5);
            byte[] salt = GenealogyUtils.getSalt(userId).getBytes();
            byte[] passwordBytes = password.getBytes();
            byte[] unencryptedBytes = new byte[salt.length + passwordBytes.length];
            System.arraycopy(salt, 0, unencryptedBytes, 0, salt.length);
            System.arraycopy(passwordBytes, 0, unencryptedBytes, salt.length - 1, passwordBytes.length);
//            System.arraycopy(salt, 0, unencryptedBytes, salt.length+passwordBytes.length-2, salt.length);
            md.update(unencryptedBytes);
            byte[] encryptedBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : encryptedBytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getSalt(MongoObjectId userId) {
        String salt = null;
        try {
            MessageDigest md = MessageDigest.getInstance(GenealogyConstants.MD5);
            md.update(userId.toString().getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            salt = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }

    //    === COLLECTION HELPERS ===
    public static class SingleValueHashMap<K, V> extends HashMap<K, V> {
        public SingleValueHashMap(K key, V value) {
            super();
            super.put(key, value);
        }

        @Override
        public int size() {
            return super.size() < 2 ? super.size() : 1;
        }

        @Override
        public V put(K key, V value) {
            if(this.size() <1) {
                return super.put(key, value);
            }
            return null;
        }

    }

    public static class SingleValueHashSet<T> extends HashSet<T> {
        public SingleValueHashSet(T t) {
            super();
            super.add(t);
        }

        @Override
        public int size() {
            return super.size() < 2 ? super.size() : 1;
        }

        @Override
        public boolean add(T t) {
            return this.size() <1 && super.add(t);
        }

    }

    public static class SingleValueArrayList<T> extends ArrayList<T> {
        public SingleValueArrayList(T t) {
            super();
            super.add(t);
        }

        @Override
        public int size() {
            return super.size() < 2 ? super.size() : 1;
        }

        @Override
        public boolean add(T t) {
            return this.size() <1 && super.add(t);
        }

    }

    public static void main(String[] args) {
        final String url = "http://openexchangerates.org/api/currencies.json?app_id=aaff8b17195f4ea7b8f0bec0c598ad3c";
//        InputStream input = null;
//        try {
//            input = new URL(url).openStream();
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        try {
//            Reader reader = new InputStreamReader(input, GenealogyConstants.UTF_8);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        String json = null;

        try {
            json = Jsoup.connect(url).ignoreContentType(true).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject currencies = (JSONObject) new JSONParser().parse(json);
            for(Object currencyCode : currencies.keySet()) {
                String displayName = (String) currencies.get(currencyCode);
                StringBuilder output = new StringBuilder(displayName.replaceAll(GenealogyConstants.SPACE, GenealogyConstants.UNDERSCORE).toUpperCase());
                output.append(GenealogyConstants.LEFT_BRACKET)
                        .append(GenealogyConstants.DOUBLE_QUOTES)
                        .append(displayName)
                        .append(GenealogyConstants.DOUBLE_QUOTES)
                        .append(GenealogyConstants.COMMA).append(GenealogyConstants.SPACE)
                        .append(GenealogyConstants.NULL)
                        .append(GenealogyConstants.COMMA).append(GenealogyConstants.SPACE).append(GenealogyConstants.DOUBLE_QUOTES)
                        .append(currencyCode).append(GenealogyConstants.DOUBLE_QUOTES)
                        .append(GenealogyConstants.COMMA).append(GenealogyConstants.SPACE)
                        .append(false)
                        .append(GenealogyConstants.RIGHT_BRACKET)
                        .append(GenealogyConstants.COMMA);
                System.out.println(output.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
