package org.example;

import java.util.Comparator;

public class PassengerByAgeThenNameComparator implements Comparator<Passenger> {

    @Override
    public int compare(Passenger passenger1, Passenger passenger2) {
        int comparatorByAge = Integer.compare(passenger1.getAge(), passenger2.getAge());

        if (comparatorByAge == 0) {
            return passenger1.getName().compareTo(passenger2.getName());
        }

        return comparatorByAge;
    }
}
