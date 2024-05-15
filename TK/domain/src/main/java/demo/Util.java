package demo;

import java.util.UUID;

public class Util {

    public static String createIdOrElse(String id) {
        return (id != null && !id.isEmpty()) ? id : createId();
    }

    public static String createId() {
        return UUID.randomUUID() + "-" + System.currentTimeMillis();
    }
}
