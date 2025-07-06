package easy.parkinglotlld;

import easy.parkinglotlld.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Level> levels;
    private static volatile ParkingLot INSTANCE;

    private ParkingLot() {
        levels = new ArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (INSTANCE == null) {
            synchronized (ParkingLot.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ParkingLot();
                }
            }
        }
        return INSTANCE;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.parkVehicle(vehicle)) {
                System.out.println("Vehicle parked successfully.");
                return true;
            }
        }
        System.out.println("Could not park vehicle.");
        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.unparkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        for (Level level : levels) {
            level.displayAvailability();
        }
    }
}
