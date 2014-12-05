package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class UpdateBean {
	private DatabaseInterface di;

	public UpdateBean() {
		try {
			String address1 = "localhost";
			String address2 = "10.42.0.1";
			
			String address = "rmi://" + address2 + ":1099/database";
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
	
	public String updateItem(String id_item, String new_value, String column) {
		try {
			return di.updateCell(id_item, new_value, column, "item");
		} catch (RemoteException e) {
			return null;
		}
	}
}
