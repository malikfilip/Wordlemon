package Panels;

import Utils.ListPopup;
import Wordlemon.App;
import Wordlemon.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GuessPanel extends JPanel {

    private Pokemon guessPokemon;
    private ImageIcon guessIcon;
    private JLabel title,pokemon;

    private JButton okButton,newButton,listButton;

    public GuessPanel (App parentApp){

        gridInit();

        listButton.addActionListener(al->{
            listButton.setEnabled(false);
            ListPopup list = new ListPopup(listButton);
        });

        newButton.addActionListener(al->{
            guessPokemon = parentApp.wordleGuess();
            guessIcon = App.getPokemonIcon(guessPokemon.getName(),256,256);
            pokemon.setIcon(guessIcon);
        });

        okButton.addActionListener(al->{
            parentApp.setGuess(guessPokemon);
            parentApp.gameUpdateWrapper();
            parentApp.switchCard("gameCard");
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                guessPokemon = parentApp.getGuess();
                guessIcon = App.getPokemonIcon(guessPokemon.getName(),256,256);
                pokemon.setIcon(guessIcon);

                title.setText(guessPokemon.getName());

                listButton.setText(parentApp.dexSize() + " POKEMON AVAILABLE");
            }
        });
    }

    private void gridInit(){

        pokemon = new JLabel();
        title = new JLabel();
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.setBackground(new Color(149,52,235));


        title.setFont(new Font("Drip October",Font.PLAIN,30));
        title.setForeground(Color.CYAN);

        //Setting up the gridbagslayout.
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gridPanel.add(title,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gridPanel.add(pokemon,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100,100));
        okButton.setBackground(Color.BLUE);
        okButton.setForeground(Color.WHITE);
        gridPanel.add(okButton,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        newButton = new JButton("NEW");
        newButton.setPreferredSize(new Dimension(100,100));
        newButton.setBackground(Color.BLUE);
        newButton.setForeground(Color.white);
        gridPanel.add(newButton,gbc);

        listButton = new JButton();
        listButton.setBackground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20,0,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        gridPanel.add(listButton,gbc);
        //------------------------------------------------------

        this.add(gridPanel);

    }

}
