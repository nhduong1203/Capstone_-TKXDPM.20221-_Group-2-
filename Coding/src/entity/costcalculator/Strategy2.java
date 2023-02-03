package entity.costcalculator;

import entity.bike.Bike;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Strategy2 implements RentCostCalculator{
    @Override
    public long calculateRentCost(String startTime, String endTime, Bike bike) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startTime);
            d2 = format.parse(endTime);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        long hours = minutes / 60;
        long minBonus = minutes - 60 * hours;
        long bonus = minBonus / 15;
        if(minBonus % 15 != 0) bonus += 1;
        long cost = 0;
        if(hours < 12){
            cost = 200000 - (12-hours) * 10000;
        }
        else if(hours < 24){
            cost = 200000;
        }
        else{
            cost = 200000 + bonus * 2000;
        }
        return cost;
    }

    @Override
    public long calculateRentCostHasStopTime(String startTime, String endTime, Bike bike, long stopTime) {
        return 0;
    }
}
