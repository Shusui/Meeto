package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class UpdateBean {
	private DatabaseInterface di;

	public UpdateBean() {
		try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String updateMeeting(String meeting_id, String new_value, String column) {
		try {
			return di.updateCell(meeting_id, new_value, column, "meeting");
		} catch (RemoteException e) {
			return null;
		}
	}
}
