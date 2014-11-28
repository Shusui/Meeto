package login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

import login.model.LoadBean;
import rmiserver.application.Meeting;

public class LoadAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;

	@Override
	public String execute() {
		ArrayList<Meeting> meetings = this.getLoadBean().loadMeetings();
		session.put("meetings", meetings);
		return SUCCESS;
	}

	public LoadBean getLoadBean() {
		if(!session.containsKey("loadBean"))
			this.setLoadBean(new LoadBean());
		
		return (LoadBean) session.get("loadBean");
	}

	public void setLoadBean(LoadBean loadBean) {
		this.session.put("loadBean", loadBean);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}