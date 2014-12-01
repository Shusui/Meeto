package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import rmiserver.DatabaseInterface;
import rmiserver.application.Action;
import rmiserver.application.Item;
import rmiserver.application.Meeting;
import rmiserver.application.User;
import rmiserver.application.Key;

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
	
	public ArrayList<User> loadMeetUsers(String idmeeting){
		try {
			return di.loadMeetUsers("", idmeeting);
		} catch (RemoteException e) {
			return null;
		}
	}
	
	public ArrayList<Action> loadUserActions(String idmeeting, String user){
		try{
			return di.personalTodo(idmeeting, user);
		} catch (RemoteException e) {
			return null;
		}
	}
	
	public ArrayList<Item> loadItems(String idmeeting){
		try{
			return di.loadItems(idmeeting);
		} catch (RemoteException e) {
			return null;
		}
	}
	
	public ArrayList<Key> loadKeys(String iditem){
		try{
			return di.loadKeys(iditem);
		} catch (RemoteException e) {
			return null;
		}
	}
}