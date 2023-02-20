package app.lang;

import java.util.HashMap;

public class LangHashMap implements UiLangMap {
    private HashMap<String, String> hashMap;

    //TODO add loading
    public LangHashMap() {
        hashMap = new HashMap<>();
    }

    @Override
    public String getText(String text) {
        return hashMap.get(text);
    }
}
