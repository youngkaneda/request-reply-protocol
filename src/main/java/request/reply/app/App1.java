/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply.app;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import org.json.JSONObject;
import request.reply.Marshaller;
import request.reply.Message;
import request.reply.Server;

/**
 *
 * @author juan
 */
public class App1 {

    public static void main(String[] args) throws IOException, SocketException, Base64DecodingException, ClassNotFoundException {
        Server server = new Server();
        byte[] request = server.getRequest();
        byte[] response = process(request);
        server.sendReply(response);
    }

    public static byte[] process(byte[] request) throws Base64DecodingException{
        JSONObject object = new JSONObject(new String(request));
        Message reqMessage = Marshaller.unMarshal(object);
        Message resMessage = new Message();
        if(reqMessage.getOperationId() == 1){
            ByteBuffer bff = ByteBuffer.wrap(reqMessage.getArguments());
            //
            int x = bff.getInt();
            int y = bff.getInt();
            int sum = sum(x, y);
            //
            ByteBuffer resultBff = ByteBuffer.allocate(4).putInt(sum);
            //
            resMessage.setMessageType(1);
            resMessage.setRequestId(reqMessage.getRequestId());
            //formato definido por mim, caracterizado por ser o operationId negativo
            //apenas uma flag
            resMessage.setOperationId(reqMessage.getOperationId() * -1);
            resMessage.setArguments(resultBff.array());
        }
        JSONObject resJson = Marshaller.marshal(resMessage);
        //
        return resJson.toString().getBytes();
    }
    
    public static int sum(int x, int y) {
        return Integer.sum(x, y);
    }
}
