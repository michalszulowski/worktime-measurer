package app.backend.engine.impl.local.json;

public interface JsonConverter <T> {
    String createJson(T object);
    T parseJson(String json);
}
