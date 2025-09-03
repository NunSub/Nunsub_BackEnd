package dmu.noonsub_backend.global.util;

import java.util.Base64;

public class Base64DecoderUtil {
    public static String decodeBase64(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }
}
