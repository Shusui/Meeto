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
		if(this.username != null && !username.equals("") && password != null && !password.equals("")) {
			this.getLoginBean().setUsername(this.username);
			this.getLoginBean().setPassword(this.password);
			String result = this.getLoginBean().verifyLogin();
			
			if(result != null && !result.equals("")){
				session.put("username", username);
				session.put("loggedin", true);
				return SUCCESS;
			}else
				return "WRONG";
		}
		else
			return LOGIN;
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