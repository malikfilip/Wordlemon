package Panels;

import Wordlemon.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {

   public WelcomePanel(App parentApp){
       this.setLayout(null);

       JButton startButton = new JButton("START");
       startButton.setBounds(170,350,150,50);
       startButton.setBackground(Color.RED);
       startButton.setForeground(Color.white);
       startButton.setBorder(BorderFactory.createLineBorder(Color.PINK,7));
       startButton.setFocusable(false);
       this.add(startButton);

       startButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                parentApp.switchCard("gameCard");

           }
       });


   }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon("src/main/resources/homepage.png").getImage();
        g.drawImage(img,0,0,null );
    }
}
