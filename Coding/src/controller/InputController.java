package controller;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * This {@code InputController} class convert barcode to bikeId
 * @author nhom2
 */
public class InputController {
    /**
     *
     * @return tra ve bikeId
     */
    public static int Convert(String message){
        int length = message.length();
        String tag = new String();
        String id = new String();
        for(int i=0;i<length;i++){
            if(Character.isDigit(message.charAt(i))){
                tag = message.substring(0, i);
                id = message.substring(i, length);
            }
        }
        if(tag.equals("ECO")){
            System.out.println(id);
            return Integer.parseInt(id);
        }
        System.out.println("-1");
        return -1;
    }
}
