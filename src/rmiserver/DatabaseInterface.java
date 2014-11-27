package rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import rmiserver.types.Action;
import rmiserver.types.Key;
import rmiserver.types.Meeting;
import rmiserver.types.User;
import rmiserver.types.Item;

public interface DatabaseInterface extends Remote {
	public String createAccount(String user, String pw) throws RemoteException;
	public String verifiyLogin(String username, String pw) throws RemoteException;
	public String createMeeting(String leader) throws RemoteException;
	public int getHighestMeetingId() throws RemoteException;
	public String updateCell(String id, String newcell, String column, String table) throws RemoteException;
	public int getUserId(String user) throws RemoteException;
	public String getUser(int id) throws RemoteException;
	public ArrayList<Meeting> loadMeetings() throws RemoteException;
	public ArrayList<User> loadMeetUsers(String not, String idmeeting) throws RemoteException; 
	public String addMeetingUser(String user, String idmeeting) throws RemoteException;
	public String delMeetingUser(String user, String idmeeting) throws RemoteException;
	public String deleteMeeting(String idmeeting) throws RemoteException;
	public String createItem(String user, String idmeeting) throws RemoteException;
	public String deleteItem(String iditem) throws RemoteException;
	public ArrayList<Item> loadItems(String idmeeting) throws RemoteException;
	public String addComment(String user, String idmeeting, String text) throws RemoteException;
	public ArrayList<String> loadChat(String iditem) throws RemoteException;
	public ArrayList<Key> loadKeys(String iditem) throws RemoteException;
	public String addKeyd(String iditem) throws RemoteException;
	public String assignTask(String user, String idmeeting, String desc) throws RemoteException;
	public ArrayList<Action> personalTodo(String idmeeting, String user) throws RemoteException;
	public String markasCompleted(String idaction) throws RemoteException;
	public ArrayList<Action> meetingsAction(String idmeeting) throws RemoteException;
}