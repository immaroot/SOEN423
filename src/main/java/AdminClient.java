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
            System.out.println("0- Exit program");

            userOption = reader.nextInt();

            switch (userOption) {
                case 1:
                    requestCreateRoom(client);
                    break;
                case 2:
                    requestDeleteRoom(client);
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

    private static void requestCreateRoom(CampusAdminClient client) throws RemoteException {
        Scanner reader;
        reader = new Scanner(System.in);
        String dateInput;
        String startTimeInput;
        String endTimeInput;
        Date date;
        TimeSlot timeSlot;

        System.out.println("Creating new time slot for a room:");

        System.out.print("Enter the room number:");
        int roomNumber = reader.nextInt();

        do {
            System.out.print("Enter the date with format DD-MM-YYYY:");
            dateInput = reader.nextLine();
        } while (!isDateFormat(dateInput));


        do {
            System.out.print("Enter start time of the time slot with format HH:MM :");
            startTimeInput = reader.nextLine();
        } while (!isTimeFormat(startTimeInput));

        do {
            System.out.print("Enter end time of the time slot with format HH:MM :");
            endTimeInput = reader.nextLine();
        } while (!isTimeFormat(endTimeInput));

        date = extractDate(dateInput);

        timeSlot = new TimeSlot(extractTime(startTimeInput), extractTime(endTimeInput));

        System.out.println();

        System.out.println("Attempting to create new room record....");

        Set<TimeSlot> timeSlotSet = new HashSet<>();
        timeSlotSet.add(timeSlot);
        String roomRecord = client.createRoom(roomNumber, date, timeSlotSet);

        System.out.println("Created RoomRecord for room num: " + roomRecord );
    }

    private static void requestDeleteRoom(CampusAdminClient client) throws RemoteException {
        Scanner reader = new Scanner(System.in);
        String dateInput, startTimeInput, endTimeInput;
        int roomNumber;
        Date date;
        TimeSlot timeSlot;

        System.out.println("Creating new time slot for a room:");

        System.out.print("Enter the room number:");
        roomNumber = reader.nextInt();

        do {
            System.out.print("Enter the date with format DD-MM-YYYY:");
            dateInput = reader.nextLine();
        } while (!isDateFormat(dateInput));


        do {
            System.out.print("Enter start time of the time slot with format HH:MM :");
            startTimeInput = reader.nextLine();
        } while (!isTimeFormat(startTimeInput));

        do {
            System.out.print("Enter end time of the time slot with format HH:MM :");
            endTimeInput = reader.nextLine();
        } while (!isTimeFormat(endTimeInput));

        date = extractDate(dateInput);

        timeSlot = new TimeSlot(extractTime(startTimeInput), extractTime(endTimeInput));

        System.out.println();

        System.out.println("Attempting to create new room record....");

        Set<TimeSlot> timeSlotSet = new HashSet<>();
        timeSlotSet.add(timeSlot);
        String roomRecord = client.deleteRoom(roomNumber, date, timeSlotSet);

        System.out.println("RoomRecord deleted for room num: " + roomRecord);
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

    private static boolean isTimeFormat(String input) {
        return input.matches("\\d{2}:\\d{2}");
    }

    private static boolean isDateFormat(String input) {
        return input.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    private static Date extractDate(String input) {
        int day = Integer.parseInt(input.split("-")[0]);
        int month = Integer.parseInt(input.split("-")[1]);
        int year = Integer.parseInt(input.split("-")[2]);
        return new Date(year, month, day);
    }

    private static Time extractTime(String input) {
        int hour   = Integer.parseInt(input.split(":")[0]);
        int min = Integer.parseInt(input.split(":")[1]);
        return new Time(hour, min);
    }
}
