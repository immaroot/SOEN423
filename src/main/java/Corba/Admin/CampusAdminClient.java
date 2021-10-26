package Corba.Admin;

import Corba.CampusServerApp.CampusServer;
import Corba.CampusServerApp.Date;
import Corba.CampusServerApp.TimeSlot;
import Corba.CampusServerApp.Campus;
import Core.Logger;
import org.omg.CORBA.ORB;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


import java.util.Properties;

public class CampusAdminClient implements ICampusAdminClient {

    private String adminID;
    private Campus campusLocation;
    private Logger logger;
    private final CampusServer server;

    public CampusAdminClient(String adminID, String[] args) throws Exception {

        this.adminID = adminID;
        switch (adminID.substring(0, 3)) {
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
                throw new IllegalStateException("Unexpected value: " + adminID.substring(0, 3));
        }

        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", "1050");
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        ORB orb = ORB.init(args, props);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        String name = "server" + campusLocation.value();

        server = Corba.CampusServerApp.CampusServerHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Client connected to server..");
    }

    @Override
    public String createRoom(short roomNumber, Date date, TimeSlot[] timeSlotSet) {
        return this.server.createRoom(roomNumber, date, timeSlotSet, adminID);
    }

    @Override
    public String deleteRoom(short roomNumber, Date date, TimeSlot[] timeSlotSet) {
        return this.server.deleteRoom(roomNumber, date, timeSlotSet, adminID);
    }
}
