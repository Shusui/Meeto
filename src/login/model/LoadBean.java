package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import rmiserver.DatabaseInterface;
import rmiserver.application.Meeting;

public class LoadBean {
	private DatabaseInterface di;

	public LoadBean() {
		try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Meeting> loadMeetings(){
		try {
			return di.loadMeetings();
		} catch (RemoteException e) {
			return null;
		}
	}
}