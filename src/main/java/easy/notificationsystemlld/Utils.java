package easy.notificationsystemlld;

import java.util.UUID;

public class Utils {

    public static String generateUniqueId(){
        return UUID.randomUUID().toString();
    }
}
