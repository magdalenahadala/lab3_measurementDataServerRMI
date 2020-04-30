package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ICallback;

public class ClientCallback 
    extends UnicastRemoteObject 
    implements ICallback {
	public ClientCallback() throws RemoteException {
		super();
	}
	public void komunikuj(String nick, String text) throws RemoteException {
		System.out.println("\r\n" + "message received: " + text);   
	}	

	
}