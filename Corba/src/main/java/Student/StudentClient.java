package Student;

import CampusServerApp.Campus;
import CampusServerApp.Date;
import CampusServerApp.TimeSlot;
import EnumHelper;

import java.util.HashMap;
import java.util.Scanner;

public class StudentClient {

    public static void main(String[] args) {

        boolean validLogin, userExitRequest;
        String userName;

        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to the Student DRRS Client!");

        do {
            validLogin = false;
            System.out.println("Please enter your Student ID:");
            userName = reader.nextLine();

            if (userName.matches("(KKL|WST|DVL)\\d{4}")) {
                validLogin = true;
            } else {
                System.out.println("Not a valid ID. Please try again.");
            }
        } while (!validLogin);

        CampusStudentClient client = null;

        try {
            client = new CampusStudentClient(userName, args);

            int userOption;

            do {
                userExitRequest = false;

                System.out.println("1- Get available timeslots");
                System.out.println("2- Book a room");
                System.out.println("3- Cancel a reservation");
                System.out.println("0- Exit program");

                userOption = reader.nextInt();

                switch (userOption) {
                    case 1:
                        requestAvailableTimeSlot(client);
                        break;
                    case 2:
                        requestBookRoom(client);
                        break;
                    case 3:
                        requestCancelReservation(client);
                        break;
                    case 0:
                        userExitRequest = true;
                        break;
                    default:
                        System.out.println("Please enter a valid option.");
                }
            } while(!userExitRequest);

        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    private static void requestAvailableTimeSlot(CampusStudentClient client) {

        Scanner reader;
        reader = new Scanner(System.in);
        String dateInput;
        do {
            System.out.print("Enter the date with format DD-MM-YYYY:");
            dateInput = reader.nextLine();
        } while (isNotDateFormat(dateInput));

        Date date = extractDate(dateInput);
        System.out.println(client.getAvailableTimeSlot(date));
    }

    private static void requestCancelReservation(CampusStudentClient client) {
        Scanner reader;
        reader = new Scanner(System.in);

        System.out.println("Please enter the booking ID you want to cancel:");
        String bookingID = reader.next();

        System.out.println();

        System.out.println("Attempting to cancel reservation...");

        String message = client.cancelBooking(bookingID);

        System.out.println(message);
    }

    private static void requestBookRoom(CampusStudentClient client) {
        Scanner reader;
        reader = new Scanner(System.in);
        String dateInput;
        String startTimeInput;
        String endTimeInput;
        Date date;
        TimeSlot timeSlot;

        System.out.println("Please select which campus would you like to book a room for:");
        HashMap<Integer, Campus> campusIntegerHashMap = new HashMap<>();
        int campusOptions = 1;
        for (Core.Campus campus : Core.Campus.values()) {
            campusIntegerHashMap.put(campusOptions, EnumHelper.getCampus(campus.getIndex()));
            System.out.println(campusOptions + "- " + campus);
            campusOptions++;
        }

        int optionInput = reader.nextInt();

        System.out.print("Enter the room number:");
        int roomNumber = reader.nextInt();

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

        System.out.println("Attempting to make a new booking....");

        String bookingID = client.bookRoom(campusIntegerHashMap.get(optionInput),  roomNumber, date, timeSlot);

        System.out.println("Created a reservation, here is your booking ID: " + bookingID);
    }

    private static boolean isNotTimeFormat(String input) {
        return !input.matches("\\d{2}:\\d{2}");
    }

    private static boolean isNotDateFormat(String input) {
        return !input.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    private static CampusServerApp.Date extractDate(String input) {
        int day = Integer.parseInt(input.split("-")[0]);
        int month = Integer.parseInt(input.split("-")[1]);
        int year = Integer.parseInt(input.split("-")[2]);
        return new CampusServerApp.Date( year,  month,  day);
    }

    private static CampusServerApp.Time extractTime(String input) {
        int hour   = Integer.parseInt(input.split(":")[0]);
        int min = Integer.parseInt(input.split(":")[1]);
        return new CampusServerApp.Time( hour,  min);
    }
}
