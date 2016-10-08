
package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tu4nFPT
 */
public class ClientRead extends Thread{
    private final BufferedReader in; 
    private final String userName;
    private  StyledDocument doc;
    private  SimpleAttributeSet align;
    public ClientRead(Client client,String userName, BufferedReader in) {       
        this.userName = userName;
        this.in = in;
        doc = client.txtScreen.getStyledDocument();
        client.txtScreen.setText("Welcome to Simple Chat ! ");
        align = new SimpleAttributeSet();
    }
    @Override
    public void run(){
        String message;        
        while(true){
            try {
                message = in .readLine();
                if(message.substring(0, userName.length()).trim().equalsIgnoreCase(userName)){
                    StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);
                }else{
                    StyleConstants.setAlignment(align, StyleConstants.ALIGN_LEFT);                           
                }   
                doc.insertString(doc.getLength(),"\n\n" + message, null); // "\n" must be in the head omg
                doc.setParagraphAttributes(doc.getLength() + 1, 1, align, false);              
            } catch (IOException | BadLocationException ex) {
                Logger.getLogger(ClientRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
