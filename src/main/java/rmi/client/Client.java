package rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.server.MathService;
import rmi.server.User;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 10086);
        MathService mathService = (MathService) registry.lookup("mathService");
        User user = mathService.getUser("黎明", 2);
        System.out.println(user);
    }
}
