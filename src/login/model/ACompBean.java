package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class ACompBean {
	private DatabaseInterface di;

	public ACompBean() {
		try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String completeTask(String action_id) {
		try {
			return di.markasCompleted(action_id)
		} catch (RemoteException e) {
			return null;
		}
	}
}
