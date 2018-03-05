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
    private int _operationId;
    private byte[] _arguments;
    
    public Message() {};
    
    public Message(int _messageType, int _requestId, int _methodId, byte[] _arguments) {
        this._messageType = _messageType;
        this._requestId = _requestId;
        this._operationId = _methodId;
        this._arguments = _arguments;
    }

    public int getMessageType() {
        return _messageType;
    }

    public void setMessageType(int messageType) {
        this._messageType = messageType;
    }

    public int getRequestId() {
        return _requestId;
    }

    public void setRequestId(int requestId) {
        this._requestId = requestId;
    }

    public int getOperationId() {
        return _operationId;
    }

    public void setOperationId(int methodId) {
        this._operationId = methodId;
    }

    public byte[] getArguments() {
        return _arguments;
    }

    public void setArguments(byte[] arguments) {
        this._arguments = arguments;
    }

    @Override
    public String toString() {
        return "Message{" + "_messageType=" + _messageType + ", _requestId=" + _requestId + ", _methodId=" + _operationId + ", _arguments=" + _arguments + '}';
    }
    
}
