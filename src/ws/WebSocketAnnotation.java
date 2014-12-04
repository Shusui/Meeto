package ws;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.Session;

import rmiserver.DatabaseInterface;

@ServerEndpoint(value = "/ws",
				configurator = GetHttpSessionConfigurator.class)
public class WebSocketAnnotation {
    private static final AtomicInteger sequence = new AtomicInteger(1);
    private String username;
    private String itemid;
    private Session session;
    private HttpSession httpSession;
    
    private DatabaseInterface di;
    private static final Set<WebSocketAnnotation> users =
            new CopyOnWriteArraySet<>();
    
    public String getItemId(){
    	return itemid;
    }
    
    public String getUsername(){
    	return username;
    }

    public WebSocketAnnotation() {
        username = "User" + sequence.getAndIncrement();
        
        try {
			String address = "rmi://localhost:1099/database";
			di = (DatabaseInterface) Naming.lookup(address);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }

    @OnOpen
    public void start(Session session, EndpointConfig config) {
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());
        
        users.add(this);
        username = (String) httpSession.getAttribute("username");
        itemid = httpSession.getAttribute("item_id").toString();
        String message = username + ": connected.";
        String old = "";
        ArrayList<String> m = null;
        
        if(!itemid.equals("-1")){
	        try {
				m = di.loadChat(itemid);
			} catch (RemoteException e) {}
	        
	        if(m != null){
	        	for(int i = 0; i < m.size(); i++)
	        		old += "<p>" + m.get(i) + "</p>";
	        }
        }
        
        if(old.equals(""))
        	sendMessage(message);
        else
        	selfMessage(old);
    }

    @OnClose
    public void end() {
    	// clean up once the WebSocket connection is closed
    	users.remove(this);
    }

    @OnMessage
    public void receiveMessage(String message) {
		String itemid = httpSession.getAttribute("item_id").toString();
		System.out.println(itemid);
		
		String reversedMessage = new StringBuffer(message).toString();
		System.out.println("Message: " + reversedMessage);
		
		String[] temp = reversedMessage.split("\\s+");
		//System.out.println("Temp[0]: " + temp[0] + "\n" + "Temp[1]: " + temp[1] + "\n" + "Temp[2]: " + temp[2]);
		if(temp[0] != null && temp[0].equals("alert")){
			toMessage(reversedMessage, temp[1]);
			return;
		}
		
		if(!itemid.equals("-1")){
	    	try {
				di.addComment(username, itemid, message);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	// one should never trust the client, and sensitive HTML
        // characters should be replaced with &lt; &gt; &quot; &amp;
    	sendMessage(username + ": " + reversedMessage);
    }
    
    @OnError
    public void handleError(Throwable t) {
    	t.printStackTrace();
    }
    
    private void toMessage(String text, String to){
    	for(WebSocketAnnotation client : users){
    		if(client.getUsername().equals(to)){
		    	try {
					//this.session.getBasicRemote().sendText(text);
		    		client.session.getBasicRemote().sendText(text);
		    	} catch (IOException e) {
					// clean up once the WebSocket connection is closed
					try {
						//this.session.close();
						client.session.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
    		}
    	}
    }
    
    private void selfMessage(String text){
    	try {
			//this.session.getBasicRemote().sendText(text);
    		this.session.getBasicRemote().sendText(text);
    	} catch (IOException e) {
			// clean up once the WebSocket connection is closed
			try {
				//this.session.close();
				this.session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }

    private void sendMessage(String text) {
    	// uses *this* object's session to call sendText()
    	for(WebSocketAnnotation client : users){
    		if(!this.getItemId().equals("-1") && client.getItemId().equals(this.getItemId())){
		    	try {
					//this.session.getBasicRemote().sendText(text);
		    		client.session.getBasicRemote().sendText(text);
		    	} catch (IOException e) {
					// clean up once the WebSocket connection is closed
					try {
						//this.session.close();
						client.session.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
    		}
    	}
    }
}
