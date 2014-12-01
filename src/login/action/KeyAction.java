package login.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import login.model.KeyBean;

public class KeyAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String description = null;
	
	@Override
	public String execute() {
		String meeting_id = session.get("meeting_id").toString();
		String item_id = session.get("item_id").toString();
		if(!meeting_id.equals("-1") && !item_id.equals("-1") && description != null && !description.equals("")) {
			this.getKeyBean().newKeyDecision(item_id, description);
		}
		
		return SUCCESS;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public KeyBean getKeyBean() {
		if(!session.containsKey("keyBean"))
			this.setKeyBean(new KeyBean());
		
		return (KeyBean) session.get("keyBean");
	}

	public void setKeyBean(KeyBean keyBean) {
		this.session.put("keyBean", keyBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
