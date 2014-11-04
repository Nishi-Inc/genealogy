package com.nishionline.genealogy.helper;

import com.google.gson.*;
import com.nishionline.genealogy.model.MongoObjectId;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.bson.types.ObjectId;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * @author Alok
 * @since 03-11-2014
 */
public class GsonUtils {
    public static Gson gson;
    public static JSONParser jsonParser;

    public static void init() {
        GsonUtils.gson = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC);

        gsonBuilder.registerTypeAdapter(Class.class, new ClassSerializerDeserializer());
        gsonBuilder.registerTypeAdapter(Client.class, new ClientDeserializer());
        gsonBuilder.registerTypeAdapter(ObjectId.class, new MongoObjectIdSerializer());

        GsonUtils.gson = gsonBuilder.create();
        GsonUtils.jsonParser = new JSONParser();
    }

    private static class ClassSerializerDeserializer implements JsonSerializer<Class>, JsonDeserializer<Class> {

        @Override
        public JsonElement serialize(Class aClass, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(aClass.getCanonicalName());
        }

        @Override
        public Class deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                return Class.forName(jsonElement.getAsString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class ClientDeserializer implements JsonDeserializer<Client> {

        @Override
        public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject clientObject = json.getAsJsonObject();
            Client client = new Client(clientObject.get("clientId").getAsString(), clientObject.get("clientSecret").getAsString(), clientObject.get("isConfidential").getAsBoolean());
            client.setApplicationName(clientObject.get("applicationName").getAsString());
            return client;
        }
    }

    private static class MongoObjectIdSerializer implements JsonSerializer<MongoObjectId> {

        @Override
        public JsonElement serialize(MongoObjectId src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("$oid", new JsonPrimitive(src.toString()));
            return jsonObject;
        }
    }
}
