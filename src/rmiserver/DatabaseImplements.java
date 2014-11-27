package rmiserver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiserver.types.Action;
import rmiserver.types.Key;
import rmiserver.types.Meeting;
import rmiserver.types.User;
import rmiserver.types.Item;

public class DatabaseImplements extends UnicastRemoteObject implements DatabaseInterface {

	private static final long serialVersionUID = 1L;
	private Connection conn;
	
	public DatabaseImplements(String db) throws RemoteException, SQLException{
		String url 	  = "jdbc:mysql://127.0.0.1:3306/";
		String driver = "com.mysql.jdbc.Driver";
		String user   = "root";
		String pw 	  = "root";
		
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		conn = DriverManager.getConnection(url+db, user, pw);
		createTables();
	}
	
	public void createTables() throws SQLException{
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user ("
						 + "iduser INTEGER(64) NOT NULL AUTO_INCREMENT,"
						 + "username VARCHAR(100) NOT NULL,"
						 + "password VARCHAR(100) NOT NULL,"
						 + "PRIMARY KEY (iduser)) ENGINE=InnoDB;");
		
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS meeting ("
						 + "idmeeting INTEGER(64) NOT NULL AUTO_INCREMENT,"
						 + "leader INTEGER(64),"
				 		 + "title VARCHAR(100),"
				 		 + "description VARCHAR(100),"
				 		 + "date VARCHAR(100),"
				 		 + "location VARCHAR(100),"
				 		 + "PRIMARY KEY (idmeeting)) ENGINE=InnoDB;");
		
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS meeting_user ("
						 + "meeting INTEGER(64),"
						 + "user INTEGER(64),"
						 + "PRIMARY KEY (meeting, user)) ENGINE=InnoDB;");
		
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS item ("
						 + "iditem INTEGER(64) NOT NULL AUTO_INCREMENT,"
						 + "title VARCHAR(100),"
						 + "description VARCHAR(100),"
						 + "user INTEGER(64),"
						 + "meeting INTEGER(64),"
						 + "PRIMARY KEY (iditem)) ENGINE=InnoDB;");
		
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS comment ("
						 + "idcomment INTEGER(64) NOT NULL AUTO_INCREMENT,"
						 + "comment VARCHAR(100),"
						 + "user INTEGER(64),"
						 + "item INTEGER(64),"
						 + "PRIMARY KEY (idcomment)) ENGINE=InnoDB;");
		
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS keydecision ("
						 + "idkd INTEGER(64) NOT NULL AUTO_INCREMENT,"
						 + "description VARCHAR(100),"
						 + "item INTEGER(64),"
						 + "PRIMARY KEY (idkd)) ENGINE=InnoDB;");
		
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS action ("
						 + "idaction INTEGER(64) NOT NULL AUTO_INCREMENT,"
						 + "description VARCHAR(100),"
						 + "meeting INTEGER(64),"
						 + "user INTEGER(64),"
						 + "done TINYINT(1) DEFAULT '0',"
						 + "PRIMARY KEY (idaction)) ENGINE=InnoDB;");
	}
	
	public synchronized String createAccount(String user, String pw) throws RemoteException{
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user where username='" + user + "';");
			
			if(rs.next())
				return "";
			else{
				stmt.executeUpdate("INSERT INTO user (username, password) VALUES ('" + user + "','" + pw + "');");
				return user;
			}
		} catch (SQLException e){
			return null;
		}
	}
	
	public String verifiyLogin(String username, String pw) throws RemoteException{
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user where username='" 
						 + username + "' and password='" + pw + "';");
			
			if(rs.next())
				return rs.getString("username");
			else
				return "";
		}catch (SQLException e){
			return null;
		}
	}
	
	public String createMeeting(String leader) throws RemoteException{
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO meeting (leader, title, description, date, location) VALUES (" 
								+ "'" + this.getUserId(leader) + "','new','new','new','new');");
			
			return Integer.toString(this.getHighestMeetingId());
		}catch (SQLException e){
			return "";
		}
	}
	
	public int getHighestMeetingId() throws RemoteException{
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from meeting order by idmeeting desc limit 1;");
			
			if(rs.next())
				return rs.getInt("idmeeting");
			else
				return 0;

		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int getHighestItemId() throws RemoteException{
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from item order by iditem desc limit 1;");
			
			if(rs.next())
				return rs.getInt("iditem");
			else
				return 0;

		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int getHighestKeyId() throws RemoteException{
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from keydecision order by idkd desc limit 1;");
			
			if(rs.next())
				return rs.getInt("idkd");
			else
				return 0;

		} catch (SQLException e) {
			return 0;
		}
	}
	
	public String updateCell(String id, String newcell, String column, String table) throws RemoteException{
		try {
			Statement stmt = conn.createStatement();
			
			String idtable = null;
			if(table.equals("meeting"))
				idtable = "idmeeting";
			else if(table.equals("item"))
				idtable = "iditem";
			else if(table.equals("keydecision"))
				idtable = "idkd";

			stmt.executeUpdate("update " + table + " set " + column + " = " 
							+ "\"" + newcell + "\"" +  " where " + idtable + "= " + "\"" + id + "\"");
			
			return "check";
		} catch (SQLException e) {
			return "";
		}
	}
	
	public int getUserId(String user) throws RemoteException{
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user where username='" + user + "';");
			
			if(rs.next())
				return rs.getInt("iduser");
			else
				return 0;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public String getUser(int id) throws RemoteException{
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user where iduser='" + id + "';");
			
			if(rs.next())
				return rs.getString("username");
			else
				return "";
		} catch (SQLException e) {
			return null;
		}
	}

	public ArrayList<Meeting> loadMeetings() throws RemoteException {
		ArrayList<Meeting> meets = new ArrayList<Meeting>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from meeting;");
			
			while(rs.next()){
				String id = Integer.toString(rs.getInt("idmeeting"));
				String leader = this.getUser(rs.getInt("leader"));
				String title = rs.getString("title");
				String description = rs.getString("description");
				String date = rs.getString("date");
				String location = rs.getString("location");
				
				meets.add(new Meeting(id, leader, title, description, date, location));
			}
			
			return meets;
		} catch (SQLException e) {
			return null;
		}
	}

	public synchronized ArrayList<User> loadMeetUsers(String not, String idmeeting) throws RemoteException {
		ArrayList<User> us = new ArrayList<User>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select iduser, username from user where iduser " + not + " IN (SELECT user FROM meeting_user WHERE meeting =" + idmeeting + ");");

			while(rs.next()){
				String id = Integer.toString(rs.getInt("iduser"));
				String user = rs.getString("username");
				
				us.add(new User(id, user));
			}
			
			return us;
		} catch (SQLException e) {
			return null;
		}
	}

	public String addMeetingUser(String user, String idmeeting) throws RemoteException {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO meeting_user (meeting, user) VALUES (" + idmeeting + "," + this.getUserId(user) + ");");
			
			return "check";
		} catch(SQLException e){
			return "";
		}
	}

	public synchronized String delMeetingUser(String user, String idmeeting) throws RemoteException {
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM meeting_user WHERE meeting=" + idmeeting + " AND user=" + this.getUserId(user) + ";");
			
			return "check";
		} catch(SQLException e){
			return "";
		}
	}

	public synchronized String deleteMeeting(String idmeeting) throws RemoteException {
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM meeting WHERE idmeeting=" + idmeeting + ";");
			stmt.executeUpdate("DELETE FROM meeting_user WHERE meeting=" + idmeeting + ";");
			stmt.executeUpdate("DELETE FROM item WHERE meeting=" + idmeeting + ";");
			
			return "check";
		} catch(SQLException e){
			return "";
		}
	}

	public String createItem(String user, String idmeeting) throws RemoteException {
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO item (title, description, user, meeting) VALUES ('new', 'new','" 
							  + this.getUserId(user) + "','" + idmeeting +"');");
			
			return Integer.toString(getHighestItemId());
		} catch(SQLException e){
			return "";
		}
	}

	public synchronized String deleteItem(String iditem) throws RemoteException {
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM item WHERE iditem=" + iditem + ";");
			stmt.executeUpdate("DELETE FROM comment WHERE item=" + iditem + ";");
			
			return "noerror";
		} catch(SQLException e){
			return "";
		}
	}

	public ArrayList<Item> loadItems(String idmeeting) throws RemoteException {
		ArrayList<Item> its = new ArrayList<Item>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from item where meeting=" + idmeeting + ";");

			while(rs.next()){
				String id = Integer.toString(rs.getInt("iditem"));
				String title = rs.getString("title");
				String description = rs.getString("description");
				
				its.add(new Item(id, title, description));
			}
			
			return its;
		} catch (SQLException e) {
			return null;
		}
	}

	public String addComment(String user, String idmeeting, String text) throws RemoteException {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO comment (comment, user, item) VALUES ('" 
							  + text + "','" + this.getUserId(user) + "','" + idmeeting + "');");
			
			return "check";
		} catch (SQLException e) {
			return "";
		}
	}

	public ArrayList<String> loadChat(String iditem) throws RemoteException {
		ArrayList<String> chat = new ArrayList<String>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM comment WHERE item=" + iditem + ";");
			
			while(rs.next()){
				String user = this.getUser(rs.getInt("user"));
				String comment = rs.getString("comment");
				
				chat.add((user + ": " + comment + "\n"));
			}
			
			return chat;
		} catch (SQLException e) {
			return null;
		}
	}

	public ArrayList<Key> loadKeys(String iditem) throws RemoteException {
		ArrayList<Key> keys = new ArrayList<Key>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM keydecision WHERE item=" + iditem + ";");
			
			while(rs.next()){
				String id = Integer.toString(rs.getInt("idkd"));
				String desc = rs.getString("description");

				keys.add(new Key(id, desc));
			}
			
			return keys;
		} catch (SQLException e) {
			return null;
		}
	}

	public String addKeyd(String iditem) throws RemoteException {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO keydecision (description, item) VALUES ('new','" 
							  + iditem + "');");
			
			return Integer.toString(getHighestKeyId());
		} catch (SQLException e) {
			return "";
		}
	}

	public String assignTask(String user, String idmeeting, String desc) throws RemoteException {
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO action (description, meeting, user) VALUES ('"
							  + desc + "','" + idmeeting + "','" + this.getUserId(user) + "');");
			
			return "check";
		} catch(SQLException e){
			return "";
		}
	}

	public ArrayList<Action> personalTodo(String idmeeting, String user) throws RemoteException {
		ArrayList<Action> todoList = new ArrayList<Action>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM action WHERE (meeting=" 
						+ idmeeting + " AND user=" + this.getUserId(user) + ") AND done <> 1;");
			
			while(rs.next()){
				String id = Integer.toString(rs.getInt("idaction"));
				String desc = rs.getString("description");

				todoList.add(new Action(id, desc));
			}
			
			return todoList;
		} catch (SQLException e) {
			return null;
		}
	}

	public String markasCompleted(String idaction) throws RemoteException {
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE action SET done=1 WHERE idaction=" + idaction);
			
			return "check";
		} catch(SQLException e){
			System.out.println("SQL EXception");
			return "";
		}
	}

	public ArrayList<Action> meetingsAction(String idmeeting) throws RemoteException {
		ArrayList<Action> all = new ArrayList<Action>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM action WHERE meeting=" + idmeeting + ";");
			
			while(rs.next()){
				String user = this.getUser(rs.getInt("user"));
				String desc = rs.getString("description");

				all.add(new Action(user, desc));
			}
			
			return all;
		} catch (SQLException e) {
			return null;
		}
	}
}