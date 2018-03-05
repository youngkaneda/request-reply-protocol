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
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("messageType", message.getMessageType());
        jsonMessage.put("requestId", message.getRequestId());
        jsonMessage.put("operationId", message.getOperationId());
        jsonMessage.put("arguments", Base64.encode(message.getArguments()));
        return jsonMessage;
    }

    public static Message unMarshal(JSONObject object) throws Base64DecodingException {
        Message message = new Message();
        message.setMessageType(object.getInt("messageType"));
        message.setOperationId(object.getInt("operationId"));
        message.setRequestId(object.getInt("requestId"));
        message.setArguments(Base64.decode(object.getString("arguments")));
        return message;
    }
}
