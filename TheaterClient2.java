import java.rmi.Naming;  
import java.rmi.RemoteException;  
import java.rmi.NotBoundException;  
import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class TheaterClient2 {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9001);
            TheaterServerInterface server = (TheaterServerInterface) registry.lookup("theater");

            Scanner scanner = new Scanner(System.in);
            int number = 0;
            while(true) {
                System.out.println("1 - SEE IF A CHAIR IS AVAILABLE\n2 - OCCUPY A CHAIR\nANY OTHER NUMBER TO LEAVE\nWhich operation would you like the server to do ?");
                number = scanner.nextInt();
                if (number == 1) {
                    System.out.println("--------------------------------------");
                    for (int i = 1; i <= 20; i++) {
                        if (server.isChairAvailable(i)) {
                            System.out.println("Chair " + i + " is availabe");
                        } else {
                            System.out.println("Chair " + i + " is NOT availabe");
                        }
                    }
                    System.out.println("--------------------------------------");
                    continue;
                }
                if (number == 2) {
                    System.out.println("From 1 to 20 which chair would you like to occupy?");
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
                    continue;
                }
                break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
