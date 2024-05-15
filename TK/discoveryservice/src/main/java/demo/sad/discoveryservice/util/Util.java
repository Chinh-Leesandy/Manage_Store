package demo.sad.discoveryservice.util;

public class Util {
    public static String createIdOrElse(String id) {
        return id != null && !id.isEmpty() ? id : (java.util.UUID.randomUUID()) + "-" + System.currentTimeMillis();
    }
}
