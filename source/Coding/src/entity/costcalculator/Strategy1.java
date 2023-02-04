package entity.costcalculator;

import entity.bike.Bike;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Strategy1 implements RentCostCalculator {
    @Override
    public long calculateRentCost(String startTime, String endTime, Bike bike) {
        // Custom date format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startTime);
            d2 = format.parse(endTime);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();//as given
//        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        if(minutes<10) {
            return 0;
        }
        else if(minutes < 30){
            return (long) (10000 * bike.getCostScale());
        }
        long bonus = (minutes-30)/15;
        if((minutes-30) % 15 != 0) bonus += 1;
        long cost = (10000 + bonus * 3000);
        cost = (long) (cost * bike.getCostScale());
        return cost;
    }

    @Override
    public long calculateRentCostHasStopTime(String startTime, String endTime, Bike bike, long stopTime) {
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
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) - stopTime;
        if(minutes<10) {
            return 0;
        }
        else if(minutes < 30){
            return 10000;
        }
        long bonus = (minutes-30)/15;
        if((minutes-30) % 15 != 0) bonus += 1;
        long cost = (10000 + bonus * 3000);
        cost = (long) (cost * bike.getCostScale());
        return cost;
    }
}
