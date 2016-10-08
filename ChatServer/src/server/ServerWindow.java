/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author tu4nFPT
 */
public class ServerWindow extends JFrame{
    
    private static final int BACKGROUND_WIDTH = 600;
    private static final int BACKGROUND_HEIGHT = 400;
    private String ipAddress;
    Image background = null;
    Image backBufferImage;
    public ServerWindow(String ipAddress){   
        this.ipAddress = ipAddress;
        backBufferImage = new BufferedImage(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);        
        try {
            background = ImageIO.read(getClass().getResource("/image/background.png"));
        } catch (IOException e) {
        }     
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });               
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true); 
    }
    
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics backBufferGraphics = backBufferImage.getGraphics();
        backBufferGraphics.drawImage(background, 0, 0, 600, 400, null);
        g.drawImage(backBufferImage, 0, 0, 600, 400, null);
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman",Font.BOLD,30));
        g.drawString("Welcome to connect to Server !", 60, 120);
        g.drawString("The Port Number : 1234", 60, 210);
        g.drawString("Server's IP Address : " + ipAddress, 60, 300);
    }
}
