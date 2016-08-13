/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Rupa.SocketProgramming.ClientListener;

import com.Rupa.SocketProgramming.Client.Client;
import com.Rupa.SocketProgramming.Clienthandler.ClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author ACER
 */
public class ClientListener extends Thread {
   private Socket socket;
    private Client client;
    private ClientHandler handler;

    public ClientListener(Socket socket, ClientHandler handler) {

        this.socket = socket;
        this.handler = handler;
    }

    @Override
    public void run() {

        try {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Wel to Chat Server");
            ps.print("Enter your name : ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = reader.readLine();
            System.out.println("Hello " +name);

            client = new Client(name, socket);
            handler.addClient(client);
            handler.broadcastMessage(client.getUserName() + " has joined the group.");

            while (!isInterrupted()) {
                String msg = reader.readLine();
                String[] tokens = msg.split(";;");
                if (tokens[0].equalsIgnoreCase("pm")) {
                    if (tokens.length > 2) {
                        handler.privateMessage(tokens[1], "PM from " + client.getUserName() + " >> " + tokens[2]);
                    } else{
                        ps.println("Invalid input");
                    }
                } else {
                    handler.broadcastMessage(client.getUserName() + " >> " + msg);
                }
            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
