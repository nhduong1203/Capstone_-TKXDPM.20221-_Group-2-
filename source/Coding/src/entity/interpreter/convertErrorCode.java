package entity.interpreter;

public class convertErrorCode implements interpreterInterface{
    @Override
    public String convertMessage(String code) {
        switch (code){
            case "00":
                return "Giao dịch hợp lệ";
            case "01":
                return "Thẻ không hợp lệ";
            case "02":
                return "Thẻ không đủ số dư";
            case "03":
                return "Internal Server Error";
            case "04":
                return "Giao dịch bị nghi ngờ gian lận";
            case "05":
                return "Không đủ thông tin giao dịch";
            case "06":
                return "Thiếu thông tin version";
            case "07":
                return "Amount không hợp lệ";
            default:
                return "404 Error";
        }
    }
}
