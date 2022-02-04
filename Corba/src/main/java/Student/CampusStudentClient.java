package Student;

import CampusServerApp.Campus;
import CampusServerApp.CampusServer;
import CampusServerApp.Date;
import CampusServerApp.TimeSlot;
import EnumHelper;
import Core.Logger;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Properties;

public class CampusStudentClient implements ICampusStudentClient {

    private String studentID;
    private Campus campusLocation;
    private Logger logger;
    private final CampusServer server;

    public CampusStudentClient(String studentID, String[] args) throws Exception {
        this.studentID = studentID;

        switch (studentID.substring(0, 3)) {
            case "KKL":
                this.campusLocation = Campus.KKL;
                break;
            case "DVL":
                this.campusLocation = Campus.DVL;
                break;
            case "WST":
                this.campusLocation = Campus.WST;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + studentID.substring(0, 3));
        }

        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", "1050");
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        ORB orb = ORB.init(args, props);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        String name = "server" + EnumHelper.getCampusValue(campusLocation);

        System.out.println("Trying to retrieve server called: " + name);

        server = CampusServerApp.CampusServerHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Client connected to server..");
    }

    @Override
    public String getAvailableTimeSlot(Date date) {
        return this.server.getAvailableTimeSlot(date, studentID);
    }

    @Override
    public String bookRoom(Campus campusName, int roomNumber, Date date, TimeSlot timeSlot) {
        return this.server.bookRoom(campusName, roomNumber, date, timeSlot, studentID);
    }

    @Override
    public String cancelBooking(String bookingID) {
        return this.server.cancelBooking(bookingID, studentID);
    }
}
