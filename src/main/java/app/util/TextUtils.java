package app.util;

public class TextUtils {
    public static String generateCharSeq(char c, int n) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sBuilder.append(c);
        }
        return sBuilder.toString();
    }
}
