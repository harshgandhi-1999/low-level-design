package medium.digitalwalletsercicelld;

import java.util.UUID;

public class Utility {

    public static String generateUniqueId(){
        return UUID.randomUUID().toString();
    }
}
