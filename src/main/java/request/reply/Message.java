/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request.reply;

import java.io.Serializable;

/**
 *
 * @author juan
 */
public class Message implements Serializable{
    private int _messageType;
    private int _requestId;
    private int _methodId;
    private byte[] _arguments;
    private RemoteRef remoteRef;
    
    public Message() {};
    
    public Message(int _messageType, int _requestId, int _methodId, byte[] _arguments) {
        this._messageType = _messageType;
        this._requestId = _requestId;
        this._methodId = _methodId;
        this._arguments = _arguments;
    }

    public RemoteRef getRemoteRef() {
        return remoteRef;
    }

    public void setRemoteRef(RemoteRef remoteRef) {
        this.remoteRef = remoteRef;
    }

    public int getMessageType() {
        return _messageType;
    }

    public void setMessageType(int _messageType) {
        this._messageType = _messageType;
    }

    public int getRequestId() {
        return _requestId;
    }

    public void setRequestId(int requestId) {
        this._requestId = requestId;
    }

    public int getMethodId() {
        return _methodId;
    }

    public void setMethodId(int methodId) {
        this._methodId = methodId;
    }

    public byte[] getArguments() {
        return _arguments;
    }

    public void setArguments(byte[] arguments) {
        this._arguments = arguments;
    }

    @Override
    public String toString() {
        return "Message{" + "_messageType=" + _messageType + ", _requestId=" + _requestId + ", _methodId=" + _methodId + ", _arguments=" + _arguments + ", remoteRef=" + remoteRef + '}';
    }
}
