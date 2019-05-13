package rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        MathService mathService = new MathServiceImpl();
        /**
         * 如果MathServiceImpl 没有继承 UnicastRemoteObject
         */
//        MathService mathService2 = (MathService) UnicastRemoteObject.exportObject(mathService, 0);
        Registry registry = LocateRegistry.createRegistry(10086);
        registry.bind("mathService", mathService);
//        registry.bind("mathService", mathService2);
        System.out.println("服务端已启动");
    }
}
