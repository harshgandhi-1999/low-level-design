package easy.parkinglotlld;

import easy.parkinglotlld.vehicle.Vehicle;
import easy.parkinglotlld.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private int floor;

    private List<ParkingSpot> parkingSpots;

    public Level(int floor, int numSpots) {
        this.floor = floor;
        parkingSpots = new ArrayList<>(numSpots);

        // Assign spots in ration of 50:50 for two-wheelers and four-wheelers
        double spotsForTwoWheeler = 0.50;

        int numTwoWheelers = (int) (numSpots * spotsForTwoWheeler);

        for (int i = 1; i <= numTwoWheelers; i++) {
            parkingSpots.add(new ParkingSpot(i, VehicleType.TWO_WHEELER));
        }
        for (int i = numTwoWheelers + 1; i <= numSpots; i++) {
            parkingSpots.add(new ParkingSpot(i, VehicleType.FOUR_WHEELER));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable() && spot.getVehicleType() == vehicle.getVehicleType()) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)) {
                return spot.unParkVehicle();
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.println("Level " + floor + " Availability:");
        for (ParkingSpot spot : parkingSpots) {
            System.out.println("Spot " + spot.getSpotNo() + ": " + (spot.isAvailable() ? "Available For" : "Occupied By ") + " " + spot.getVehicleType());
        }
    }
}
