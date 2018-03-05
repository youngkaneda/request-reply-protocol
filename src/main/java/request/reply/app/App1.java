/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply.app;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import java.io.IOException;
import java.net.SocketException;
import request.reply.Server;

/**
 *
 * @author juan
 */
public class App1 {
    public static void main(String[] args) throws IOException, SocketException, Base64DecodingException, ClassNotFoundException {
        Server server = new Server();
        byte[] response = server.getRequest();
        server.sendReply(response);
    }
    
}
