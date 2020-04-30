package lab2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import lab1.Packet;
import lab1.TimeHistory;

public class UDPClient {
	public static void main(String[] args) throws ClassNotFoundException {
		DatagramSocket aSocket = null;
		Scanner scanner = null;
		try {
			// args contain server hostname
			if (args.length < 1) {
				System.out.println("Usage: UDPClient <server host name>");
				System.exit(0);
			}
			byte[] buffer = new byte[1024];
			InetAddress aHost = InetAddress.getByName(args[0]);
			int serverPort = 9876;
			aSocket = new DatagramSocket();
			scanner = new Scanner(System.in);
			String line = "";
			//------------------------------------------------------
			Integer[] tab1 = {1, 2, 3, 4, 5, 6, 7};
			TimeHistory<Integer> packet = new TimeHistory<Integer>("device1", "description1",
					2020, 1, "unit1", 15.6, tab1 , 10.20);
			byte[] data=Tools.serialize(packet);
			DatagramPacket dp = new DatagramPacket(data,data.length,aHost,serverPort);
			aSocket.send(dp);
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			//String read=new String(reply.getData(),0,reply.getLength());
			Packet read = (Packet) Tools.deserialize(reply.getData());
			System.out.println(read);
			//------------------------------------------------------
		
		} catch (SocketException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			aSocket.close();
			scanner.close();
		}
	}
}