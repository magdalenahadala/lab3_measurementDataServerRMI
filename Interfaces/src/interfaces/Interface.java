package interfaces;

import java.rmi.*;
import java.util.Vector;

import lab1.TimeHistory;
import lab1.Spectrum;

public interface Interface extends Remote {
	   boolean zarejestruj(String nick, ICallback n) throws RemoteException;
	   boolean wyrejestruj(String nick) throws RemoteException;
	   boolean zapisz(TimeHistory ramka) throws RemoteException;
	   boolean zapisz(Spectrum ramka) throws RemoteException;
	   boolean komunikuj(String nick, String message) throws RemoteException;
	   Vector<String> tHist(String line) throws RemoteException;
	   Vector<String> Spectrum(String line) throws RemoteException;
	   Vector<String> informuj(String nick) throws RemoteException;
	   Vector<TimeHistory> wypiszTH(String device) throws RemoteException;
	   Vector<Spectrum> wypiszS(String device) throws RemoteException;
	}