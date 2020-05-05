package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICallback extends Remote {
	public void inform(String nick, String text) throws RemoteException;
}
