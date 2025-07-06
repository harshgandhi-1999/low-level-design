package easy.parkinglotlld;

import easy.parkinglotlld.vehicle.Vehicle;
import easy.parkinglotlld.vehicle.VehicleType;

public class ParkingSpot {
    private int spotNo;

    private Vehicle parkedVehicle;

    private VehicleType vehicleType;

    public ParkingSpot(int spotNo, VehicleType vehicleType) {
        this.spotNo = spotNo;
        this.vehicleType = vehicleType;
    }

    public int getSpotNo() {
        return spotNo;
    }

    public void setSpotNo(int spotNo) {
        this.spotNo = spotNo;
    }

    public synchronized boolean isAvailable() {
        return parkedVehicle == null;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        if (isAvailable() && vehicle.getVehicleType() == vehicleType) {
            parkedVehicle = vehicle;
        } else {
            throw new IllegalArgumentException("Invalid vehicle type or spot already occupied");
        }
    }

    public synchronized boolean unParkVehicle() {
        parkedVehicle = null;
        return true;
    }
}
