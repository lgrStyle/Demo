package rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MathService extends Remote{

    User getUser(String name, int age) throws RemoteException;
}
