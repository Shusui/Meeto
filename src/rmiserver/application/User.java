package rmiserver.application;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String user;
 
    public User(String id, String user){
    	this.id = id;
    	this.user = user;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id){
    	this.id = id;
    } 
    
    public String getUser() {
    	return user;
    }
    
    public void setUser(String user){
    	this.user = user;
    }  
}
