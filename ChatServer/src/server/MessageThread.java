/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tu4nFPT
 */
class MessageThread extends Thread{
     private BufferedReader in;
    private PrintStream out;
    private ArrayList<PrintStream> writers = new ArrayList<>();
    public MessageThread(Socket socket, ArrayList<PrintStream> writers) {
        this.writers = writers;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());      
            writers.add(out);
        } catch (IOException ex) {
            Logger.getLogger(MessageThread.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
     @Override
    public void run(){     
        while(true){
            String message;
            try {
                message = in.readLine();
                for (int i = 0; i < writers.size(); i++) {
                    writers.get(i).println(message);
                }
            } catch (IOException ex) {
                Logger.getLogger(MessageThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
