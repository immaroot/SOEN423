package WS;


import jakarta.xml.ws.Endpoint;

public class CampusServerPublisher {

    public static void main(String[] args) {
        Endpoint ep = Endpoint.create(new CampusServerImpl());
        ep.publish("http://127.0.0.1:9000/campusServer");
    }
}
