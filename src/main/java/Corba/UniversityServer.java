package Corba;

import Corba.CampusServerApp.Campus;
import Corba.CampusServerApp.CampusServerHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.ArrayList;
import java.util.List;

public class UniversityServer {

    public static void main(String[] args) {

        try {

            ORB orb = ORB.init(args, null);
            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            List<CampusServer> serverList = new ArrayList<>();
            serverList.add(new CampusServer(Campus.KKL));
            serverList.add(new CampusServer(Campus.WST));
            serverList.add(new CampusServer(Campus.DVL));

            for (CampusServer server : serverList) {
                server.setOrb(orb);
                org.omg.CORBA.Object ref = rootPoa.servant_to_reference(server);
                Corba.CampusServerApp.CampusServer href = CampusServerHelper.narrow(ref);

                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

                String name = "server" + server.campusLocation.value();

                System.out.println("Settting up " + name + " server");

                NameComponent[] path = ncRef.to_name(name);
                ncRef.rebind(path, href);

                System.out.println("Server " + name + " is waiting...");

            }

            orb.run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
