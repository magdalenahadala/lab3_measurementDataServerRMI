package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
Registry reg; //rejestr nazw obiekt�w
Servant servant;

public static void main(String[] args) {
	try {
		Server s = new Server();
	}
	catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
}

protected Server() throws RemoteException {
	try {
		//utworzenie rejestru nazw 
		reg = LocateRegistry.createRegistry(1099);
		//utworzenie obiektu dost�pnego zdalnie
		servant = new Servant();
		// zwiazanie nazwy z obiektem
		reg.rebind("Server", (Remote) servant);
		System.out.println("Server is ready...");
	}
	catch(RemoteException e) {
		e.printStackTrace();
		throw e;
	}
}
}