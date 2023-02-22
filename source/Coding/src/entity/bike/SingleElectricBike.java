package entity.bike;

import controller.OutputController;
import entity.costcalculator.DepositStrategy;
import entity.costcalculator.Strategy1;
import utils.Configs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleElectricBike extends Bike{
    private int remainBattery;
    private int maxTime;
    private String motor;

    public SingleElectricBike(ResultSet res) throws SQLException {
        super(res);
        this.remainBattery = res.getInt("remainBattery");
        this.maxTime = res.getInt("maxTime");
        this.motor = res.getString("motor");
        this.costScale = 1.5F;
    }

//    @Override
//    public int calculateDepositCost() {
//        depositCostCalculator = new DepositStrategy();
//        return super.calculateDepositCost();
//    }
//
//    @Override
//    public long calculateRentCost(String startTime, String endTime) {
//        rentCostCalculator = new Strategy1();
//        return super.calculateRentCost(startTime, endTime);
//    }

    @Override
    public String getInfo() {
        return "Id: " + OutputController.Convert(Integer.toString(getId())) + "\n" +
                "Num of Seats: " + getNumOfSeat() + "\n" +
                "Type: " + getType() + "\n" +
                "Value: " + getValueOfBike() + "\n" +
                "Status: " + getBikeStatus() + "\n" +
                "Motor: " + this.motor + "\n" +
                "Max Time Run: " + this.maxTime + "\n" +
                "Remain Battery: " + this.remainBattery + "%";
    }

    @Override
    public String getRentInfo() {
        return "Id: " + OutputController.Convert(Integer.toString(getId())) + "\n" +
                "Num of Seats: " + getNumOfSeat() + "\n" +
                "Type: " + getType() + "\n" +
                "Status: " + getBikeStatus() + "\n" +
                "Value: " + getValueOfBike() + " VNĐ" + "\n" +
                "Deposit: " + Configs.rentTransaction.getDepositeCost() + " VNĐ" + "\n" +
                "Start time: " + Configs.rentTransaction.getRentTime() +
                "Motor: " + this.motor + "\n" +
                "Max Time Run: " + this.maxTime + "\n" +
                "Remain Battery: " + this.remainBattery + "%";
    }
}
