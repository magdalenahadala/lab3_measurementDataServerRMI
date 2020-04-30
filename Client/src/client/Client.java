package client;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import interfaces.Interface;
import lab1.TimeHistory;
import lab1.Spectrum;
import java.util.Date;
import java.util.Scanner;


public class Client {
	private Scanner userInput = new Scanner(System.in);
	String username;
	Interface remoteObject; // referencja do zdalnego obiektu
	ICallback callback;
	public static void main(String[] args) throws FileNotFoundException {
	  	if(args.length < 1) {
			System.out.println("Usage: Client <server host name>");
			System.exit(-1);
		}
		new Client(args[0]);
    }

	public Client(String hostname) throws FileNotFoundException {
		System.out.println("Login using your name:  ");
		if(userInput.hasNextLine())
			username = userInput.nextLine();
		Registry reg;    // rejestr nazw obiektow
		try {
			// pobranie referencji do rejestru nazw obiektow
			reg = LocateRegistry.getRegistry(hostname);
			// odszukanie zdalnego obiektu po jego nazwie
			remoteObject = (Interface) reg.lookup("Server");
			callback = new ClientCallback();
			// wywolanie metod zdalnego obiektu
			remoteObject.zarejestruj(username, callback);
			loop();
			remoteObject.wyrejestruj(username);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	
	void informuj() {
		String line;
		System.out.print("Enter the username you are looking for ");
		if(userInput.hasNextLine()) {
			line = userInput.nextLine();
			Vector<String> vec = null;
			try {
				vec = remoteObject.informuj(line);
			} catch (RemoteException e) { e.printStackTrace(); }
			System.out.println("There are " + vec.size() + " user(s):");
			for (String s : vec)
				System.out.println(" - " + s);
		}
	}
	
	void zapiszTH() {
		Integer[] tab1 = {1, 2, 3, 4, 5, 6, 7};
		Double[] tab2 = {1.1, 2.1, 3.2, 4.3, 5.4};
		TimeHistory ramka = new TimeHistory("device1", "description1", 2020, 1, "unit1", 15.6, tab1 , 10.20);
		System.out.print("Please fill in the informations: ");
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
			remoteObject.zapisz(ramka);
		} catch (RemoteException e) { e.printStackTrace(); }
	}
	
	void zapiszS() {
		Double[] tab2 = {1.1, 2.1, 3.2, 4.3, 5.4};
		Spectrum ramka = new Spectrum("device2", "description2", 2020, 1, "unit2", 15.6, tab2 , 1);
		System.out.print("Please fill in the informations: ");
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
//		System.out.print("Length: ");
//		if (userInput.hasNextLine()) {
//			ramka.length = userInput.nextLine();
//		}
		System.out.print("Scale: ");
		if (userInput.hasNextLine()) {
			ramka.scailing = Integer.parseInt(userInput.nextLine());
		}
		try {
			remoteObject.zapisz(ramka);
		} catch (RemoteException e) { e.printStackTrace(); }
	}
	
	
	
	
	// funkcja zapisujaca dane do pliku
	public static void zapis(String string, String plik) throws FileNotFoundException {	
	PrintWriter zapis = new PrintWriter(plik);
	String newLine = System.getProperty("line.separator");
	 
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	System.out.println(dateFormat.format(date));
	 
	 zapis.println("Data (" + dateFormat.format(date) + "): " + string + newLine);
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
		System.out.println("Do you want to save obtained data in data.txt? y/n");
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
		System.out.println("Do you want to save obtained data in data.txt? y/n");
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
	
	
	void wypiszTH() {
		String line;
		System.out.print("Device name? :  ");
		if(userInput.hasNextLine()) {
			line = userInput.nextLine();
			Vector<TimeHistory> vec = null;
			try {
				vec = remoteObject.wypiszTH(line);
			} catch (RemoteException e) { e.printStackTrace(); }
			System.out.println("There are " + vec.size() + " user(s):");
			for (TimeHistory s : vec)
				System.out.println(" - " + s);
		}
	}
	void wypiszS() {
		String line;
		System.out.print("Device name? :  ");
		if(userInput.hasNextLine()) {
			line = userInput.nextLine();
			Vector<Spectrum> vec = null;
			try {
				vec = remoteObject.wypiszS(line);
			} catch (RemoteException e) { e.printStackTrace(); }
			System.out.println("There are " + vec.size() + " user(s):");
			for (Spectrum s : vec)
				System.out.println(" - " + s);
		}
	
	}
	
	private void komunikuj() {
		String uname;
		System.out.print("Who do you want to chat with? ");
		if(userInput.hasNextLine()) {
			uname = userInput.nextLine();
			String utext;
			System.out.print("Enter the text you want to send : ");
			if (userInput.hasNextLine()) {
				utext = userInput.nextLine();
				try {
					remoteObject.komunikuj(uname, utext);
				} catch (RemoteException e) { e.printStackTrace(); }
			}
		}
	}
	
	Object loop() throws FileNotFoundException {
		while(true) {
			String line;
			System.out.println("Choose what do you want to do: ");
			System.out.println("[i]nform, [c]omunicate, [n]ew data, [o]ld data, [l]og out");
			if(userInput.hasNextLine()) {
				line = userInput.nextLine();
				if(!line.matches("[icnol]")) {
					System.out.println("You entered an invalid command");
					continue;
				}
				switch (line) {
					case "i":
						informuj();
						return loop();
					case "c":
						komunikuj();
						return loop();
					case "n":
						System.out.println("What do you want to do with the new data set?");
						System.out.println("[s]ave TimeHistory, s[a]ve Spectrum, [w]rite TimeHistory info,w[r]ite Spectrum info, [b]ack");
						if(userInput.hasNextLine()) {
							line = userInput.nextLine();
							if(!line.matches("[sawrb]")) {
								System.out.println("You entered an invalid command !");
								continue;
							}
							switch (line) {
								case "s":
									zapiszTH();
									tHist();
									return loop();
								case "a":
									zapiszS();
									Spectrum();
									return loop();
								case "w":
									wypiszTH();
									return loop();
								case "r":
									wypiszS();
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