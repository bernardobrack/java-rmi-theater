import java.rmi.Naming;  
import java.rmi.RemoteException;  
import java.rmi.NotBoundException;  
import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class TheaterClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9001);
            TheaterServerInterface server = (TheaterServerInterface) registry.lookup("theater");

            Scanner scanner = new Scanner(System.in);
            int number = 0;

            do {
                System.out.println("(LEAVE) Choose a different number to leave");
                System.out.print("(PICK) Choose a number between 1 and 20: ");
                number = scanner.nextInt();

                boolean available = server.isChairAvailable(number);

                if (available) {
                    boolean success = server.occupyChair(number);

                    if (success) {
                        System.out.println("Chair " + number + " occupied successfully.");
                    } else {
                        System.out.println("Chair " + number + " was already occupied.");
                    }
                } else {
                    System.out.println("Chair " + number + " is already occupied. Please choose a different number.");
                }
            } while (number <= 1 || number <= 20);
            System.out.println("THANKS! HAVE A GOOD ONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
