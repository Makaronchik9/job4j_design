package ru.job4j.ood.lsp.parking;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractParkingTest {
    private AbstractParking abstractParking;

    @BeforeEach
    void setUp() {
        abstractParking = new AbstractParking(3, 2);
    }

    private boolean containsCar(AbstractParking parking, Car carToFind) {
        return containsCarInArray(parking.passengerCars, carToFind) || containsCarInArray(parking.truckCars, carToFind);
    }

    private boolean containsCarInArray(Car[] cars, Car carToFind) {
        boolean rsl = false;
        for (Car car : cars) {
            if (car != null && car.equals(carToFind)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Test
    public void whenPassengerCarIsAccepted() {
        Car car = new Car("Audi", "passengerCar", 1);
        Car car1 = new Car("BMW", "passengerCar", 1);
        Car car2 = new Car("Toyota", "passengerCar", 1);
        abstractParking.add(car);
        abstractParking.add(car1);
        abstractParking.add(car2);
        assertTrue(containsCar(abstractParking, car));
        assertTrue(containsCar(abstractParking, car1));
        assertTrue(containsCar(abstractParking, car2));
    }

    @Test
    public void whenPassengerCarIsNotAccepted() {
        Car car = new Car("Audi", "passengerCar", 1);
        Car car1 = new Car("BMW", "passengerCar", 1);
        Car car2 = new Car("Toyota", "passengerCar", 1);
        Car car3 = new Car("Fiat", "passengerCar", 1);
        abstractParking.add(car);
        abstractParking.add(car1);
        abstractParking.add(car2);
        assertTrue(containsCar(abstractParking, car));
        assertTrue(containsCar(abstractParking, car1));
        assertTrue(containsCar(abstractParking, car2));
        assertFalse(containsCar(abstractParking, car3));
    }

    @Test
    public void whenTruckCarIsAccepted() {
        Car car = new Car("Kamaz", "truckCar", 2);
        abstractParking.add(car);
        assertTrue(containsCar(abstractParking, car));
    }

    @Test
    public void whenTruckCarIsNotAccepted() {
        Car car = new Car("Audi", "passengerCar", 1);
        Car car1 = new Car("BMW", "passengerCar", 1);
        Car car2 = new Car("Toyota", "passengerCar", 1);
        Car car3 = new Car("Kamaz", "truckCar", 2);
        Car car4 = new Car("Maz", "truckCar", 2);
        Car car5 = new Car("Maz2", "truckCar", 2);
        abstractParking.add(car);
        abstractParking.add(car1);
        abstractParking.add(car2);
        abstractParking.add(car3);
        abstractParking.add(car4);
        abstractParking.add(car5);
        assertTrue(containsCar(abstractParking, car));
        assertTrue(containsCar(abstractParking, car1));
        assertTrue(containsCar(abstractParking, car2));
        assertTrue(containsCar(abstractParking, car3));
        assertTrue(containsCar(abstractParking, car4));
        assertFalse(containsCar(abstractParking, car5));
    }

    @Test
    public void whenTruckCarIsAcceptedOnThePassengerCarsParking() {
        Car car = new Car("Audi", "passengerCar", 1);
        Car car1 = new Car("Kamaz", "truckCar", 2);
        Car car2 = new Car("Maz", "truckCar", 2);
        Car car3 = new Car("Maz2", "truckCar", 2);
        abstractParking.add(car);
        abstractParking.add(car1);
        abstractParking.add(car2);
        abstractParking.add(car3);
        assertTrue(containsCar(abstractParking, car));
        assertTrue(containsCar(abstractParking, car1));
        assertTrue(containsCar(abstractParking, car2));
        assertTrue(containsCar(abstractParking, car3));
    }

    @Test
    public void whenRemovePassengerCar() {
        Car car = new Car("Audi", "passengerCar", 1);
        abstractParking.add(car);
        abstractParking.remove(car);
        assertFalse(containsCar(abstractParking, car));
    }

    @Test
    public void whenRemoveTruckCar() {
        Car car = new Car("Kamaz", "truckCar", 2);
        abstractParking.add(car);
        abstractParking.remove(car);
        assertFalse(containsCar(abstractParking, car));
    }

    @Test
    public void whenRemoveTruckCarFromPassengerSpots() {
        Car car = new Car("Audi", "passengerCar", 1);
        Car car1 = new Car("Kamaz", "truckCar", 2);
        Car car2 = new Car("Maz", "truckCar", 2);
        Car car3 = new Car("Maz2", "truckCar", 2);
        abstractParking.add(car);
        abstractParking.add(car1);
        abstractParking.add(car2);
        abstractParking.add(car3);
        abstractParking.remove(car3);
        assertTrue(containsCar(abstractParking, car));
        assertTrue(containsCar(abstractParking, car1));
        assertTrue(containsCar(abstractParking, car2));
        assertFalse(containsCar(abstractParking, car3));
    }

    @Test
    public void findAllCars() {
        Car car = new Car("Audi", "passengerCar", 1);
        Car car1 = new Car("Kamaz", "truckCar", 2);
        abstractParking.add(car);
        abstractParking.add(car1);
        List<Car> allCars = abstractParking.findAll();
        assertTrue(allCars.contains(car));
        assertTrue(allCars.contains(car1));
    }
}