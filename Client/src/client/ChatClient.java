package client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import interfaces.ICallback;
import interfaces.IChat;
import lab1.Spectrum;
import lab1.TimeHistory;
import java.util.Date;
import java.util.Scanner;

public class ChatClient {
	private Scanner userInput = new Scanner(System.in);
	String username;
	IChat remoteObject; // a remote object reference
	ICallback callback;

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1) {
			System.out.println("Usage: ChatClient <server host name>");
			System.exit(-1);
		}
		new ChatClient(args[0]);
	}

	public ChatClient(String hostname) throws FileNotFoundException {
		System.out.println("Enter client name: ");
		if (userInput.hasNextLine())
			username = userInput.nextLine();
		Registry reg; // the remote objects registry
		try {
			// getting a reference to the object names registry
			reg = LocateRegistry.getRegistry(hostname);
			// find a remote object by its name
			remoteObject = (IChat) reg.lookup("ChatServer");
			callback = new ClientCallback();
			// calling methods of a remote object
			remoteObject.register(username, callback);
			loop();
			remoteObject.unregister(username);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	void inform() {
		String line;
		System.out.print("Enter the user name filter regular expression: ");
		if (userInput.hasNextLine()) {
			line = userInput.nextLine();
			Vector<String> vec = null;
			try {
				vec = remoteObject.inform(line);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			System.out.println("There are " + vec.size() + " user(s):");
			for (String s : vec)
				System.out.println(" - " + s);
		}
	}

	void saveTH() {
		Integer[] tab1 = {1, 2, 3, 4, 5, 6, 7};
		Double[] tab2 = {1.1, 2.1, 3.2, 4.3, 5.4};
		TimeHistory ramka = new TimeHistory("device1", "description1", 2020, 1, "unit1", 15.6, tab1 , 10.20);
		System.out.print("Please fill in the informations: \n");
		System.out.print("Device: ");
		if (userInput.hasNextLine()) {
			ramka.device = userInput.nextLine();
		}
		System.out.print("Description: ");
		if (userInput.hasNextLine()) {
			ramka.description = userInput.nextLine();
		}
		System.out.print("Date: ");
		if (userInput.hasNextLine()) {
			ramka.date = Long.parseLong(userInput.nextLine());
		}
		System.out.print("ChannelNr: ");
		if (userInput.hasNextLine()) {
			ramka.channelNr = Integer.parseInt(userInput.nextLine());
		}
		System.out.print("Unit: ");
		if (userInput.hasNextLine()) {
			ramka.unit = userInput.nextLine();
		}
		System.out.print("Resolution: ");
		if (userInput.hasNextLine()) {
			ramka.resolution = Double.parseDouble(userInput.nextLine());
		}
		System.out.print("Sensitivity: ");
		if (userInput.hasNextLine()) {
			ramka.sensitivity = Double.parseDouble(userInput.nextLine());
		}
		
		try {
			remoteObject.save(ramka);
		} catch (RemoteException e) { e.printStackTrace(); }
	}

	
	void saveS() {
		Object[] tab2 = null;
		//Double[] tab2 = {1.1, 2.1, 3.2, 4.3, 5.4};
		Spectrum ramka = new Spectrum("device2", "description2", 2020, 1, "unit2", 15.6, tab2 , 1);
		System.out.print("Please fill in the informations: \n");
		System.out.print("Device: ");
		if (userInput.hasNextLine()) {
			ramka.device = userInput.nextLine();
		}
		System.out.print("Description: ");
		if (userInput.hasNextLine()) {
			ramka.description = userInput.nextLine();
		}
		System.out.print("Date: ");
		if (userInput.hasNextLine()) {
			ramka.date = Long.parseLong(userInput.nextLine());
		}
		System.out.print("ChannelNr: ");
		if (userInput.hasNextLine()) {
			ramka.channelNr = Integer.parseInt(userInput.nextLine());
		}
		System.out.print("Unit: ");
		if (userInput.hasNextLine()) {
			ramka.unit = userInput.nextLine();
		}
		System.out.print("Resolution: ");
		if (userInput.hasNextLine()) {
			ramka.resolution =  Double.parseDouble(userInput.nextLine());
		}
		System.out.print("Scale: ");
		if (userInput.hasNextLine()) {
			ramka.scailing = Integer.parseInt(userInput.nextLine());
		}
		try {
			remoteObject.save(ramka);
		} catch (RemoteException e) { e.printStackTrace(); }
	}
	
	// funkcja zapisujaca dane do pliku
		public static void zapis(String string, String plik) throws FileNotFoundException {	
		PrintWriter zapis = new PrintWriter(plik);
		String newLine = System.getProperty("line.separator");
		 
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		 
		 zapis.println("Date (" + dateFormat.format(date) + "): " + string + newLine);
		 zapis.close();
		 }
		
		 
		void tHist() throws FileNotFoundException {
			String data;
			String line = null;
			Integer[] tab1 = {1, 2, 3, 4, 5, 6, 7};
			Double[] tab2 = {1.1, 2.1, 3.2, 4.3, 5.4};
			TimeHistory TimeHist = new TimeHistory("device1", "description1", 2020, 1, "unit1", 15.6, tab1 , 10.20);
			data=TimeHist.toString();
			System.out.println("Time History is: " + data);
			System.out.println("Do you want to save obtained data in TimeHistory.txt?" + "\n" + "y/n");
			line=userInput.nextLine();
			if(line.matches("[y]")) {
				System.out.println("TimeHistory data is saved");	
				zapis("Time History: " + data, "TimeHistory.txt");
			}
			else if (line.matches("[n]")) {
				System.out.println("back");
			}
			else {
				System.out.println("Wrong command");
			}
		}

		
		
		void Spectrum() throws FileNotFoundException {
			String data;
			String line = null;
			Double[] tab2 = {1.1, 2.1, 3.2, 4.3, 5.4};
			Spectrum Spec = new Spectrum("device2", "description2", 2020, 1, "unit2", 15.6, tab2 , 1);
			data=Spec.toString();
			System.out.println("Spectrum is: " + data);
			System.out.println("Do you want to save obtained data in Spectrum.txt" + "\n" + "y/n");
			line=userInput.nextLine();
			if(line.matches("[y]")) {
				System.out.println("Spectrum data is saved");	
				zapis("Spectrum: " + data, "Spectrum.txt");
			}
			else if (line.matches("[n]")) {
				System.out.println("back");
			}
			else {
				System.out.println("Wrong command");
			}
		}
	
		 
		void writeTH() throws FileNotFoundException {
			String line;
			System.out.print("Device name? :  ");
			if(userInput.hasNextLine()) {
				line = userInput.nextLine();
				Vector<TimeHistory> vec = null;
				try {
					vec = remoteObject.writeTH(line);
				} catch (RemoteException e) { e.printStackTrace(); }
				System.out.println("There are " + vec.size() + " user(s):");
				for (TimeHistory s : vec)
					System.out.println(" - " + s);
				String data = vec.toString();
				System.out.println("Time History is: " + data);
				System.out.println("Do you want to save obtained data in TimeHistory_new.txt?" + "\n" + "y/n");
				line=userInput.nextLine();
				if(line.matches("[y]")) {
					System.out.println("TimeHistory data is saved");	
					zapis("Time History: " + data, "TimeHistory_new.txt");
				}
				else if (line.matches("[n]")) {
					System.out.println("back");
				}
				else {
					System.out.println("Wrong command");
				}
				
			}
		}
		
		
		
		
		
		void writeS() throws FileNotFoundException {
			String line;
			System.out.print("Device name? :  ");
			if(userInput.hasNextLine()) {
				line = userInput.nextLine();
				Vector<Spectrum> vec = null;
				try {
					vec = remoteObject.writeS(line);
				} catch (RemoteException e) { e.printStackTrace(); }
				System.out.println("There are " + vec.size() + " user(s):");
				for (Spectrum s : vec)
					System.out.println(" - " + s);
				String data = vec.toString();
				System.out.println("Spectrum is: " + data);
				System.out.println("Do you want to save obtained data in Spectrum_new.txt?" + "\n" + "y/n");
				line=userInput.nextLine();
				if(line.matches("[y]")) {
					System.out.println("Spectrum data is saved");	
					zapis("Spectrum: " + data, "Spectrum_new.txt");
				}
				else if (line.matches("[n]")) {
					System.out.println("back");
				}
				else {
					System.out.println("Wrong command");
				}
				
			}
		}
				
				
				
				
	
	private void propagate() {
		String username;
		System.out.print("Enter the name of addressee: ");
		if (userInput.hasNextLine()) {
			username = userInput.nextLine();
			String utext;
			System.out.print("Enter the text you want to send: ");
			if (userInput.hasNextLine()) {
				utext = userInput.nextLine();
				try {
					remoteObject.propagate(username, utext);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String prompt() {
		return "[" + username + "] > ";
	}

	Object loop() throws FileNotFoundException {
		while (true) {
			String line;
			System.out.println("Choose what do you want to do: ");
			System.out.println(prompt() + "[i]nform, [c]omunicate, [n]ew data, [o]ld data, [l]og out");
			if (userInput.hasNextLine()) {
				line = userInput.nextLine();
				if (!line.matches("[icnol]")) {
					System.out.println("You entered invalid command !");
					continue;
				}
				switch (line) {
				case "i":
					inform();
					return loop();
				case "c":
					propagate();
					return loop();
				case "n":
					System.out.println("What do you want to do with the new data set?");
					System.out.println("[s]ave TimeHistory, s[a]ve Spectrum, [w]read TimeHistory info, [r]read Spectrum info, [b]ack");
					if(userInput.hasNextLine()) {
						line = userInput.nextLine();
						if(!line.matches("[sawrb]")) {
							System.out.println("You entered an invalid command !");
							continue;
						}
						switch (line) {
							case "s":
								saveTH();
								//tHist();
								return loop();
							case "a":
								saveS();
								//Spectrum();
								return loop();
							case "w":
								writeTH();
								return loop();
							case "r":
								writeS();
								return loop();
							case "b":
								System.out.println("back");			
								return loop();
						}	
					}
				case "o":
					System.out.println("Which old data set do you want to see?");
					System.out.println("[T]imeHistory, [S]pectrum, [b]ack ");
					if(userInput.hasNextLine()) {
						line = userInput.nextLine();
						if(!line.matches("[TSb]")) {
							System.out.println("You entered an invalid command !");
							continue;
						}
						switch (line) {
							case "T":
								tHist();
								return loop();
							case "S":
								Spectrum();
								return loop();
							case "b":
								System.out.println("back");			
								return loop();
						}
					}
				case "l":
					return 0;
					
				}
			}
		}
	}
}
