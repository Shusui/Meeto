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
		System.out.println("Session Login: " + session);
		
		session.put("regError", false);
		session.put("regSuccess", false);
		session.put("invalidUser", false);
		session.put("loggedin", false);
		session.put("failLogin", false);
		session.put("noUser", false);
		session.put("noPw", false);
		session.put("noCreds", false);
		if(this.username.equals("") && this.password.equals("")) {
			session.put("noCreds", true);
			return "ERROR";
		}
		if(this.username.equals("")) {
			session.put("noUser", true);
			return "ERROR";
		}
		if(this.password.equals("")) {
			session.put("noPw", true);
			return "ERROR";
		}
		this.getLoginBean().setUsername(this.username);
		this.getLoginBean().setPassword(this.password);
		String result = this.getLoginBean().verifyLogin();
		if(result != null && !result.equals("")){
			session.put("username", username);
			session.put("loggedin", true);
			return SUCCESS;
		}else {
			session.put("failLogin", true);
			return "ERROR";
		}
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
		System.out.println("Session");
		this.session = session;
	}
}