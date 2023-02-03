package controller;

/**
 * This {@code InputController} class convert bikeId to barcode
 * @author nhom2
 */
public class OutputController {
    /**
     *
     * @return tra ve barcode
     */
    public static String Convert(String message){
        String tag = "ECO";
        return tag.concat(message);
    }
}
