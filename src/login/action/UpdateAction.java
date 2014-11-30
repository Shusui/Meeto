package login.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import login.model.UpdateBean;

public class UpdateAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String title = null, description = null, date = null, location = null;
	
	@Override
	public String execute() {
		String meeting_id = session.get("meeting_id").toString();
		if(!meeting_id.equals("-1")) {
			if(title != null && !title.equals("")) {
				String result1 = this.getUpdateBean().updateMeeting(meeting_id, title, "title");
			}
			if(description != null && !description.equals("")) {
				String result2 = this.getUpdateBean().updateMeeting(meeting_id, description, "description"); 
			}
			if(date != null && !date.equals("")) {
				String result3 = this.getUpdateBean().updateMeeting(meeting_id, date, "date");
			}
			if(location != null && !location.equals("")) {
				String result4 = this.getUpdateBean().updateMeeting(meeting_id, location, "location");
			}
		}
		return SUCCESS;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescritpion(String description) {
		this.description = description;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public UpdateBean getUpdateBean() {
		if(!session.containsKey("updateBean"))
			this.setUpdateBean(new UpdateBean());
		
		return (UpdateBean) session.get("updateBean");
	}

	public void setUpdateBean(UpdateBean updateBean) {
		this.session.put("updateBean", updateBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
