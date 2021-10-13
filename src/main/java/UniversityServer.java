import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class UniversityServer {


    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

        int RMIPortNum = 1234;

        startRegistry(RMIPortNum);

        Registry registry = LocateRegistry.getRegistry(RMIPortNum);

        String URI = "rmi://localhost:" + RMIPortNum + "/";

        CampusServer kkl = new CampusServer(Campus.KKL);
        registry.bind(URI + "KKL", kkl);
        new Thread(kkl).start();

        CampusServer wst = new CampusServer(Campus.WST);
        registry.bind(URI + "WST", wst);
        new Thread(wst).start();

        CampusServer dvl = new CampusServer(Campus.DVL);
        registry.bind(URI + "DVL", dvl);
        new Thread(dvl).start();

        System.out.println(Arrays.toString(registry.list()));

    }

    private static void startRegistry(int RMIPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
        } catch (RemoteException e) {
            System.out.println("RMI registry cannot be located at port " + RMIPortNum);
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
            System.out.println("RMI registry created at port " + RMIPortNum);
        }
    }
}
