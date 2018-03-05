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
public class RemoteRef implements Serializable{
    private String host;
    private int port;

    public RemoteRef(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ObjectReference{" + "host=" + host + ", port=" + port + '}';
    }    
}
