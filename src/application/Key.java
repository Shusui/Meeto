package application;

import java.io.Serializable;

public class Key implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String desc;
 
    public Key(String id, String desc){
    	this.id   = id;
    	this.desc = desc;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id){
    	this.id = id;
    }
    
    public String getDesc() {
    	return desc;
    }
    
    public void setDesc(String desc){
    	this.desc = desc;
    }  
}
