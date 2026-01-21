package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractParking implements Parking {

    protected Car[] passengerCars;
    protected Car[] truckCars;

    public AbstractParking(int passengerCarCapacity, int truckCarCapacity) {
        passengerCars = new Car[passengerCarCapacity];
        truckCars = new Car[truckCarCapacity];
    }

    @Override
    public void add(Car car) {
        if ("passengerCar".equals(car.getType())) {
            for (int i = 0; i < passengerCars.length; i++) {
                if (passengerCars[i] == null) {
                    passengerCars[i] = car;
                    break;
                }
            }
        } else if ("truckCar".equals(car.getType())) {
            for (int i = 0; i < truckCars.length; i++) {
                if (truckCars[i] == null) {
                    truckCars[i] = car;
                    return;
                }
            }
            for (int i = 0; i <= passengerCars.length - car.getOccupiedCells(); i++) {
                boolean gotPlace = true;
                for (int j = 0; j < car.getOccupiedCells(); j++) {
                    if (passengerCars[i + j] != null) {
                        gotPlace = false;
                        break;
                    }
                }
                if (gotPlace) {
                    for (int j = 0; j < car.getOccupiedCells(); j++) {
                        passengerCars[i + j] = car;
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void remove(Car car) {
        if ("passengerCar".equals(car.getType())) {
            for (int i = 0; i < passengerCars.length; i++) {
                if (passengerCars[i] != null && passengerCars[i].equals(car)) {
                    passengerCars[i] = null;
                }
            }
        } else if ("truckCar".equals(car.getType())) {
            for (int i = 0; i < truckCars.length; i++) {
                if (truckCars[i] != null && truckCars[i].equals(car)) {
                    truckCars[i] = null;
                    return;
                }
            }
            for (int i = 0; i < passengerCars.length; i++) {
                if (passengerCars[i].equals(car)) {
                    for (int j = 0; j < car.getOccupiedCells(); j++) {
                        if (i + j < passengerCars.length) {
                            passengerCars[i + j] = null;
                        }
                    }
                    return;
                }
            }
        }
    }

    @Override
    public List<Car> findAll() {
        List<Car> allCars = new ArrayList<>();
        allCars.addAll(Arrays.asList(passengerCars));
        allCars.addAll(Arrays.asList(truckCars));
        return allCars;
    }
}