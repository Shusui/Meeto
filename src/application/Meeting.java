package application;

import java.io.Serializable;

public class Meeting implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String leader;
	private String title;
	private String outcome;
	private String date;
	private String local;
 
    public Meeting(String id, String leader, String title, String outcome, String date, String local) {
    	this.id      = id;
    	this.leader  = leader;
        this.title   = title;
        this.outcome = outcome;
        this.date    = date;
        this.local   = local;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id){
    	this.id = id;
    }
    
    public String getLeader() {
    	return leader;
    }
    
    public void setLeader(String leader){
    	this.leader = leader;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getOutcome() {
        return outcome;
    }
    
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getLocal() {
        return local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }
    
}
