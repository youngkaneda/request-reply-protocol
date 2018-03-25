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
import java.net.SocketException;
import java.util.HashMap;
import org.json.JSONObject;

/**
 *
 * @author juan
 */
public class Server {

    private HashMap<Integer, RemoteRef> map;
    private DatagramSocket server;

    public Server(int port) throws SocketException {
        server = new DatagramSocket(port);
        map = new HashMap();
    }

    public byte[] getRequest() throws SocketException, IOException, Base64DecodingException {
        DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
        server.receive(pack);
        //
        JSONObject object = new JSONObject(new String(pack.getData()));
        //retorna o id do requisitante e mapeia seu endereço ao id
        //assim o sendReply saberá para quem retornar a mensagem de acordo com o id que está dentro do byte[]
        int id = object.getInt("requestId");
        RemoteRef requester = new RemoteRef(pack.getAddress().getHostName(), pack.getPort());
        map.put(id, requester);
        //somente as informações necessárias ao processamento são retornadas
        //já que a classe não possui mais uma referência a um objeto RemoteRef
        return pack.getData();
    }

    public void sendReply(byte[] reply) throws IOException, ClassNotFoundException {
        JSONObject replyJson = new JSONObject(new String(reply));
	//
        int requester = replyJson.getInt("requestId");
        //
        RemoteRef remoteRef = map.get(requester);
        //
        server.send(new DatagramPacket(replyJson.toString().getBytes(), replyJson.toString().getBytes().length,
                InetAddress.getByName(remoteRef.getHost()), remoteRef.getPort()));
        //
        map.remove(requester);
    }
    
    public RemoteRef getRequester(int id) {
        return map.get(id);
    }

}
