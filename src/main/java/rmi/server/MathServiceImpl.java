package rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MathServiceImpl extends UnicastRemoteObject implements MathService{

    /**
     * 
     */
    private static final long serialVersionUID = -7580475938530444430L;

    protected MathServiceImpl() throws RemoteException {
        super();
        
    }

    @Override
    public User getUser(String name, int age) throws RemoteException{
        User user = new User(name, age);
        return user;
    }

}
