/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
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

    public byte[] doOperation(RemoteRef remoteRef, Message message, int port) throws Base64DecodingException {
        try(DatagramSocket client = new DatagramSocket(port)){
            JSONObject jsonMessage = Marshaller.marshal(message);
            //
            DatagramPacket pack = new DatagramPacket(jsonMessage.toString().getBytes(), jsonMessage.toString().getBytes().length, 
                    InetAddress.getByName(remoteRef.getHost()), remoteRef.getPort());
            client.send(pack);
            //
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            client.receive(response);
            return response.getData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
