package interfaces;

import java.rmi.*;
import java.util.Vector;
import lab1.TimeHistory;
import lab1.Spectrum;

public interface IChat extends Remote {
   boolean register(String nick, ICallback n) throws RemoteException;
   boolean unregister(String nick) throws RemoteException;
   boolean save(TimeHistory ramka) throws RemoteException;
   boolean save(Spectrum ramka) throws RemoteException;
   boolean propagate(String nick, String message) throws RemoteException;
   Vector<String> tHist(String line) throws RemoteException;
   Vector<String> Spectrum(String line) throws RemoteException;
   Vector<String> inform(String nick) throws RemoteException;
   Vector<TimeHistory> writeTH(String device) throws RemoteException;
   Vector<Spectrum> writeS(String device) throws RemoteException;
};