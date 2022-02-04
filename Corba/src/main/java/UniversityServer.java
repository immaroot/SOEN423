import CampusServerApp.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UniversityServer {

    public static void main(String[] args) {

        try {

            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");

            ORB orb = ORB.init(args, props);
            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            List<CampusServer> serverList = new ArrayList<>();
            serverList.add(new CampusServer(Campus.KKL));
            serverList.add(new CampusServer(Campus.WST));
            serverList.add(new CampusServer(Campus.DVL));

            for (CampusServer server : serverList) {

                new Thread(server).start();

                server.setOrb(orb);
                org.omg.CORBA.Object ref = rootPoa.servant_to_reference(server);
                CampusServerApp.CampusServer href = CampusServerHelper.narrow(ref);

                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

                String name = "server" + EnumHelper.getCampusValue(server.campusLocation);

                System.out.println("Setting up " + name + " server");

                NameComponent[] path = ncRef.to_name(name);
                ncRef.rebind(path, href);

                System.out.println("Server " + name + " is waiting...");

                TimeSlot timeSlot1 = new TimeSlot(new Time(8,0), new Time(9,0));
                TimeSlot timeSlot2 = new TimeSlot(new Time(9,0), new Time(10,0));
                TimeSlot[] timeSlots = new TimeSlot[]{timeSlot1, timeSlot2};

                server.createRoom(101, new Date(1000,10, 10), timeSlots, EnumHelper.getCampusName(server.campusLocation) + "A1234");



            }

            orb.run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
