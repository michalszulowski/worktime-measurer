package app.util;

public class TextUtils {
    public static String generateCharSeq(char ofChar, int length) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sBuilder.append(ofChar);
        }
        return sBuilder.toString();
    }
}
