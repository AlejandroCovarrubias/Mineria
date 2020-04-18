/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Home
 */
@ServerEndpoint(value = "/gps")
public class ServerSocket {

    private static List<Session> clients = 
            Collections.synchronizedList(new ArrayList<Session>());
    
    
    @OnOpen
    public void onOpen(Session sesion){
        System.out.println("Open Connection ...");
        clients.add(sesion);
    }
     
    @OnClose
    public void onClose(Session sesion){
        System.out.println("Close Connection ...");
        clients.remove(sesion);
    }
     
    @OnMessage
    public void onMessage(String message, Session sesion){
        System.out.println("llego "+message);
    }
    
    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }  
    
}
