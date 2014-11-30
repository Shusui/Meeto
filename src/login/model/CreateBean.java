package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;
import rmiserver.application.Meeting;

public class CreateBean {
	private DatabaseInterface di;

	public CreateBean() {
		try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String addItem(String username, String idmeeting){
		try {
			return di.createItem(username, idmeeting);
		} catch (RemoteException e) {
			return null;
		}
	}
	
	public String scheduleMeeting(String leader) {
		try {
			return di.createMeeting(leader);
		} catch (RemoteException e) {
			return null;
		}
	}
}
