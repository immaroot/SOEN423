import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class RequestHandler implements Runnable {

    private IStudentServer server = null;
    private int portNumber;
    private Campus campusLocation;
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];

    public RequestHandler(IStudentServer server, int portNumber, Campus campusLocation) {
        this.server = server;
        this.portNumber = portNumber;
        this.campusLocation = campusLocation;
    }

    @Override
    public void run() {

        try {
            this.socket = new MulticastSocket(this.portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InetAddress group = null;
        try {
            group = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            this.socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String received = new String(packet.getData(), 0, packet.getLength());

                if (received.startsWith("REQUEST_COUNT")) {

                }
            }

    }
}
