package org.example;

import java.util.Comparator;

public class PassengerByTicketClassComparator implements Comparator<Passenger> {
    @Override
    public int compare(Passenger passenger1, Passenger passenger2) {
        return passenger1.getPassengerClass().compareTo(passenger2.getPassengerClass());
    }
}
