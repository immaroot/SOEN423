import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class StudentClient {

    public static void main(String[] args) throws IOException {

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
            client = new CampusStudentClient(userName);

            int userOption;

            do {
                userExitRequest = false;

                System.out.println("1- Get available timeslots");
                System.out.println("0- Exit program");

                userOption = reader.nextInt();

                switch (userOption) {
                    case 1:
                        System.out.println(client.getAvailableTimeSlot(new Date(1000,10,10)));
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
}
