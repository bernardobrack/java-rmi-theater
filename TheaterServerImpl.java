import java.rmi.RemoteException;  
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;  
import java.rmi.Remote;

public class TheaterServerImpl implements TheaterServerInterface {
    private boolean[] numbers = new boolean[20];

    public TheaterServerImpl() throws RemoteException{
        Arrays.fill(numbers, true);
    }

    public synchronized boolean isChairAvailable(int number) throws RemoteException {
        if (number < 1 || number > 20) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }

        return numbers[number - 1];
    }

    public synchronized boolean occupyChair(int number) throws RemoteException {
        if (number < 1 || number > 20) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }

        if (numbers[number - 1]) {
            numbers[number - 1] = false;
            return true;
        } else {
            return false;
        }
    }
}
