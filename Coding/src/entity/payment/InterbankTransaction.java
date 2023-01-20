package entity.payment;

/**
 * This {@code InterbankTransaction} class represent Interbank transaction entity
 * in our ECO BIKE Software.
 *
 * @author nhom2
 *
 */
public class InterbankTransaction {
    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;
    private String command;
    private String transactionContent;
    private double amount;
    private String createdAt;  //format: yyyy-mm-đd hour:minute:second

    public InterbankTransaction(){
    }

    public InterbankTransaction(String _cardCode, String _owner, String _cvvCode, String _dateExpired, String _command, String _content, double _amount, String _createdAt){
        this.cardCode = _cardCode;
        this.owner = _owner;
        this.cvvCode = _cvvCode;
        this.dateExpired = _dateExpired;
        this.command = _command;
        this.transactionContent = _content;
        this.amount = _amount;
        this.createdAt = _createdAt;
    }

    public double getAmount() {
        return amount;
    }

    public String getCardCode() {return cardCode;}

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwner() {
        return owner;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public String getCommand() {
        return command;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
