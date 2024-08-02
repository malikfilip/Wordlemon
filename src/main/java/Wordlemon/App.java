package Wordlemon;


import Panels.GamePanel;
import Panels.WelcomePanel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {
    private static List<Pokemon> pokedex = new ArrayList<Pokemon>();

    private static void createDex(File[] files) {
        ObjectMapper objMapper = new ObjectMapper();
        if(files != null) {
            for(File file: files) {
                File fileJson = new File(file.toString());
                try {
                    JsonNode jsonInfo = objMapper.readTree(fileJson);
                    pokedex.add(new Pokemon(jsonInfo.get("name").asText(),jsonInfo.get("generation").asInt(), jsonInfo.at("/forms/0/types/0").asText(), jsonInfo.at("/forms/0/types/1").asText(),
                            jsonInfo.at("/forms/0/weight").asDouble(),jsonInfo.at("/forms/0/dimensions/height").asDouble(),jsonInfo.get("dex").asInt()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static {
        //Get all Json names in array files[]
        File directory = new File("src/main/resources/pokeinfo");
        File[] files = directory.listFiles();
        createDex(files);
    }
    private Pokemon activeGuess;

    private Font appFont;
    private CardLayout cLayout;
    private JPanel appPanel;


    App(){

        this.setTitle("Wordlemon");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);



        cLayout = new CardLayout();
        appPanel = new JPanel(cLayout);

        wordleGuess();
        WelcomePanel welcomePage = new WelcomePanel(this);
        GamePanel gamePage = new GamePanel(this);
        appPanel.add(welcomePage,"welcomeCard");
        appPanel.add(gamePage,"gameCard");

        this.add(appPanel);

        this.setVisible(true);
    };

    public void switchCard(String sceneKey){
        cLayout.show(appPanel,sceneKey);
    }
    public int dexSize(){
        return pokedex.size();
    }
    public void dexPrint(){
        for(Pokemon poke : pokedex){
            System.out.printf("Pokemon %d, %s[%s - %s] - Generation %d - H = %.2f m, W = %.2f kg\n",poke.getDexNo(),poke.getName(),poke.getType1(),poke.getType2(),poke.getGeneration()
            ,poke.getHeight(),poke.getWeight());
        }
    }
    public void wordleGuess(){
        int guessId = (int)(Math.random() * pokedex.size());
        activeGuess = pokedex.get(guessId);
    }
    public Pokemon getGuess(){
        return activeGuess;
    }

}
