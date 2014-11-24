package login.model;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import rmiserver.DatabaseInterface;

public class LoginBean {
	private DatabaseInterface db;
	private String username;
	private String password;

	public LoginBean() {
		try {
			db = (DatabaseInterface) Naming.lookup("database");
		}
		catch(NotBoundException | MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String verifyLogin(){
		System.out.println(username + " " + password);
		
		// FIX LATER
		return "root";
		
		/*
		try {
			return db.verifiyLogin(this.username, this.password);
		} catch (RemoteException e) {
			return null;
		}
		*/
	}
}