package login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
import login.model.LoginBean;

public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 4L;
	private Map<String, Object> session;
	private String username = null, password = null;

	@Override
	public String execute() {
		session.put("loggedin", false);
		session.put("failUser", false);
		session.put("failPw", false);
		if(this.username.equals("")) {
			session.put("failUser", true);
			if(this.password.equals("")) {
				session.put("failPw", true);
			}
			return "ERROR";
		}
		if(this.password.equals("")) {
			session.put("failPw", true);
			return "ERROR";
		}
		this.getLoginBean().setUsername(this.username);
		this.getLoginBean().setPassword(this.password);
		String result = this.getLoginBean().verifyLogin();
		if(result != null && !result.equals("")){
			session.put("username", username);
			session.put("loggedin", true);
			return SUCCESS;
		}else
			return "ERROR";
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LoginBean getLoginBean() {
		if(!session.containsKey("loginBean"))
			this.setLoginBean(new LoginBean());
		
		return (LoginBean) session.get("loginBean");
	}

	public void setLoginBean(LoginBean loginBean) {
		this.session.put("loginBean", loginBean);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}