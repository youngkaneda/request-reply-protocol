/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.json.JSONObject;

/**
 *
 * @author juan
 */
public class Client {

    public byte[] doOperation(RemoteRef remoteRef, Message message) throws Base64DecodingException {
        try(DatagramSocket client = new DatagramSocket(1024)){
            JSONObject jsonRemoteRef = new JSONObject();
            jsonRemoteRef.put("host", remoteRef.getHost());
            jsonRemoteRef.put("port", remoteRef.getPort());
            //
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("message_type", message.getMessageType());
            jsonMessage.put("request_id", message.getRequestId());
            jsonMessage.put("method_id", message.getMethodId());
            jsonMessage.put("arguments", Base64.encode(message.getArguments()));
            jsonMessage.put("remote_ref", jsonRemoteRef);
            //
            DatagramPacket pack = new DatagramPacket(jsonMessage.toString().getBytes(), jsonMessage.toString().getBytes().length, 
                    InetAddress.getByName(remoteRef.getHost()), remoteRef.getPort());
            client.send(pack);
            //
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            client.receive(response);
            JSONObject responseJson = new JSONObject(new String(response.getData()));
            Message replied = Marshaller.unMarshal(responseJson);
            return replied.getArguments();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
