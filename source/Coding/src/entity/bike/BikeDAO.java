package entity.bike;

import entity.db.AIMSDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This {@code BikeDAO} class DAU bike
 * in our ECO BIKE Software.
 *
 * @author nhom2
 *
 */
public class BikeDAO {
    public static int getBikeStatusByID(int bikeID) throws SQLException {
        String sql = "SELECT * FROM bike WHERE bikeCode = "+bikeID;
        ResultSet res = AIMSDB.query(sql);
        res.next();
        return res.getInt("status");
    }
    public static void updateBIkeStatus(int bikeID, int status){
        String sql = "UPDATE bike SET status = "+status+" WHERE bikeCode = "+bikeID;
        AIMSDB.execute(sql);
    }

    public static void updateBikeInDock(int bikeID, int dockID){
        String sql = "UPDATE bike SET dockID = "+dockID+" WHERE bikeCode = "+bikeID;
        AIMSDB.execute(sql);
    }
}
