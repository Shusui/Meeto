package rmiserver;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

public class Database {
	
	@SuppressWarnings("rawtypes")
	public static String getIpAddress() {
		try {
			for (Enumeration enumeration = NetworkInterface.getNetworkInterfaces(); enumeration.hasMoreElements();) {
				NetworkInterface netinterface = (NetworkInterface) enumeration.nextElement();
				for (Enumeration enumerationIpAddress = netinterface.getInetAddresses(); enumerationIpAddress.hasMoreElements();) {
					InetAddress inetAddress = (InetAddress) enumerationIpAddress.nextElement();
					if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (Exception e) {}
		
		return null;
	}

	public static void main(String [] args) {	
 		try {
 			String ipAddress = getIpAddress();
			if (ipAddress != null)
				System.setProperty("java.rmi.server.hostname", getIpAddress());
 			
 			System.out.print("Connecting to database.. ");
 			DatabaseImplements db = new DatabaseImplements("meeto");
 			System.out.println("Connected to database!");
 			
 			Registry r = LocateRegistry.createRegistry(1099);
 			r.rebind("database", db);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
	}

}
