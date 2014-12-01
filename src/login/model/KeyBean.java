package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class KeyBean {
	private DatabaseInterface di;

	public KeyBean() {
		try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String newKeyDecision(String item_id, String description) {
		try {
			String key_id = di.addKeyd(item_id);
			return di.updateCell(key_id, description, "description", "keydecision");
		} catch (RemoteException e) {
			return null;
		}
	}
}
