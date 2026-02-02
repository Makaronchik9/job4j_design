package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleParking extends AbstractParking {

    public SimpleParking(int passengerCarCapacity, int truckCarCapacity) {
        super(passengerCarCapacity, truckCarCapacity);
    }

    @Override
    public void add(Car car) {
        if ("passengerCar".equals(car.getType())) {
            parkPassenger(car);
        } else if ("truckCar".equals(car.getType())) {
            parkTruck(car);
        }
    }

    private void parkPassenger(Car car) {
        for (int i = 0; i < passengerCars.length; i++) {
            if (passengerCars[i] == null) {
                passengerCars[i] = car;
                return;
            }
        }
    }

    private void parkTruck(Car car) {
        for (int i = 0; i < truckCars.length; i++) {
            if (truckCars[i] == null) {
                truckCars[i] = car;
                return;
            }
        }

        int size = car.getOccupiedCells();
        for (int i = 0; i <= passengerCars.length - size; i++) {
            boolean free = true;
            for (int j = 0; j < size; j++) {
                if (passengerCars[i + j] != null) {
                    free = false;
                    break;
                }
            }
            if (free) {
                for (int j = 0; j < size; j++) {
                    passengerCars[i + j] = car;
                }
                return;
            }
        }
    }

    @Override
    public void remove(Car car) {
        clear(passengerCars, car);
        clear(truckCars, car);
    }

    private void clear(Car[] places, Car car) {
        for (int i = 0; i < places.length; i++) {
            if (car.equals(places[i])) {
                places[i] = null;
            }
        }
    }

    @Override
    public List<Car> findAll() {
        List<Car> result = new ArrayList<>();
        result.addAll(Arrays.asList(passengerCars));
        result.addAll(Arrays.asList(truckCars));
        return result;
    }
}
