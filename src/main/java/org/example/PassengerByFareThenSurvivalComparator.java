package org.example;

import java.util.Comparator;

public class PassengerByFareThenSurvivalComparator implements Comparator<Passenger> {
    @Override
    public int compare(Passenger passenger1, Passenger passenger2) {
        int compareByFare = Double.compare(passenger1.getFare(), passenger2.getFare());

        if (compareByFare == 0) {
            return Integer.compare(passenger1.getSurvived(), passenger2.getSurvived());
        }

        return compareByFare;
    }
}
