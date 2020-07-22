package edu.escuelaing.alfonso.proyecto.arsw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class ChatController {

    @Autowired
    SimpMessagingTemplate msgt;

    /**
     * Broadcast of messages in specific chat
     * @param state new message in chat
     */
    @MessageMapping("/chat")
    public void handleState(List<String> mensajes){
    	String producto = mensajes.get(1);
    	if(mensajes.size() == 3 || mensajes.size() == 2) {    		
    		String respuesta =  producto + "." + mensajes.get(0);
    		msgt.convertAndSend("/topic/chat." + producto, respuesta);
    	}else {
    		Integer mensaje = Integer.parseInt(mensajes.get(0));
            Integer precio = Integer.parseInt(mensajes.get(2));
            String username = mensajes.get(3);
            String respuesta = null;
            if(mensaje > precio) { 
            	
                respuesta= mensaje.toString() +"-"+ username;
                System.out.println(respuesta);
            }else {
            	respuesta= precio.toString();
            }
           
            
            msgt.convertAndSend("/topic/chat." + producto, respuesta);
    	}
        
        
    }

}