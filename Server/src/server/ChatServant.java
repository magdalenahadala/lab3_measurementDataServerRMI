package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import interfaces.ICallback;
import interfaces.IChat;
import lab1.TimeHistory;
import lab1.Spectrum;

public class ChatServant extends UnicastRemoteObject implements IChat {
	//private Map<String, ICallback> people = new HashMap<>();
	private Map<String, ICallback> people = new HashMap<String, ICallback>();
	private Map<String, TimeHistory> THmap = new HashMap<String, TimeHistory>();
	private Map<String, Spectrum> Smap = new HashMap<String, Spectrum>();
	
	public ChatServant() throws RemoteException {
	}

	// Implements propagate() of IChat interface
	  public boolean propagate(String nick, String text) throws RemoteException {
		    System.out.println("Server.propagate(): " + text);
		    ICallback callback = people.get(nick);
		    if(callback != null) {
		    	callback.inform(nick, text);
				return true;
		    }
		    return false;
		  }

	// Implements register() of IChat interface
	public boolean register(String nick, ICallback n) throws RemoteException {
		System.out.println("Server.register(): " + nick);
		if (!people.containsKey(nick)) {
			people.put(nick, n);
			return true;
		}
		return false;
	}

	// Implements unregister() of IChat interface
	public boolean unregister(String nick) throws RemoteException {
		if (people.remove(nick) != null) {
			System.out.println("Server.unregister(): " + nick);
			return true;
		}
		return false;
	}
	//2 Metody implementujaca funkcje zapisz() interfejsu IChat
		 public boolean save(TimeHistory THdata) throws RemoteException {
		   System.out.println("Server.save(): " + THdata.device);
			if(THdata.device != null) {
				String key = THdata.device + THdata.channelNr;
				THmap.put(key, THdata);
				return true;
			}
		   return false;
		 }
		
		 public boolean save(Spectrum spectre) throws RemoteException {
			   System.out.println("Server.save(): " + spectre.device);
				if(spectre.device != null) {

					String key = spectre.device + spectre.channelNr;
					Smap.put(key, spectre);
					return true;
				}
			   return false;
			 }
	// Implements inform() of IChat interface
	public Vector<String> inform(String nick) throws RemoteException {
		Set<String> set = people.keySet();
		Vector<String> v = new Vector<String>();
		for (String s : set)
			if (s.matches(nick))
				v.add(s);
		return v;
	}
	
	public Vector<String> tHist(String device) throws RemoteException {
		Set<String> set = THmap.keySet();
		Vector<String> v = new Vector<String>();
		for(String s : set)
			if(s.matches(device))
				v.add((s));
		return v;
	}

	public Vector<String> Spectrum(String device) throws RemoteException {
		Set<String> set = Smap.keySet();
		Vector<String> v = new Vector<String>();
		for(String s : set)
			if(s.matches(device))
				v.add(s);
		return v;
	}
	public Vector<TimeHistory> writeTH(String device) throws RemoteException {
		Set<String> set = THmap.keySet();
		Vector<TimeHistory> v = new Vector<TimeHistory>();

		for(String s : set)
			if(s.contains(device))
				v.add(THmap.get(s));
		return v;
		
	}

	public Vector<Spectrum> writeS(String device) throws RemoteException {
		Set<String> set = THmap.keySet();
		Vector<Spectrum> v = new Vector<Spectrum>();

		for(String s : set)
			if(s.contains(device))
				v.add(Smap.get(s));
		return v;
		
	}


}