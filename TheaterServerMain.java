import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TheaterServerMain {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(9001);
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            TheaterServerImpl theater = new TheaterServerImpl();
            TheaterServerInterface stub = (TheaterServerInterface) UnicastRemoteObject.exportObject(theater, 0);
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9001);
            registry.bind("theater", stub);
            System.out.println("Number game server is running.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
