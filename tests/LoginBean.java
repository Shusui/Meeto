import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import rmiserver.RMIServerInterface;

public class LoginBean {
	private RMIServerInterface db;
	private String username;
	private String password;

	public HeyBean() {
		try {
			db = (RMIServerInterface) Naming.lookup("database");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String verifyLogin() {
		return db.verifiyLogin(this.username, this.password);
	}
}