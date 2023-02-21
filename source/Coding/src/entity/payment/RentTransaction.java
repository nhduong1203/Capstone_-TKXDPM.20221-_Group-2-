package entity.payment;

import controller.OutputController;
import entity.bike.Bike;
import entity.db.AIMSDB;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * This {@code RentTransaction} class represent Rent Transaction entity
 * in our ECO BIKE Software.
 *
 * @author nhom2
 *
 */
public class RentTransaction {
    public static Logger LOGGER = Utils.getLogger(RentTransaction.class.getName());

    private int rentalCode;

    private String rentTime;

    private int depositCost;

    private String returnTime;

    private int bikeCode;

    private String barcode;

    private int rentCost;

    public RentTransaction() throws SQLException {
        setRentalCode();
    }

    /**
     * phuong thuc khoi tao rent transaction
     * @param rentalCode ma thue
     * @param rentTime thoi gian thue
     * @param depositCost phi dat coc
     * @param returnTime thoi gian tra
     * @param bikeCode ma xe thue
     * @param rentCost tien thue xe
     */
    public RentTransaction(int rentalCode, String rentTime, int depositCost, String returnTime, int bikeCode, int rentCost){
        this.rentalCode = rentalCode;
        this.rentTime = rentTime;
        this.depositCost = depositCost;
        this.returnTime = returnTime;
        this.bikeCode = bikeCode;
        this.rentCost = rentCost;
        this.barcode = OutputController.Convert(String.valueOf(bikeCode));
    }

    /**
     *
     * @return giao dich thue xe neu thanh cong
     * @throws SQLException
     */
    public boolean startRent() throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        setRentTime(formatter.format(date));
        return createRentTransaction();
    }

    /**
     * phuong thuc tao moi mot giao dich thue trong database
     * @return thong bao thanh cong hoac that bai khi tao moi
     * @throws SQLException
     */
    private boolean createRentTransaction() throws SQLException {
        String sql = String.format("INSERT INTO renttransaction (rentalCode, rentCardCode, rentTime, bikeCode, depositeCost) VALUES (%d, '%s','%s',%d,%d)",this.rentalCode,"TTDuong", this.rentTime, this.bikeCode, this.depositCost);
        LOGGER.info(sql);
        Statement stm = AIMSDB.getConnection().createStatement();
        boolean status = stm.execute(sql);
        return status;
    }


    /**
     * phuong thuc xu li thoi gian thue sau khi tra xe
     * @return
     * @throws SQLException
     */
    public boolean endRent() throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        setReturnTime(formatter.format(date));
        return true;
    }

    /**
     * phuong thuc cap nhat giao dich thue trong co so du lieu
     * @return thong bao thanh cong hay that bai khi cap nhat
     * @throws SQLException
     */
    public boolean updateRentTransaction() throws SQLException {
        String sql = String.format("UPDATE renttransaction SET returnTime = '%s', rentCost = %d WHERE rentalCode = %d", returnTime, rentCost, rentalCode);
        LOGGER.info(sql);
        Statement stm = AIMSDB.getConnection().createStatement();
        boolean status = stm.execute(sql);
        return status;
    }

    public int getRentalCode() {
        return rentalCode;
    }

    /**
     * set rentalCode trong co so du lieu
     * @throws SQLException
     */
    public void setRentalCode() throws SQLException {
        String sql = "SELECT MAX(rentalCode) as Max FROM renttransaction";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()){
            this.rentalCode = res.getInt("Max")+1;
        }else{
            this.rentalCode = 1;
        }
    }

    /**
     *
     * lay ra giao dich chua hoan tat
     * @return giao dich thue xe
     * @throws SQLException
     */
    public static RentTransaction getRentTransactionUncompleted() throws SQLException{
        String sql = "SELECT * FROM renttransaction WHERE returnTime IS NULL";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()){
            return new RentTransaction(res.getInt("rentalCode"), res.getString("rentTime"), res.getInt("depositeCost"), null, res.getInt("bikeCode"), -1);
        }
        return null;
    }

    /**
     *
     * lay ra tat ca giao dich da hoan tat
     * @return danh sach giao dich thue xe
     * @throws SQLException
     */
    public static ArrayList<RentTransaction> getAllCompletedTransaction() throws SQLException{
        String sql = "SELECT * FROM renttransaction WHERE returnTime IS NOT NULL";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        ArrayList<RentTransaction> listTransaction = new ArrayList<RentTransaction>();
        while(res.next()){
            int cost = res.getInt("rentCost");
            if(cost < 0) cost = 0;
            RentTransaction newTransaction =  new RentTransaction(res.getInt("rentalCode"), res.getString("rentTime"), res.getInt("depositeCost"), res.getString("returnTime"), res.getInt("bikeCode"), cost);
            listTransaction.add(newTransaction);
        }
        return listTransaction;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public int getDepositeCost() {
        return depositCost;
    }

    public void setDepositeCost(Bike bike) {
        this.depositCost = bike.calculateDepositCost();
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public int getBikeCode() {
        return bikeCode;
    }

    public void setBikeCode(int bikeCode) {
        this.bikeCode = bikeCode;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public String getBarcode(){
        return barcode;
    }

    public void setBarcode(String _barcode){
        barcode = _barcode;
    }

}
