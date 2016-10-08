/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 *
 * @author tu4nFPT
 */
public class Server{

    /**
     * @param args the command line arguments
     */
    private static final int PORT = 1234;
    private static ArrayList<PrintStream> writers = new ArrayList<>();
    
    public static void main(String[] args) {
        InetAddress ip = getLocalAddress();
        ServerWindow serverWindow = new ServerWindow(ip.getHostAddress());
        try {
            System.out.println("Sever is listening in port 1234 !");
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    MessageThread messagethread = new MessageThread(socket, writers);
                    messagethread.start();
                }
            }
        } catch (IOException ex) {
        }
    }

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while( b.hasMoreElements()){
                for ( InterfaceAddress f : b.nextElement().getInterfaceAddresses())
                    if ( f.getAddress().isSiteLocalAddress())
                        return f.getAddress();
            }
        } catch (SocketException e) {
        }
        return null;
    }
    
}
