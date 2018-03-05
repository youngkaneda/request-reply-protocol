/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import org.json.JSONObject;

/**
 *
 * @author juan
 */
public class Server {

    public byte[] getRequest() throws SocketException, IOException, Base64DecodingException {
        DatagramSocket server = new DatagramSocket(1025);
        DatagramPacket pack = new DatagramPacket(new byte[1024], new byte[1024].length);
        server.receive(pack);
        //
        String message = new String(pack.getData());
        //
        JSONObject object = new JSONObject(message);
        Message request = Marshaller.unMarshal(object);
        //
        ByteBuffer argBuffer = ByteBuffer.wrap(request.getArguments());
        //
        ByteBuffer result = ByteBuffer.allocate(4);
        if (request.getMethodId() == 1) {
            result.putInt(sum(argBuffer.getInt(), argBuffer.getInt()));
        }
        //
        Message response = new Message(1, request.getRequestId(), -1, result.array());
        //
        RemoteRef requester = new RemoteRef(pack.getAddress().toString().replaceAll("/", ""), pack.getPort());
        //
        response.setRemoteRef(requester);
        //
        byte[] responseArr = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        //
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(response);
            oos.flush();
            responseArr = bos.toByteArray();
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (oos != null) {
                oos.close();
            }
        }
        //
        return responseArr;
    }

    public void sendReply(byte[] reply) throws IOException, ClassNotFoundException {
        Message obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        //
        try {
            bis = new ByteArrayInputStream(reply);
            ois = new ObjectInputStream(bis);
            obj = (Message) ois.readObject();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
        if (obj == null) {
            return;
        }
        //
        DatagramSocket server = new DatagramSocket();
        JSONObject replyJson = Marshaller.marshal(obj);
        //
        server.send(new DatagramPacket(replyJson.toString().getBytes(), replyJson.toString().getBytes().length,
                InetAddress.getByName(obj.getRemoteRef().getHost()), obj.getRemoteRef().getPort()));
    }

    public int sum(int x, int y) {
        return Integer.sum(x, y);
    }
}
