package login.model;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmiserver.DatabaseInterface;

public class LoginBean {
	private DatabaseInterface di;
	private String username;
	private String password;

	public LoginBean() {
		try {
			String address = "rmi://localhost:2005/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		/*
		catch(NotBoundException | MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String verifyLogin(){
		System.out.println(username + " " + password);
		
		try {
			return di.verifiyLogin(this.username, this.password);
		} catch (RemoteException e) {
			return null;
		}
	}
}