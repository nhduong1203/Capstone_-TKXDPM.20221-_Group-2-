package entity.bike;

import controller.OutputController;
import entity.costcalculator.DepositStrategy;
import entity.costcalculator.Strategy1;
import utils.Configs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleBike extends Bike{
    private int numOfPedal;
    public SingleBike(ResultSet res) throws SQLException {
        super(res);
        this.numOfPedal = res.getInt("numOfPedal");
        this.costScale = 1.0F;
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
                "Type: " + getType() + "\n" +
                "Num of Seats: " + getNumOfSeat() + "\n" +
                "Num of Saddles: " + getNumOfSaddle() + "\n" +
                "Num of Pedals: " + numOfPedal + "\n" +
                "Value: " + getValueOfBike() + "\n" +
                "Status: " + getBikeStatus();

    }

    @Override
    public String getRentInfo() {
        return "Id: " + OutputController.Convert(Integer.toString(getId())) + "\n" +
                "Type: " + getType() + "\n" +
                "Num of Seats: " + getNumOfSeat() + "\n" +
                "Num of Saddles: " + getNumOfSaddle() + "\n" +
                "Num of Pedals: " + numOfPedal + "\n" +
                "Status: " + getBikeStatus() + "\n" +
                "Value: " + getValueOfBike() + " VNĐ" + "\n" +
                "Deposit: " + Configs.rentTransaction.getDepositeCost() + " VNĐ" + "\n" +
                "Start time: " + Configs.rentTransaction.getRentTime();
    }
}
