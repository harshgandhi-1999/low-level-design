package easy.parkinglotlld;

import easy.parkinglotlld.vehicle.Bike;
import easy.parkinglotlld.vehicle.Car;
import easy.parkinglotlld.vehicle.Vehicle;

public class ParkingLotDemo {

    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();

        parkingLot.addLevel(new Level(1, 10));
        parkingLot.addLevel(new Level(2, 5));

        Vehicle car = new Car("ABC123");
        Vehicle motorcycle = new Bike("M1234");

        // Park vehicles
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(motorcycle);

        // Display availability
        parkingLot.displayAvailability();

        // Unpark vehicle
        parkingLot.unParkVehicle(motorcycle);

        // Display updated availability
        parkingLot.displayAvailability();
    }
}
