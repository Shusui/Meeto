package login.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

import login.model.DeleteBean;

public class DeleteAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	
	public String delitem(){
		String item_id = session.get("item_id").toString();
		if(!item_id.equals("-1")) {
			System.out.println("DELETE ITEM");
			String result = this.getDeleteBean().deleteItem(item_id);
		}
		else{
			System.out.println("-1");
		}
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		String meeting_id = session.get("meeting_id").toString();
		if(!meeting_id.equals("-1")) {
			String result = this.getDeleteBean().deleteMeeting(meeting_id);
		}
		return SUCCESS;
	}
	
	public DeleteBean getDeleteBean() {
		if(!session.containsKey("deleteBean"))
			this.setDeleteBean(new DeleteBean());
		
		return (DeleteBean) session.get("deleteBean");
	}

	public void setDeleteBean(DeleteBean deleteBean) {
		this.session.put("deleteBean", deleteBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
