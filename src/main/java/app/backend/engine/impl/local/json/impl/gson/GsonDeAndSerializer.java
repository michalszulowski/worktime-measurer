package app.backend.engine.impl.local.json.impl.gson;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class GsonDeAndSerializer<T> implements JsonSerializer<T>, JsonDeserializer<T> {
}
