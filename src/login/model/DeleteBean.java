package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class DeleteBean {
	private DatabaseInterface di;

	public DeleteBean() {
		try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String deleteMeeting(String meeting_id) {
		try {
			return di.deleteMeeting(meeting_id);
		} catch (RemoteException e) {
			return null;
		}
	}
}
