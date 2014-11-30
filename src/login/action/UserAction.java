package login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
import login.model.UserBean;

public class UserAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private Map<String, Object> session;
	
	@Override
	public String execute() {
		String meeting_id = session.get("meeting_id").toString();
		if(!meeting_id.equals("-1")) {
			String result = this.getUserBean().addUser(username, meeting_id);
		}
		return SUCCESS;
	}
	
	public String execute_() {
		String meeting_id = session.get("meeting_id").toString();
		if(!meeting_id.equals("-1")) {
			String result = this.getUserBean().delUser(username, meeting_id);
		}
		return SUCCESS;
	}
	
	public String decline() {
		String meeting_id = session.get("meeting_id").toString();
		if(!meeting_id.equals("-1")) {
			String result = this.getUserBean().delUser(session.get("username").toString(), meeting_id);
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public UserBean getUserBean() {
		if(!session.containsKey("userBean"))
			this.setUserBean(new UserBean());
		
		return (UserBean) session.get("userBean");
	}

	public void setUserBean(UserBean userBean) {
		this.session.put("userBean", userBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
