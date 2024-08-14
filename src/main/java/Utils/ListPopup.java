package Utils;

import Wordlemon.App;
import Wordlemon.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class ListPopup extends JFrame {

    private String [] columns = {"Name","Gen","Type 1","Type 2", "Height", "Weight"};
    JTable listOfGuesses = new JTable(pokemonParse(),columns);
   //List<Pokemon> newDex
    public ListPopup(JButton button){
        this.setTitle("Pokemon left");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.RED);
        JScrollPane table = new JScrollPane(listOfGuesses);
        this.add(table);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                button.setEnabled(true);
            }
        });
    }

    Object[][] pokemonParse(){
        Object[][] parsedDex = new Object[App.getPokedex().size()][6];
        for(int i = 0; i<App.getPokedex().size();i++){
            Pokemon poke = App.getPokedex().get(i);
            parsedDex[i][0]= poke.getName();
            parsedDex[i][1]= poke.getGeneration();
            parsedDex[i][2]=poke.getType1();
            parsedDex[i][3]=(Objects.equals(poke.getType2(), "")) ? "NONE" : poke.getType2() ;
            parsedDex[i][4]=poke.getWeight() + " m";
            parsedDex[i][5]=poke.getHeight() + " kg";

        }
    return parsedDex;
    }
}
