package entity.costcalculator;

import entity.bike.Bike;

public class DepositStrategy implements DepositCostCalculator{
    @Override
    public int calculateDepositCost(Bike bike) {
        return bike.getValueOfBike() * 4 / 10;
    }
}
