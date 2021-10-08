import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AdminClient {

    public static void main(String[] args) throws IOException {
        boolean validLogin, userExitRequest;
        String userName;

        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to the Admin DRRS Client!");

        do {
            validLogin = false;
            System.out.println("Please enter your Admin ID:");
            userName = reader.nextLine();

            if (userName.matches("(KKL|WST|DVL)A\\d{4}")) {
                validLogin = true;
            } else {
                System.out.println("Not a valid ID. Please try again.");
            }
        } while (!validLogin);

        CampusAdminClient client = null;

        try {
            client = new CampusAdminClient(userName);

        int userOption;

        do {
            userExitRequest = false;

            System.out.println("1- Create a room record.");
            System.out.println("2- Delete a room record.");
            System.out.println("3- Say hello!");
            System.out.println("0- Exit program");

            userOption = reader.nextInt();

            switch (userOption) {
                case 1:
                    requestCreateRoom(client);
                    break;
                case 2:
                    requestDeleteRoom();
                    break;
                case 3:
                    requestSayHello(client);
                    break;
                case 0:
                    userExitRequest = true;
                    break;
                default:
                    System.out.println("Please enter a valid option.");
            }
        } while(!userExitRequest);

        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    private static void requestSayHello(CampusAdminClient client) throws RemoteException {
        String message = client.sayHello();
        System.out.println(message);
    }

    private static void requestCreateRoom(CampusAdminClient client) throws RemoteException {
        Scanner reader = new Scanner(System.in);
        String dateInput, startTimeInput, endTimeInput;
        int roomNumber;
        int year = 0;
        int month = 0;
        int day = 0;
        int startHour = 0;
        int startMinute = 0;
        int endHour = 0;
        int endMinute = 0;

        Date date;
        TimeSlot timeSlot;

        System.out.println("Creating new time slot for a room:");

        System.out.print("Enter the room number:");
        roomNumber = reader.nextInt();

        do {
            System.out.print("Enter the date with format DD-MM-YYYY:");
            dateInput = reader.nextLine();
        } while (!extractedDateInput(dateInput, day, month, year));


        do {
            System.out.print("Enter start time of the time slot with format HH:MM :");
            startTimeInput = reader.nextLine();
        } while (!extractedTimeInput(startTimeInput, startHour, startMinute));

        do {
            System.out.print("Enter end time of the time slot with format HH:MM :");
            endTimeInput = reader.nextLine();
        } while (!extractedTimeInput(endTimeInput, endHour, endMinute));

        date = new Date(year, month, day);
        timeSlot = new TimeSlot(new Time(startHour, startMinute), new Time(endHour, endMinute));

        System.out.println();

        System.out.println("Attempting to create new room record....");

        Set<TimeSlot> timeSlotSet = new HashSet<>();
        timeSlotSet.add(timeSlot);
        String roomRecord = client.createRoom(roomNumber, date, timeSlotSet);


        System.out.println("Created room record fot room num: " + roomRecord );

    }

    private static void requestDeleteRoom() {
        Scanner reader = new Scanner(System.in);
        int roomNumber;
        int year;
        int month;
        int day;
        int startHour;
        int startMinute;
        int endHour;
        int endMinute;

        Date date;
        TimeSlot timeSlot;

        System.out.println("Deleting time slot for a room:");

        System.out.print("Enter the room number:");
        roomNumber = reader.nextInt();

        System.out.print("Enter the year of date:");
        year = reader.nextInt();

        System.out.print("Enter the month of date:");
        month = reader.nextInt();

        System.out.print("Enter the day of date:");
        day = reader.nextInt();

        System.out.print("Enter start hour of the time slot:");
        startHour = reader.nextInt();

        System.out.print("Enter start minute of the time slot:");
        startMinute = reader.nextInt();

        System.out.print("Enter end hour of the time slot:");
        endHour = reader.nextInt();

        System.out.print("Enter end minute of the time slot:");
        endMinute = reader.nextInt();

        date = new Date(year, month, day);
        timeSlot = new TimeSlot(new Time(startHour, startMinute), new Time(endHour, endMinute));

        System.out.println();

        System.out.println("Attempting to delete room record....");
    }

    private static boolean extractedTimeInput(String input, int hour, int min) {
        if (input.matches("\\d{2}:\\d{2}")) {
            hour   = Integer.parseInt(input.split(":")[0]);
            min = Integer.parseInt(input.split(":")[1]);
            return true;
        } else {
            return false;
        }
    }

    private static boolean extractedDateInput(String input, int day, int month, int year) {
        if (input.matches("\\d{2}-\\d{2}-\\d{4}")) {
            day = Integer.parseInt(input.split("-")[0]);
            month = Integer.parseInt(input.split("-")[1]);
            year = Integer.parseInt(input.split("-")[2]);
            return true;
        } else {
            return false;
        }
    }
}
