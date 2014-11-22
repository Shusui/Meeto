import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import application.Action;
import application.Key;
import application.Meeting;
import application.User;
import application.Item;

public interface DatabaseInterface extends Remote {

	public String createAccount(String user, String pw) throws RemoteException; // FINE
	public String verifiyLogin(String username, String pw) throws RemoteException; // FINE
	public String createMeeting(String leader) throws RemoteException; // FINE
	public int getHighestMeetingId() throws RemoteException; // NO NEED
	public String updateCell(String id, String newcell, String column, String table) throws RemoteException; // FINE
	public int getUserId(String user) throws RemoteException; // NO NEED
	public String getUser(int id) throws RemoteException; // FINE
	public ArrayList<Meeting> loadMeetings() throws RemoteException;
	public ArrayList<User> loadMeetUsers(String not, String idmeeting) throws RemoteException; 
	public String addMeetingUser(String user, String idmeeting) throws RemoteException; // FINE
	public String delMeetingUser(String user, String idmeeting) throws RemoteException; // FINE
	public String deleteMeeting(String idmeeting) throws RemoteException; // FINE
	public String createItem(String user, String idmeeting) throws RemoteException; // FINE
	public String deleteItem(String iditem) throws RemoteException;
	public ArrayList<Item> loadItems(String idmeeting) throws RemoteException;
	public String addComment(String user, String idmeeting, String text) throws RemoteException;
	public ArrayList<String> loadChat(String iditem) throws RemoteException;
	public ArrayList<Key> loadKeys(String iditem) throws RemoteException;
	public String addKeyd(String iditem) throws RemoteException; // FINE
	public String assignTask(String user, String idmeeting, String desc) throws RemoteException;
	public ArrayList<Action> personalTodo(String idmeeting, String user) throws RemoteException;
	public String markasCompleted(String idaction) throws RemoteException;
	public ArrayList<Action> meetingsAction(String idmeeting) throws RemoteException;
}