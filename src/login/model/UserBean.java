package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class UserBean {
	private DatabaseInterface di;

	public UserBean() {
		try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String addUser(String username, String meeting_id) {
		try {
			return di.addMeetingUser(username, meeting_id);
		} catch (RemoteException e) {
			return null;
		}
	}
	
	public String delUser(String username, String meeting_id) {
		try {
			return di.delMeetingUser(username, meeting_id);
		} catch (RemoteException e) {
			return null;
		}
	}
	
}
