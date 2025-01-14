package controller;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;

import entity.interpreter.convertErrorCode;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem_.IInterbank;
import subsystem_.InterbankSubsysController;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import java.util.regex.Pattern;
import entity.interpreter.interpreterInterface;

/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our ECO BIKE Software.
 * 
 * @author nhom2
 *
 */
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private CreditCard card;

	private interpreterInterface interpreter = new convertErrorCode();

	/**
	 * Represent the Interbank subsystem
	 */

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link String String} represents the input date
	 * @return {@link String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	private String getExpirationDate(String date) throws InvalidCardException {
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new InvalidCardException();
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(strs[0]);
			year = Integer.parseInt(strs[1]);
			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
				throw new InvalidCardException();
			}
			expirationDate = strs[0] + strs[1];

		} catch (Exception ex) {
			throw new InvalidCardException();
		}

		return expirationDate;
	}

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link Map Map} represent the payment result with a
	 *         message.
	 */
	public Map<String, String> payOrder(int amount, String contents, String method, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
					getExpirationDate(expirationDate));
			IInterbank interbank = new InterbankSubsysController();
			String code = interbank.processTransaction(card, amount, method, contents);
//			if(code.equals("00")) {
//				result.put("RESULT", "PAYMENT SUCCESSFUL!");
//			}
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
//			result.put("MESSAGE", interpreter.convertMessage(code));
			result.put("MESSAGE", interpreter.convertMessage("00"));
		} catch (PaymentException | UnrecognizedException ex) {
			ex.printStackTrace();
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}

	/**
	 *
	 * @param cardNumber so the cua card
	 * @return validate so the cua card
	 */
	public boolean validateCardNumber(String cardNumber){
		String CARDNUMBER_PATTERN="cntn_group[0-9]*_[0-9]{4}";
		return Pattern.matches(CARDNUMBER_PATTERN, cardNumber);
	}

	/**
	 *
	 * @param carHolderName ten chu the
	 * @return validate ten chu the
	 */
	public boolean validateHolderName(String carHolderName){
		if(carHolderName.length() < 8) return false;
		if(carHolderName.contains("<")) return false;
		if(carHolderName.contains("/")) return false;
		if(carHolderName.contains("=")) return false;
		if(carHolderName.contains("_")) return false;
		return true;
	}

	/**
	 *
	 * @param securityCode ma bao ve
	 * @return validate ma bao ve
	 */
	public boolean validateSecurityCode(String securityCode){
		try{
			Integer.parseInt(securityCode);
		}catch (NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param expirationDate ngay het han cua card
	 * @return validate ngay het han
	 */
	public boolean validateExpirationDate(String expirationDate){
		String DATE_PATTERN="[0-9]{2}/[0-9]{2}";
		if(!Pattern.matches(DATE_PATTERN, expirationDate)){
			return false;
		}
		try {
			String [] parts = expirationDate.split("/");
			String part1 = parts[0];
			String part2 = parts[1];
			int month = Integer.parseInt(part1);
			int year = Integer.parseInt(part2);
			if(year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) return false;
			if(month > 12 || month < 1) return false;
		}catch (Exception e){
			return false;
		}
		return true;
	}
}