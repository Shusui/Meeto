package login.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import login.model.RegisterBean;

public class RegisterAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 4L;
	private Map<String, Object> session;
	private String username = null, password = null;
	
	@Override
	public String execute() {
		System.out.println("Session: " + session);
		
		session.put("regError", false);
		session.put("regSuccess", false);
		session.put("noUser", false);
		session.put("noPw", false);
		session.put("noCreds", false);
		session.put("invalidUser", false);
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
		this.getRegisterBean().setUsername(this.username);
		this.getRegisterBean().setPassword(this.password);
		String result = this.getRegisterBean().createAccount();
		if(result != null && !result.equals("")){
			session.put("regSuccess", true);
			return SUCCESS;
		
		} else if(result.equals("")) {
			session.put("invalidUser", true);
			return "ERROR";
		}else {
			session.put("regError", true);
			return "ERROR";
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public RegisterBean getRegisterBean() {
		if(!session.containsKey("registerBean"))
			this.setRegisterBean(new RegisterBean());
		
		return (RegisterBean) session.get("registerBean");
	}

	public void setRegisterBean(RegisterBean registerBean) {
		this.session.put("registerBean", registerBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		System.out.println("Session2");
		this.session = session;
	}
}
