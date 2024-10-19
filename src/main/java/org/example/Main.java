package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* Local machine is set to pt-br, this is needed to parse file correctly */
        Locale.setDefault(Locale.US);

        /* QUESTIONS BEGIN HERE */
        Scanner sc = new Scanner(System.in);
        String fileName = "titanic-data-100.csv";
        ArrayList<Passenger> passengerList = new ArrayList<>();

        /* DISPLAYS THE APPLICATION MENU */
        displayMenu();

        /* LOAD THE PASSENGER DATA FROM THE FILE */
        loadPassengerDataFromFile(passengerList, fileName);

        /* RECEIVES THE USER INPUT AND EXECUTE THE CUSTOM METHODS */
        handleInput(sc.nextInt(), passengerList);
    }

    /**************************************
     *                                    *
     *          CUSTOM METHODS            *
     *                                    *
     * ************************************/

    /**
     * Returns an array of passengers.
     *
     * @param passengerList ArrayList<Passenger>
     * @return String[]
     */
    public static String[] getPassengerNames(ArrayList<Passenger> passengerList) {
        String[] passengerNames = new String[passengerList.size()];

        for (int i = 0; i < passengerList.size(); i++) {
            passengerNames[i] = passengerList.get(i).getName();
        }

        return passengerNames;
    }

    /**
     * Returns a List of passengers
     *
     * @param passengerList ArrayList<Passenger>
     * @param entryName     String
     * @return List<Passenger>
     */
    public static List<Passenger> getPassengersContainingNames(List<Passenger> passengerList, String entryName) {
        List<Passenger> passengersContainingNames = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getName().contains(entryName)) {
                passengersContainingNames.add(passenger);
            }
        }

        return passengersContainingNames;
    }

    /**
     * Returns all passengers older than a specific age
     *
     * @param passengerList List<Passenger>
     * @param age           int
     * @return List<Passenger>
     */
    public static List<Passenger> getPassengersOlderThan(List<Passenger> passengerList, int age) {
        List<Passenger> passengersOlderThan = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getAge() > age) {
                passengersOlderThan.add(passenger);
            }
        }

        return passengersOlderThan;
    }

    /**
     * Returns a List of all passengers by a specific gender
     *
     * @param passengerList List<Passenger>
     * @param gender        String
     * @return List<Passenger>
     */
    public static List<Passenger> countPassengersByGender(List<Passenger> passengerList, String gender) {
        List<Passenger> passengersByGender = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getGender().equals(gender)) {
                passengersByGender.add(passenger);
            }
        }

        return passengersByGender;
    }

    /**
     * Return the total value of all passenger fares
     *
     * @param passengerList ArrayList<Passenger>
     * @return double
     */
    public static double sumFares(ArrayList<Passenger> passengerList) {
        double totalFares = 0.0;
        for (Passenger passenger : passengerList) {
            totalFares += passenger.getFare();
        }

        return totalFares;
    }

    /**
     * Return an array of all male survivors
     *
     * @param passengerList List<Passenger>
     * @return String[]
     */
    public static String[] maleSurvivors(List<Passenger> passengerList) {
        List<String> maleSurvivors = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getGender().equals("male") && passenger.getSurvived() == 1) {
                maleSurvivors.add(passenger.getName());
            }
        }

        return maleSurvivors.toArray(new String[0]);
    }

    /**
     * Return the passenger who owns a specific ticket
     *
     * @param passengerList ArrayList<Passenger>
     * @param ticketNumber  String
     * @return Passenger | null
     */
    public static Passenger ticketOwner(ArrayList<Passenger> passengerList, String ticketNumber) {
        for (Passenger passenger : passengerList) {
            if (passenger.getTicketNumber().equals(ticketNumber)) {
                return passenger;
            }
        }
        return null;
    }

    /**
     * Return the average age of all passengers
     *
     * @param passengerList ArrayList<Passenger>
     * @return int
     */
    public static int averageAge(ArrayList<Passenger> passengerList) {
        int totalAge = 0;

        for (Passenger passenger : passengerList) {
            totalAge += passenger.getAge();
        }

        return totalAge / passengerList.size();
    }

    /**
     * Return all passengers from a specific class
     *
     * @param passengerList  ArrayList<Passenger>
     * @param passengerClass PassengerClass
     * @return ArrayList<Passenger>
     */
    public static ArrayList<Passenger> getPassengersByTicketClass(ArrayList<Passenger> passengerList, PassengerClass passengerClass) {
        ArrayList<Passenger> passengersByTicketClass = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getPassengerClass() == passengerClass) {
                passengersByTicketClass.add(passenger);
            }
        }
        return passengersByTicketClass;
    }

    /**
     * Sort passenger object by id
     *
     * @param passengerList ArrayList<Passenger>
     */
    public static void sortPassengersByPassengerId(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList);
    }

    /**
     * Sort passenger by name
     *
     * @param passengerList ArrayList<Passenger>
     */
    public static void sortPassengersByName(ArrayList<Passenger> passengerList) {
        passengerList.sort(Comparator.comparing(Passenger::getName));
    }

    /**
     * Sort passenger by age then name
     *
     * @param passengerList List<Passenger>
     * @return List<Passenger>
     */
    public static List<Passenger> sortPassengersByAgeThenName(List<Passenger> passengerList) {
        Collections.sort(passengerList, new PassengerByAgeThenNameComparator());
        return passengerList;
    }

    /**
     * Sort passengers by gender then ID
     *
     * @param passengerList List<Passenger>
     * @return List<Passenger>
     */
    public static List<Passenger> sortPassengersByGenderThenPassengerNumber(List<Passenger> passengerList) {
        Collections.sort(passengerList, new PassengerByGenderThenIdComparator());
        return passengerList;
    }

    /**
     * Sort passengers by fare price then survival status
     *
     * @param passengerList List<Passenger>
     * @return List<Passenger>
     */
    public static List<Passenger> sortPassengersByFareThenSurvival(List<Passenger> passengerList) {
        Collections.sort(passengerList, new PassengerByFareThenSurvivalComparator());
        return passengerList;
    }

    /**
     * Sort passenger by ticket class
     *
     * @param passengerList List<Passenger>
     * @return List<Passenger>
     */
    public static List<Passenger> sortPassengersByTicketClass(List<Passenger> passengerList) {
        Collections.sort(passengerList, new PassengerByTicketClassComparator());
        return passengerList;
    }

    /**
     * Sort passengers by age
     *
     * @param passengerList List<Passenger>
     * @return List<Passenger>
     */
    public static List<Passenger> sortPassengersByAge(List<Passenger> passengerList) {
        passengerList.sort(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger passenger1, Passenger passenger2) {
                return passenger1.getAge().compareTo(passenger2.getAge());
            }
        });
        return passengerList;
    }

    /**
     * Sort passengers by ticket number
     *
     * @param passengerList List<Passenger>
     * @return List<Passenger>
     */
    public static List<Passenger> sortPassengersByTicketNumberLambda(List<Passenger> passengerList) {
        passengerList.sort((passenger1, passenger2) -> passenger1.getTicketNumber().compareTo(passenger2.getTicketNumber()));
        return passengerList;
    }

    /**
     * Sort passengers by ticket number
     *
     * @param passengerList List<Passenger>
     * @return List<Passenger>
     */
    public static List<Passenger> sortPassengersByTicketNumberStatic(List<Passenger> passengerList) {
        passengerList.sort(Passenger.TicketNumberComparator);
        return passengerList;
    }

    /**
     * Find Passengers by ticket number
     *
     * @param passengerList   List<Passenger>
     * @param targetPassenger Passenger
     * @return Passenger
     */
    public static Passenger findPassengerByTicketNumber(List<Passenger> passengerList, Passenger targetPassenger) {
        Collections.sort(passengerList, Passenger.TicketNumberComparator);

        int index = Collections.binarySearch(passengerList, targetPassenger, Passenger.TicketNumberComparator);

        if (index >= 0) {
            return passengerList.get(index);
        } else {
            return null;
        }
    }

    /**
     * Find passengers by passenger ID
     *
     * @param passengerList   List<Passenger>
     * @param targetPassenger Passenger
     * @return Passenger
     */
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

    /**************************************
     *                                    *
     *          MENU FUNCTIONS            *
     *                                    *
     * ************************************/

    /**
     * Display the initial menu
     */
    public static void displayMenu() {
        System.out.println("""
                         _    _                  _____ _ _              _        ____                                             \s
                      __|_|__|_|__              |_   _(_) |_ __ _ _ __ (_) ___  |  _ \\ __ _ ___ ___  ___ _ __   __ _  ___ _ __ ___\s
                    _|____________|__             | | | | __/ _` | '_ \\| |/ __| | |_) / _` / __/ __|/ _ \\ '_ \\ / _` |/ _ \\ '__/ __|
                   |o o o o o o o o /             | | | | || (_| | | | | | (__  |  __/ (_| \\__ \\__ \\  __/ | | | (_| |  __/ |  \\__ \\
                 ~'`~'`~'`~'`~'`~'`~'`~'~         |_| |_|\\__\\__,_|_| |_|_|\\___| |_|   \\__,_|___/___/\\___|_| |_|\\__, |\\___|_|  |___/
                                                                                                                |___/              \s
                \s""");

        System.out.println("""
                Select an option:
                1. Get Passenger Names
                2. Get Passenger Containing Names
                3. Get Passengers Older Than A Certain Age
                4. Count Passengers by Gender
                5. Total Fares Prices
                6. List Male Survivors
                7. Owner of a Certain Ticket
                8. Average Age
                9. Get Passengers by Ticket Class
                10. Sort Passengers by Passenger ID
                11. Sort Passengers by Name
                12. Sort Passengers by Age then Name
                13. Sort Passengers by Gender then Passenger ID
                14. Sort Passengers by Fare then Survival
                15. Sort Passengers by Ticket Class
                16. Sort Passengers by Age
                17. Sort Passengers by Ticket Number Using Lambda Function
                18. Sort Passengers by Ticket Number Using Static Comparator
                19. Find Passenger by Ticket Number
                20. Find Passenger by Passenger ID
                """);

    }

    /**
     * Receives the user input
     *
     * @param userInput     int
     * @param passengerList ArrayList<Passenger>
     */
    public static void handleInput(int userInput, ArrayList<Passenger> passengerList) {
        Scanner input = new Scanner(System.in);
        switch (userInput) {
            case 1:
                /* Q1. GET PASSENGER NAMES */
                System.out.println("PASSENGER NAMES : " + Arrays.toString(getPassengerNames(passengerList)));
                break;
            case 2:
                /* Q2. GET PASSENGER CONTAINING NAMES */
                System.out.println("Please insert a name to be searched: ");
                String searchName = input.nextLine();
                System.out.println("PASSENGER CONTAINING NAME " + searchName.toUpperCase() + ": " + getPassengersContainingNames(passengerList, searchName));
                break;
            case 3:
                /* Q3. PASSENGERS ELDER THAN */
                System.out.println("Enter the threshold age: ");
                int searchAge = input.nextInt();
                System.out.println("PASSENGER ELDER THAN " + searchAge + ": " + getPassengersOlderThan(passengerList, searchAge));
                break;
            case 4:
                /* Q4. COUNT BY GENDER */
                System.out.println("Enter the gender you want to search (male|female): ");
                String searchGender = input.nextLine();
                System.out.println("ONLY " + searchGender.toUpperCase() + " PASSENGERS: " + countPassengersByGender(passengerList, searchGender));
                break;
            case 5:
                /* Q5. SUM FARES */
                System.out.printf("THE SUM OF ALL THE FARES IS: [$%.2f]\r\n", sumFares(passengerList));
                break;
            case 6:
                /* Q6. COUNT OF MALE SURVIVORS */
                System.out.println("NAME OF MALE SURVIVORS: " + Arrays.toString(maleSurvivors(passengerList)));
                break;
            case 7:
                /* Q7. TICKET OWNER */
                System.out.println("Enter the ticket number: ");
                String ticketNumber = input.nextLine();
                System.out.println("PASSENGER TICKET NUMBER: " + ticketOwner(passengerList, ticketNumber));
                break;
            case 8:
                /* Q8. AVERAGE AGE */
                System.out.println("AVERAGE AGE: " + averageAge(passengerList));
                break;
            case 9:
                /* Q9. PASSENGER BY TICKET CLASS */
                System.out.println("Enter the class you want to check: ");
                String className = input.nextLine();
                switch (className.toLowerCase()) {
                    case "first":
                        System.out.println("PASSENGERS FROM FIRST CLASS : " + getPassengersByTicketClass(passengerList, PassengerClass.FIRST));
                        break;
                    case "second":
                        System.out.println("PASSENGERS FROM SECOND CLASS : " + getPassengersByTicketClass(passengerList, PassengerClass.SECOND));
                        break;
                    case "third":
                        System.out.println("PASSENGERS FROM THIRD CLASS : " + getPassengersByTicketClass(passengerList, PassengerClass.THIRD));
                        break;
                    default:
                        System.out.println("No Class Match");
                }
                break;
            case 10:
                /* Q10. SORTING BY ID */
                sortPassengersByPassengerId(passengerList);
                for (Passenger passenger : passengerList) {
                    System.out.println(passenger);
                }
                break;
            case 11:
                /* Q11. SORT PASSENGERS BY NAME */
                sortPassengersByName(passengerList);
                for (Passenger passenger : passengerList) {
                    System.out.println(passenger);
                }
                break;
            case 12:
                /* Q12. SORT PASSENGERS BY AGE AND NAME */
                List<Passenger> sortedByAgeThenName = sortPassengersByAgeThenName(passengerList);

                for (Passenger passenger : sortedByAgeThenName) {
                    System.out.println(passenger);
                }
                break;
            case 13:
                /* Q13. SORT PASSENGERS BY GENDER AND PASSENGER NUMBER */
                List<Passenger> sortedByGenderThenNumber = sortPassengersByGenderThenPassengerNumber(passengerList);

                for (Passenger passenger : sortedByGenderThenNumber) {
                    System.out.println(passenger);
                }
                break;
            case 14:
                /* Q14. SORTED BY FARE THEN SURVIVAL */
                List<Passenger> sortedByFareThenSurvival = sortPassengersByFareThenSurvival(passengerList);

                for (Passenger passenger : sortedByFareThenSurvival) {
                    System.out.println(passenger);
                }
                break;
            case 15:
                /* Q15. SORTED BY TICKET CLASS */
                List<Passenger> sortedByTicketClass = sortPassengersByTicketClass(passengerList);

                for (Passenger passenger : sortedByTicketClass) {
                    System.out.println(passenger);
                }
                break;
            case 16:
                /* Q16. SORTED BY AGE */
                List<Passenger> sortedByAge = sortPassengersByAge(passengerList);

                for (Passenger passenger : sortedByAge) {
                    System.out.println(passenger);
                }
                break;
            case 17:
                /* Q17. SORTED BY TICKET NUMBER LAMBDA */
                List<Passenger> sortedByTicketNumberLambda = sortPassengersByTicketNumberLambda(passengerList);

                for (Passenger passenger : sortedByTicketNumberLambda) {
                    System.out.println(passenger);
                }
                break;
            case 18:
                /* Q18. SORTED BY NUMBER STATIC  */
                List<Passenger> sortedByTicketNumberStatic = sortPassengersByTicketNumberStatic(passengerList);

                for (Passenger passenger : sortedByTicketNumberStatic) {
                    System.out.println(passenger);
                }
                break;
            case 19:
                /* Q19. FIND PASSENGER BY TICKET NUMBER */
                System.out.println("Enter the ticket number: ");
                String searchTicketNumber = input.nextLine();

                Collections.sort(passengerList, Passenger.TicketNumberComparator);

                Passenger targetPassengerByTicketNumber = new Passenger(null, searchTicketNumber);

                Passenger foundPassengerByTicketNumber = findPassengerByTicketNumber(passengerList, targetPassengerByTicketNumber);

                if (foundPassengerByTicketNumber != null) {
                    System.out.println("Passenger found! " + foundPassengerByTicketNumber);
                } else {
                    System.out.println("Passenger not found :(");
                }
                break;
            case 20:
                /* Q20. FIND PASSENGER BY ID */
                System.out.println("Enter the passenger ID: ");
                String passengerId = input.nextLine();

                Collections.sort(passengerList, Passenger.PassengerIdComparator);

                Passenger targetPassengerById = new Passenger(passengerId, null);

                Passenger foundPassengerById = findPassengerByPassengerId(passengerList, targetPassengerById);

                if (foundPassengerById != null) {
                    System.out.println("Passenger found! " + foundPassengerById);
                } else {
                    System.out.println("Passenger not found :(");
                }
            default:
                System.out.println("INVALID ENTRY...PLEASE TRY AGAIN");
                handleInput(input.nextInt(), passengerList);
                break;
        }
    }
}