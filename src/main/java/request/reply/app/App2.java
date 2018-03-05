/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply.app;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import java.nio.ByteBuffer;
import request.reply.Client;
import request.reply.Message;
import request.reply.RemoteRef;

/**
 *
 * @author juan
 */
public class App2 {
    public static void main(String[] args) throws Base64DecodingException {
        Client cli = new Client();
        RemoteRef ref = new RemoteRef("127.0.0.1", 1025);
        ByteBuffer bbf = ByteBuffer.allocate(8);
        bbf.putInt(1);
        bbf.putInt(456);
        Message message = new Message(0, 12, 1, bbf.array());
        cli.doOperation(ref, message);
    }
    
}
