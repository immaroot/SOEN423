package Admin;

import java.util.*;

import CampusServerApp.Date;
import CampusServerApp.TimeSlot;

public class AdminClient {

    public static void main(String[] args) {
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

        CampusAdminClient client;

        try {
            client = new CampusAdminClient(userName, args);

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void requestCreateRoom(CampusAdminClient client) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Creating new time slot for a room:");

        System.out.print("Enter the room number:");
        int roomNumber = reader.nextInt();

        String dateInput;
        do {
            System.out.print("Enter the date with format DD-MM-YYYY:");
            dateInput = reader.nextLine();
        } while (isNotDateFormat(dateInput));


        String startTimeInput;
        do {
            System.out.print("Enter start time of the time slot with format HH:MM :");
            startTimeInput = reader.nextLine();
        } while (isNotTimeFormat(startTimeInput));

        String endTimeInput;
        do {
            System.out.print("Enter end time of the time slot with format HH:MM :");
            endTimeInput = reader.nextLine();
        } while (isNotTimeFormat(endTimeInput));

        Date date = extractDate(dateInput);

        TimeSlot timeSlot = new CampusServerApp.TimeSlot(extractTime(startTimeInput), extractTime(endTimeInput));

        System.out.println();

        System.out.println("Attempting to create new room record....");

        TimeSlot[] timeSlots = {timeSlot};
        String roomRecord = client.createRoom((short) roomNumber, date, timeSlots);

        System.out.println("Created RoomRecord for room num: " + roomRecord);
    }

    private static void requestDeleteRoom(CampusAdminClient client) {
        Scanner reader = new Scanner(System.in);
        String dateInput, startTimeInput, endTimeInput;
        int roomNumber;
        Date date;
        TimeSlot timeSlot;

        System.out.println("Deleting a time slot for a room:");

        System.out.print("Enter the room number:");
        roomNumber = reader.nextInt();

        do {
            System.out.print("Enter the date with format DD-MM-YYYY:");
            dateInput = reader.nextLine();
        } while (isNotDateFormat(dateInput));


        do {
            System.out.print("Enter start time of the time slot with format HH:MM :");
            startTimeInput = reader.nextLine();
        } while (isNotTimeFormat(startTimeInput));

        do {
            System.out.print("Enter end time of the time slot with format HH:MM :");
            endTimeInput = reader.nextLine();
        } while (isNotTimeFormat(endTimeInput));

        date = extractDate(dateInput);

        timeSlot = new TimeSlot(extractTime(startTimeInput), extractTime(endTimeInput));

        System.out.println();

        System.out.println("Attempting to delete room record....");

        TimeSlot[] timeSlots = {timeSlot};
        String roomRecord = client.deleteRoom((short) roomNumber, date, timeSlots);

        System.out.println("RoomRecord deleted for room num: " + roomRecord);
    }


    private static boolean isNotTimeFormat(String input) {
        return !input.matches("\\d{2}:\\d{2}");
    }

    private static boolean isNotDateFormat(String input) {
        return !input.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    private static Date extractDate(String input) {
        int day = Integer.parseInt(input.split("-")[0]);
        int month = Integer.parseInt(input.split("-")[1]);
        int year = Integer.parseInt(input.split("-")[2]);
        return new Date((short) year, (short) month, (short) day);
    }

    private static CampusServerApp.Time extractTime(String input) {
        int hour   = Integer.parseInt(input.split(":")[0]);
        int min = Integer.parseInt(input.split(":")[1]);
        return new CampusServerApp.Time((short) hour, (short) min);
    }
}
