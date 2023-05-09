import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TheaterServerInterface extends Remote {
    public boolean isChairAvailable(int number) throws RemoteException;
    public boolean occupyChair(int number) throws RemoteException;

}