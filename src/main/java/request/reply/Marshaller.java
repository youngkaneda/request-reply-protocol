/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.json.JSONObject;

/**
 *
 * @author juan
 */
public class Marshaller {

    public Marshaller() {};
    
    public static JSONObject marshal(Message message) {
        //
        JSONObject jsonRemoteRef = new JSONObject();
        jsonRemoteRef.put("host", message.getRemoteRef().getHost());
        jsonRemoteRef.put("port", message.getRemoteRef().getPort());
        //
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("message_type", message.getMessageType());
        jsonMessage.put("request_id", message.getRequestId());
        jsonMessage.put("method_id", message.getMethodId());
        jsonMessage.put("arguments", Base64.encode(message.getArguments()));
        jsonMessage.put("remote_ref", jsonRemoteRef);

        return jsonMessage;
    }

    public static Message unMarshal(JSONObject object) throws Base64DecodingException {
        Message message = new Message();
        message.setMessageType(object.getInt("message_type"));
        message.setMethodId(object.getInt("method_id"));
        message.setRequestId(object.getInt("request_id"));
        message.setArguments(Base64.decode(object.getString("arguments")));
        //
        RemoteRef remoteRef = new RemoteRef(object.getJSONObject("remote_ref").getString("host"),
                object.getJSONObject("remote_ref").getInt("port"));
        //
        message.setRemoteRef(remoteRef);

        return message;
    }
}
