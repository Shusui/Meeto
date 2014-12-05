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
import config.Config;

public class LoadBean {
	private DatabaseInterface di;

	public LoadBean() {
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

	public ArrayList<Meeting> loadMeetings(){
		System.out.println("LOAD MEETINGS BEAN");
		
		try {
			ArrayList<Meeting> tmp =  di.loadMeetings();
			return tmp;
		} catch (RemoteException e) {
			System.out.println("REMOTE");
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
	
	public String loadChat(String iditem){
		try{
			ArrayList<String> a = di.loadChat(iditem);
			String tmp= "";
			for(int i = 0; i < a.size(); i++)
				tmp += a.get(i);
			
			return tmp;
		} catch (RemoteException e) {
			return null;
		}
	}
}