package login.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

import login.model.CreateBean;
import rmiserver.application.Meeting;

public class CreateAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	
	@Override
	public String execute() {
		String result = this.getCreateBean().scheduleMeeting((String) session.get("username"));
		return SUCCESS;
	}
	
	public CreateBean getCreateBean() {
		if(!session.containsKey("createBean"))
			this.setCreateBean(new CreateBean());
		
		return (CreateBean) session.get("createBean");
	}

	public void setCreateBean(CreateBean createBean) {
		this.session.put("createBean", createBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
