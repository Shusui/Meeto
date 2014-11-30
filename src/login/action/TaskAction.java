package login.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import login.model.TaskBean;

public class TaskAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String description = null, username = null;
	
	@Override
	public String execute() {
		String meeting_id = session.get("meeting_id").toString();
		if(!meeting_id.equals("-1") && username != null && description != null && !username.equals("") && !description.equals("")) {
			String result = this.getTaskBean().assignUserTask(username, meeting_id, description);
		}
		return SUCCESS;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setDescritpion(String description) {
		this.description = description;
	}
	
	public TaskBean getTaskBean() {
		if(!session.containsKey("taskBean"))
			this.setTaskBean(new TaskBean());
		
		return (TaskBean) session.get("taskBean");
	}

	public void setTaskBean(TaskBean taskBean) {
		this.session.put("taskBean", taskBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
