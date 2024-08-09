package Panels;

import Wordlemon.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {

   public WelcomePanel(App parentApp){
       this.setBackground(Color.PINK);

       JButton startButton = new JButton("START");
       this.add(startButton);

       startButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                parentApp.switchCard("gameCard");

           }
       });
   }

}
