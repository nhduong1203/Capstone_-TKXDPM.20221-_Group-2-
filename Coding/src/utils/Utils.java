package utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author nhom2 Contains helper functions
 */
public class Utils {

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

	public static String startStop;

	public static long timeStop = 0;

	public static void stopRent(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		startStop = formatter.format(date);
	}

	public static void continuteRent(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		String endStop = formatter.format(date);

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = formatter.parse(startStop);
			d2 = formatter.parse(endStop);
		}catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = d2.getTime() - d1.getTime();
		timeStop += TimeUnit.MILLISECONDS.toMinutes(diff);
	}

}