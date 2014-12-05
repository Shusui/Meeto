package login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;

public class InitAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 5L;
	private Map<String, Object> session;
	
	public InitAction(){
		System.out.println("Finally..");
	}
	
	@Override
	public String execute() {
		System.out.println("All False and Null");
		session.put("regError", false);
		session.put("regSuccess", false);
		session.put("invalidUser", false);
		session.put("loggedin", false);
		session.put("failLogin", false);
		session.put("noUser", false);
		session.put("noPw", false);
		session.put("noCreds", false);
		session.put("meeting_id", -1);
		session.put("actions", null);
		session.put("item_id", -1);
		session.put("items", null);
		session.put("action_id", -1);
		session.put("chat_messages", "");
		session.put("keys", null);

		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
