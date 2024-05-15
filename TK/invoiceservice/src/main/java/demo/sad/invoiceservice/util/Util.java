package demo.sad.invoiceservice.util;

public class Util {
    public static String createIdOrElse(String id) {
        return id != null && !id.isEmpty() ? id : createId();
    }

    public static String createId() {
        return (java.util.UUID.randomUUID()) + "-" + System.currentTimeMillis();
    }
}
