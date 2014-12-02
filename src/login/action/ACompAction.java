package login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
import login.model.ACompBean;

public class ACompAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	
	@Override
	public String execute() {
		String action_id = session.get("action_id").toString();
		if(!action_id.equals("-1")) {
			this.getACompBean().completeTask(action_id);
		}
		return SUCCESS;
	}
	
	public ACompBean getACompBean() {
		if(!session.containsKey("acompBean"))
			this.setACompBean(new ACompBean());
		
		return (ACompBean) session.get("acompBean");
	}

	public void ACompBean(ACompBean acompBean) {
		this.session.put("acompBean", acompBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
