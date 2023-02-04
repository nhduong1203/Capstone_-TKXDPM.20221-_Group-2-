package entity.costcalculator;

import entity.bike.Bike;

public class DepositStrategy200 implements DepositCostCalculator{
    @Override
    public int calculateDepositCost(Bike bike) {
        return 200000;
    }
}
