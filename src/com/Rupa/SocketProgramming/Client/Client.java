/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Rupa.SocketProgramming.Client;

import java.net.Socket;

/**
 *
 * @author ACER
 */
public class Client {
   private String userName;
    private Socket socket;

    /**
     * Default Constructor
     */
    public Client() {
    }

    public Client(String userName, Socket socket) {
        this.userName = userName;
        this.socket = socket;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    
}
