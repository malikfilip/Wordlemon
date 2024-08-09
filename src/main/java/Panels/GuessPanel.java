package Panels;

import Wordlemon.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessPanel extends JPanel {

    private JButton choose;
    public GuessPanel (App parentApp){
        choose = new JButton("Choose");
        this.add(choose);
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentApp.wordleGuess();
                parentApp.gameUpdateWrapper();
                parentApp.switchCard("gameCard");
            }
        });
    }
}
