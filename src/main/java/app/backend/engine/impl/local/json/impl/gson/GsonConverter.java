package app.backend.engine.impl.local.json.impl.gson;

import app.backend.engine.impl.local.json.JsonConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class GsonConverter<T> implements JsonConverter<T> {
    protected Gson gson;

    @Override
    public String createJson(T object) {
        return gson.toJson(object);
    }
}
