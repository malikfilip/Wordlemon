package Wordlemon;


import Panels.GamePanel;
import Panels.GuessPanel;
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

    //Parsing JSONs to List<Pokemon> for commodities during development.
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

    public static ImageIcon getPokemonIcon(String name, int resX,int resY){
        ImageIcon bigIcon = new ImageIcon("src/main/resources/pokeicons/" + name.toUpperCase() + ".png");
        Image content = bigIcon.getImage();
        Image scaledImage = content.getScaledInstance(resX,resY,Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static List<Pokemon> getPokedex(){
        return pokedex;
    }
    static {
        //Get all Json names in array files[]
        File directory = new File("src/main/resources/pokeinfo");
        File[] files = directory.listFiles();
        createDex(files);
    }
    //----------------------------

    private Pokemon activeGuess;

    private Font appFont;
    private CardLayout cLayout;
    private JPanel appPanel;

    private GamePanel gamePage;
    private WelcomePanel welcomePage;
    private GuessPanel guessPage;

    private ImageIcon logo = new ImageIcon("src/main/resources/logowordlemon.png");

    App(){

        this.setTitle("Wordlemon");
        this.setIconImage(logo.getImage());
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);



        cLayout = new CardLayout();
        appPanel = new JPanel(cLayout);

        activeGuess = wordleGuess();
        welcomePage = new WelcomePanel(this);
        gamePage = new GamePanel(this);
        guessPage = new GuessPanel(this);

        appPanel.add(welcomePage,"welcomeCard");
        appPanel.add(gamePage,"gameCard");
        appPanel.add(guessPage,"guessCard");

        this.add(appPanel);

        this.setVisible(true);
    };

    public void switchCard(String sceneKey){
        cLayout.show(appPanel,sceneKey);
    }
    public int dexSize(){
        return pokedex.size();
    }

    public void dexSet(List<Pokemon> newDex){
        pokedex = newDex;
    }
    public void dexPrint(){
        for(Pokemon poke : pokedex){
            System.out.printf("Pokemon %d, %s[%s - %s] - Generation %d - H = %.2f m, W = %.2f kg\n",poke.getDexNo(),poke.getName(),poke.getType1(),poke.getType2(),poke.getGeneration()
            ,poke.getHeight(),poke.getWeight());
        }
    }
    //Maybe implement searching alg but im too lazy rn.
    public Pokemon wordleGuess(){
        if(pokedex.isEmpty()){
            JOptionPane.showMessageDialog(null,"No pokemon matches that criteria !");
            System.exit(-1);
        }
        int guessId = (int)(Math.random() * pokedex.size());
        return pokedex.get(guessId);
    }
    //-----------------------------------------------------
    public Pokemon getGuess(){
        return activeGuess;
    }
    public void setGuess(Pokemon guess){
        activeGuess = guess;
    }

    //Link between card panels using main app as an enabler.
    //Meant to be used from guess panel in order to update game panel.
    public void gameUpdateWrapper (){
        gamePage.updateGame(activeGuess);
    }


}

