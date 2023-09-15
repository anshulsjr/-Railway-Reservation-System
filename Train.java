import java.util.ArrayList;
import java.util.Scanner;

class Train {
    private String trainName;
    private String time;
    private int passengerStrength;
    private int trainNumber;

    public Train(String trainName, String time, int passengerStrength, int trainNumber) {
        this.trainName = trainName;
        this.time = time;
        this.passengerStrength = passengerStrength;
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTime() {
        return time;
    }

    public int getPassengerStrength() {
        return passengerStrength;
    }

    public int getTrainNumber() {
        return trainNumber;
    }
}

class ReservationSystem {
    private ArrayList<Train> trains;
    private ArrayList<String> bookedSeats;

    public ReservationSystem() {
        trains = new ArrayList<>();
        bookedSeats = new ArrayList<>();

        // Initialize predefined train data
        trains.add(new Train("Mumbai - Delhi", "13:05", 50, 1010));
        trains.add(new Train("Delhi - Jaipur", "7:00", 50, 2013));
        trains.add(new Train("Prayagraj - Delhi", "10:00", 50, 3045));
    }

    public void displayAvailableTrains() {
        System.out.println("Available Trains:");
        System.out.println("Train Number\tTrain Name\tDeparture Time\tPassenger Strength");

        for (Train train : trains) {
            System.out.printf("%d\t%s\t%s\t%d\n", train.getTrainNumber(), train.getTrainName(), train.getTime(), train.getPassengerStrength());
        }
    }

    public boolean checkSeatAvailability(int trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                int availableSeats = train.getPassengerStrength() - bookedSeats.size();
                System.out.println("Available Seats: " + availableSeats);
                return true;
            }
        }
        System.out.println("Invalid Train Number.");
        return false;
    }

    public void bookSeat(int trainNumber, String passengerName) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                if (bookedSeats.size() < train.getPassengerStrength()) {
                    bookedSeats.add(passengerName);
                    System.out.println("Seat booked successfully.");
                } else {
                    System.out.println("No available seats on this train.");
                }
                return;
            }
        }
        System.out.println("Invalid Train Number.");
    }

    public void cancelSeat(String passengerName) {
        if (bookedSeats.remove(passengerName)) {
            System.out.println("Seat canceled successfully.");
        } else {
            System.out.println("Passenger not found in the booked seats.");
        }
    }

    public void displayBookedTickets() {
        System.out.println("Booked Tickets:");
        for (String passenger : bookedSeats) {
            System.out.println(passenger);
        }
    }

    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nRailway Reservation System");
            System.out.println("1. Display Available Trains");
            System.out.println("2. Check Seat Availability");
            System.out.println("3. Book a Seat");
            System.out.println("4. Cancel a Seat");
            System.out.println("5. Display Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableTrains();
                    break;
                case 2:
                    System.out.print("Enter Train Number: ");
                    int trainNumber = scanner.nextInt();
                    reservationSystem.checkSeatAvailability(trainNumber);
                    break;
                case 3:
                    System.out.print("Enter Train Number: ");
                    trainNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Passenger Name: ");
                    String passengerName = scanner.nextLine();
                    reservationSystem.bookSeat(trainNumber, passengerName);
                    break;
                case 4:
                    System.out.print("Enter Passenger Name: ");
                    passengerName = scanner.nextLine();
                    reservationSystem.cancelSeat(passengerName);
                    break;
                case 5:
                    reservationSystem.displayBookedTickets();
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
