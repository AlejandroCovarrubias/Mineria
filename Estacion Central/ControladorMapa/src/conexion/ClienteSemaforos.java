/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Home
 */
@ClientEndpoint
public class ClienteSemaforos {
    
    @OnOpen
    public void onOpen(Session p) {
    }
    
    @OnMessage
    public void onMessage(String message) {
        
    }
    
    
}
