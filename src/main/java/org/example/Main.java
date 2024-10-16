package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        String fileName = "titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList = new ArrayList<>();

        loadPassengerDataFromFile(passengerList, fileName);

//        displayAllPassengers( passengerList );

        // Assignment: Implement and test the following methods

        /* Q1. GET PASSENGER NAMES */
        System.out.println("PASSENGER NAMES : " + Arrays.toString(getPassengerNames(passengerList)));

        /* Q2. GET PASSENGER CONTAINING NAMES */
        System.out.println("PASSENGER CONTAINING NAMES : " + getPassengersContainingNames(passengerList, "William"));

        /* Q3. PASSENGERS ELDER THAN */
        System.out.println("PASSENGER ELDER THAN 20: " + getPassengersOlderThan(passengerList, 20));

        /* Q4. COUNT BY GENDER */
        System.out.println("ONLY MALE PASSENGERS: " + countPassengersByGender(passengerList, "male"));
        System.out.println("ONLY FEMALE PASSENGERS: " + countPassengersByGender(passengerList, "female"));

        /* Q5. SUM FARES */
        System.out.printf("THE SUM OF ALL THE FARES IS: [$%.2f]\r\n", sumFares(passengerList));

        /* Q6. COUNT OF MALE SURVIVORS */
        System.out.println("NAME OF MALE SURVIVORS: " + Arrays.toString(maleSurvivors(passengerList)));

        /* Q7. TICKET OWNER */
        System.out.println("PASSENGER TICKET NUMBER: " + ticketOwner(passengerList, "382652"));

        /* Q8. AVERAGE AGE */
        System.out.println("AVERAGE AGE: " + averageAge(passengerList));

        /* Q9. PASSENGER BY TICKET CLASS */
        System.out.println("PASSENGERS INTO A CERTAIN CLASS : " + getPassengersByTicketClass(passengerList, PassengerClass.FIRST));

        /* Q10. SORTING BY ID */
        sortPassengersByPassengerId(passengerList);
        for (Passenger passenger : passengerList) {
            System.out.println(passenger);
        }

        /* Q11. SORT PASSENGERS BY NAME */
        sortPassengersByName(passengerList);
        for (Passenger passenger : passengerList) {
            System.out.println(passenger);
        }

        /* Q12. SORT PASSENGERS BY AGE AND NAME */
        List<Passenger> sortedByAgeThenName = sortPassengersByAgeThenName(passengerList);

        for (Passenger passenger : sortedByAgeThenName) {
            System.out.println(passenger);
        }

        /* Q13. SORT PASSENGERS BY GENDER AND PASSENGER NUMBER */
        List<Passenger> sortedByGenderThenNumber = sortPassengersByGenderThenPassengerNumber(passengerList);

        for (Passenger passenger : sortedByGenderThenNumber) {
            System.out.println(passenger);
        }

        /* Q14. SORTED BY FARE THEN SURVIVAL */
        List<Passenger> sortedByFareThenSurvival = sortPassengersByFareThenSurvival(passengerList);

        for (Passenger passenger : sortedByFareThenSurvival) {
            System.out.println(passenger);
        }

        /* Q15. SORTED BY TICKET CLASS */
        List<Passenger> sortedByTicketClass = sortPassengersByTicketClass(passengerList);

        for (Passenger passenger : sortedByTicketClass) {
            System.out.println(passenger);
        }

        /* Q16. SORTED BY AGE */
        List<Passenger> sortedByAge = sortPassengersByAge(passengerList);

        for (Passenger passenger : sortedByAge) {
            System.out.println(passenger);
        }

        /* Q17. SORTED BY TICKET NUMBER LAMBDA */
        List<Passenger> sortedByTicketNumberLambda = sortPassengersByTicketNumberLambda(passengerList);

        for (Passenger passenger : sortedByTicketNumberLambda) {
            System.out.println(passenger);
        }

        /* Q18. SORTED BY NUMBER STATIC  */
        List<Passenger> sortedByTicketNumberStatic = sortPassengersByTicketNumberStatic(passengerList);

        for (Passenger passenger : sortedByTicketNumberStatic) {
            System.out.println(passenger);
        }

        /* Q19. FIND PASSENGER BY TICKET NUMBER */
        Collections.sort(passengerList, Passenger.TicketNumberComparator);

        Passenger targetPassengerByTicketNumber = new Passenger(null, "347082");
        Passenger foundPassengerByTicketNumber = findPassengerByTicketNumber(passengerList, targetPassengerByTicketNumber);

        if (foundPassengerByTicketNumber != null) {
            System.out.println("Passenger found! " + foundPassengerByTicketNumber);
        } else {
            System.out.println("Passenger not found :(");
        }

        /* Q20. FIND PASSENGER BY ID */
        Collections.sort(passengerList, Passenger.PassengerIdComparator);

        Passenger targetPassengerById = new Passenger("2", null);

        Passenger foundPassengerById = findPassengerByPassengerId(passengerList, targetPassengerById);

        if (foundPassengerById != null) {
            System.out.println("Passenger found! " + foundPassengerById);
        } else {
            System.out.println("Passenger not found :(");
        }
    }

    /*
     * CUSTOM METHODS
     */

    public static String[] getPassengerNames(ArrayList<Passenger> passengerList) {
        String[] passengerNames = new String[passengerList.size()];

        for (int i = 0; i < passengerList.size(); i++) {
            passengerNames[i] = passengerList.get(i).getName();
        }

        return passengerNames;
    }

    public static List<Passenger> getPassengersContainingNames(List<Passenger> passengerList, String entryName) {
        List<Passenger> passengersContainingNames = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getName().contains(entryName)) {
                passengersContainingNames.add(passenger);
            }
        }

        return passengersContainingNames;
    }

    public static List<Passenger> getPassengersOlderThan(List<Passenger> passengerList, int age) {
        List<Passenger> passengersOlderThan = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getAge() > age) {
                passengersOlderThan.add(passenger);
            }
        }

        return passengersOlderThan;
    }

    public static List<Passenger> countPassengersByGender(List<Passenger> passengerList, String gender) {
        List<Passenger> passengersByGender = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getGender().equals(gender)) {
                passengersByGender.add(passenger);
            }
        }

        return passengersByGender;
    }

    public static double sumFares(ArrayList<Passenger> passengerList) {
        double totalFares = 0.0;
        for (Passenger passenger : passengerList) {
            totalFares += passenger.getFare();
        }

        return totalFares;
    }

    public static String[] maleSurvivors(List<Passenger> passengerList) {
        String[] maleSurvivors = new String[passengerList.size()];

        for (int i = 0; i < passengerList.size(); i++) {
            if (passengerList.get(i).getGender().equals("male")) {
                maleSurvivors[i] = passengerList.get(i).getName();
            }
        }

        return maleSurvivors;
    }

    public static Passenger ticketOwner(ArrayList<Passenger> passengerList, String ticketNumber) {
        for (Passenger passenger : passengerList) {
            if (passenger.getTicketNumber().equals(ticketNumber)) {
                return passenger;
            }
        }
        return null;
    }

    public static int averageAge(ArrayList<Passenger> passengerList) {
        int totalAge = 0;

        for (Passenger passenger : passengerList) {
            totalAge += passenger.getAge();
        }

        return totalAge / passengerList.size();
    }

    public static ArrayList<Passenger> getPassengersByTicketClass(ArrayList<Passenger> passengerList, PassengerClass passengerClass) {
        ArrayList<Passenger> passengersByTicketClass = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getPassengerClass() == passengerClass) {
                passengersByTicketClass.add(passenger);
            }
        }
        return passengersByTicketClass;
    }

    public static void sortPassengersByPassengerId(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList);
    }

    public static void sortPassengersByName(ArrayList<Passenger> passengerList) {
        passengerList.sort(Comparator.comparing(Passenger::getName));
    }

    public static List<Passenger> sortPassengersByAgeThenName(List<Passenger> passengerList) {
        passengerList.sort(Comparator.comparing(Passenger::getAge).thenComparing(Passenger::getName));
        return passengerList;
    }

    public static List<Passenger> sortPassengersByGenderThenPassengerNumber(List<Passenger> passengerList) {
        passengerList.sort(Comparator.comparing(Passenger::getGender).thenComparing(Passenger::getPassengerId));
        return passengerList;
    }

    public static List<Passenger> sortPassengersByFareThenSurvival(List<Passenger> passengerList) {
        passengerList.sort(Comparator.comparing(Passenger::getFare).thenComparing(Comparator.comparing(Passenger::getSurvived).reversed()));
        return passengerList;
    }

    public static List<Passenger> sortPassengersByTicketClass(List<Passenger> passengerList) {
        passengerList.sort(Comparator.comparing(Passenger::getPassengerClass));
        return passengerList;
    }

    public static List<Passenger> sortPassengersByAge(List<Passenger> passengerList) {
        passengerList.sort(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return p1.getAge().compareTo(p2.getAge());
            }
        });
        return passengerList;
    }

    public static List<Passenger> sortPassengersByTicketNumberLambda(List<Passenger> passengerList) {
        passengerList.sort((p1, p2) -> p1.getTicketNumber().compareTo(p2.getTicketNumber()));
        return passengerList;
    }

    public static List<Passenger> sortPassengersByTicketNumberStatic(List<Passenger> passengerList) {
        passengerList.sort(Passenger.TicketNumberComparator);
        return passengerList;
    }

    public static Passenger findPassengerByTicketNumber(List<Passenger> passengerList, Passenger targetPassenger) {
        Collections.sort(passengerList, Passenger.TicketNumberComparator);

        int index = Collections.binarySearch(passengerList, targetPassenger, Passenger.TicketNumberComparator);

        if (index >= 0) {
            return passengerList.get(index);
        } else {
            return null;
        }
    }

    public static Passenger findPassengerByPassengerId(List<Passenger> passengerList, Passenger targetPassenger) {
        Collections.sort(passengerList, Passenger.PassengerIdComparator);

        int index = Collections.binarySearch(passengerList, targetPassenger, Passenger.PassengerIdComparator);

        if (index >= 0) {
            return passengerList.get(index);
        } else {
            return null;
        }
    }

    public static void loadPassengerDataFromFile(ArrayList<Passenger> passengerList, String fileName) {

        // Format of each row of data is:
        // Name,Age,Height(m),GPA  - these heading names are included as the first row in file
        // John Malone,20,1.78,3.55   for example

        // Use a Regular Expression to set both comma and newline as delimiters.
        //  sc.useDelimiter("[,\\r\\n]+");
        // Text files in windows have lines ending with "CR-LF" or "\r\n" sequences.
        // or may have only a newline - "\n"
        // Windows uses CRLF (\r\n, 0D 0A) line endings while Unix just uses LF (\n, 0A).
        //
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+")) {

            // skip past the first line, as it has field names (not data)
            if (sc.hasNextLine())
                sc.nextLine();   // read the header line containing column titles, but don't use it

            // while there is a next token to read....
            System.out.println("Go...");

            while (sc.hasNext()) {
                String passengerId = sc.next();    // read passenger ID
                int survived = sc.nextInt();     // 0=false, 1=true
                int passengerClass = sc.nextInt();  // passenger class, 1=1st, 2=2nd or 3rd
                String name = sc.next();
                String gender = sc.next();
                int age = sc.nextInt();
                int siblingsAndSpouses = sc.nextInt();
                int parentsAndChildren = sc.nextInt();
                String ticketNumber = sc.next();
                double fare = sc.nextDouble();
                String cabin = sc.next();
                String embarkedAt = sc.next();

//                System.out.println(passengerId + ", " + name);

                passengerList.add(
                        new Passenger(passengerId, survived, passengerClass,
                                name, gender, age, siblingsAndSpouses, parentsAndChildren, ticketNumber,
                                fare, cabin, embarkedAt) // NEW ATTRIBUTE ticketClass
                );
            }
        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException caught. The file " + fileName + " may not exist." + exception);
        }
    }

    public static void displayAllPassengers(ArrayList<Passenger> passengerList) {
        for (Passenger passenger : passengerList) {
            System.out.println(passenger);
        }
    }
}