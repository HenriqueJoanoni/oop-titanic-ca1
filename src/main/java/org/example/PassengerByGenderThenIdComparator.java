package org.example;

import java.util.Comparator;

public class PassengerByGenderThenIdComparator implements Comparator<Passenger> {
    @Override
    public int compare(Passenger passenger1, Passenger passenger2) {
        int comparatorByGender = passenger1.getGender().compareTo(passenger2.getGender());

        if (comparatorByGender == 0) {
            return passenger1.getPassengerId().compareTo(passenger2.getPassengerId());
        }

        return comparatorByGender;
    }
}
