package controller;

import entity.dock.Dock;

import java.sql.SQLException;
import java.util.List;

/**
 * This {@code DockReturnController} class control the flow of dock return screen
 * in our ECO BIKE Software.
 * @author nhom2
 */
public class DockReturnController extends BaseController{
    public List getAllDock() throws SQLException {
        return Dock.getAllDockNotFull();
    }
}
