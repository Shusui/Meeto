package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class TaskBean {
	private DatabaseInterface di;

	public TaskBean() {
		try {
			String address2 = "localhost";
			String address1 = "10.42.0.1";
			
			String address = "rmi://" + address2 + ":1099/database";
			di = (DatabaseInterface) Naming.lookup(address);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String assignUserTask(String username, String meeting_id, String description) {
		try {
			return di.assignTask(username, meeting_id, description); 
		} catch (RemoteException e) {
			return null;
		}
	}
}
