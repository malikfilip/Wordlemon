package Panels;

import Utils.Guess;
import Utils.Submit;
import Wordlemon.App;
import Wordlemon.Pokemon;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class GamePanel extends JPanel {

    private Pokemon guess;
    private ImageIcon guessIcon;
    private Submit submitGuess;
    private int turn = 1;
    private String consoleText;
    //Game header
    JPanel topPanel = new JPanel(new GridLayout(1,2));
    JPanel turnPanel = new JPanel(new GridBagLayout());
    JLabel turnLabel = new JLabel();
    JLabel pokemonPicture = new JLabel();
    JPanel guessPanel = new JPanel(new GridBagLayout());
    JLabel guessLabel = new JLabel();

    //Game content
    JPanel midPanel = new JPanel(new FlowLayout());
    JLabel guessGeneration,guessType1,guessType2,guessHeight,guessWeight;
    JLabel generationTitle,type1Title,type2Title,heightTitle,weightTitle;
    ImageIcon correctIcon,wrongIcon,foundIcon,higherIcon,lowerIcon;
    JPanel buttonsPanel = new JPanel(new GridBagLayout());
    JButton submitButton, restartButton, exitButton;
    //GameConsole
    JPanel botPanel = new JPanel(new BorderLayout());
    JTabbedPane consolePane = new JTabbedPane();
    JTextArea console;
    JScrollPane scroll;



    public GamePanel(App parentApp){

        guess = parentApp.getGuess();
        submitGuess = new Submit();
        guessIcon = App.getPokemonIcon(guess.getName(),64,64);

        //GUI

        this.setLayout(new BorderLayout());
        this.add(topPanel,BorderLayout.NORTH);
        this.add(midPanel,BorderLayout.CENTER);
        this.add(botPanel,BorderLayout.SOUTH);
        //Top panel
        topPanel.setBackground(Color.CYAN);
        guessPanel.setBackground(Color.RED);

        topPanel.setPreferredSize(new Dimension(topPanel.getWidth(),70));
        botPanel.setPreferredSize(new Dimension(botPanel.getWidth(),100));
        //-Turn panel
        turnLabel.setText("Turn " + turn);
        turnLabel.setFont(new Font("Drip October",Font.PLAIN,20));
        turnPanel.add(turnLabel);
        //-Guess panel
        guessLabel.setText(guess.getName());
        guessLabel.setFont(new Font("Drip October",Font.PLAIN,15));
        guessPanel.add(guessLabel);
        pokemonPicture.setIcon( guessIcon);
        guessPanel.add(pokemonPicture);

        topPanel.add(turnPanel);
        topPanel.add(guessPanel);
        //Mid panel
        guessInit();

        midPanel.add(generationTitle);
        midPanel.add(type1Title);
        midPanel.add(type2Title);
        midPanel.add(heightTitle);
        midPanel.add(weightTitle);

        midPanel.add(guessGeneration);
        midPanel.add(guessType1);
        midPanel.add(guessType2);
        midPanel.add(guessHeight);
        midPanel.add(guessWeight);

        midPanel.add(buttonsPanel);

        //Bottom panel
        consoleInit();
        botPanel.add(consolePane);

        createIconActions();
        createButtonActions(parentApp);


    }

    private void guessInit(){
        foundIcon = new ImageIcon("src/main/resources/common/guessFound.png");
        correctIcon = new ImageIcon("src/main/resources/common/guessCorrect.png");
        wrongIcon = new ImageIcon("src/main/resources/common/guessWrong.png");
        higherIcon = new ImageIcon("src/main/resources/common/guessHigher.png");
        lowerIcon = new ImageIcon("src/main/resources/common/guessLower.png");

        generationTitle = new JLabel("Generation",SwingConstants.CENTER);
        generationTitle.setPreferredSize(new Dimension(80,40));
        generationTitle.setFont(new Font("Drip October",Font.PLAIN,12));
        type1Title = new JLabel("Type 1",SwingConstants.CENTER);
        type1Title.setPreferredSize(new Dimension(80,40));
        type1Title.setFont(new Font("Drip October",Font.PLAIN,12));
        type2Title = new JLabel("Type 2",SwingConstants.CENTER);
        type2Title.setPreferredSize(new Dimension(80,40));
        type2Title.setFont(new Font("Drip October",Font.PLAIN,12));
        heightTitle = new JLabel("Height",SwingConstants.CENTER);
        heightTitle.setPreferredSize(new Dimension(80,40));
        heightTitle.setFont(new Font("Drip October",Font.PLAIN,12));
        weightTitle = new JLabel("Weight",SwingConstants.CENTER);
        weightTitle.setPreferredSize(new Dimension(80,40));
        weightTitle.setFont(new Font("Drip October",Font.PLAIN,12));

        guessGeneration = new JLabel("CLICK");
        guessType1 = new JLabel("CLICK");
        guessType2 = new JLabel("CLICK");
        guessWeight = new JLabel("CLICK");
        guessHeight = new JLabel("CLICK");

        guessGeneration.setIcon(correctIcon);
        guessGeneration.setVerticalTextPosition(JLabel.BOTTOM);
        guessGeneration.setHorizontalTextPosition(JLabel.CENTER);

        guessType1.setIcon(correctIcon);
        guessType1.setVerticalTextPosition(JLabel.BOTTOM);
        guessType1.setHorizontalTextPosition(JLabel.CENTER);

        guessType2.setIcon(correctIcon);
        guessType2.setVerticalTextPosition(JLabel.BOTTOM);
        guessType2.setHorizontalTextPosition(JLabel.CENTER);

        guessWeight.setIcon(correctIcon);
        guessWeight.setVerticalTextPosition(JLabel.BOTTOM);
        guessWeight.setHorizontalTextPosition(JLabel.CENTER);

        guessHeight.setIcon(correctIcon);
        guessHeight.setVerticalTextPosition(JLabel.BOTTOM);
        guessHeight.setHorizontalTextPosition(JLabel.CENTER);

        submitButton = new JButton("Guess");
        submitButton.setPreferredSize(new Dimension(100,100));

        submitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        submitButton.setForeground(Color.white);
        submitButton.setBackground(new Color(0x55bb99));
        submitButton.setFocusable(false);


        restartButton = new JButton("Reset");
        restartButton.setPreferredSize(new Dimension(100,100));
        restartButton.setBorder(BorderFactory.createRaisedBevelBorder());
        restartButton.setForeground(Color.white);
        restartButton.setBackground(Color.darkGray);
        restartButton.setFocusable(false);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(100,100));
        exitButton.setBackground(new Color(0x490000));
        exitButton.setFocusable(false);
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        exitButton.setForeground(Color.white);



        buttonsPanel.setPreferredSize(new Dimension(500,150));
        buttonsPanel.add(exitButton);
        buttonsPanel.add(Box.createHorizontalStrut(30));
        buttonsPanel.add(submitButton);
        buttonsPanel.add(Box.createHorizontalStrut(30));
        buttonsPanel.add(restartButton);
    }

    private void consoleInit(){

        console = new JTextArea();
        console.setBackground(Color.BLACK);
        console.setForeground(Color.GREEN);
        console.setEditable(false);

        scroll = new JScrollPane(console);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
        writeToConsole(guess);


        consolePane.add("Console",scroll);



    }

    private void createIconActions(){
        guessGeneration.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeIconHigherLower(guessGeneration);
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                generationTitle.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                generationTitle.setForeground(Color.BLACK);
            }
        });
        guessType1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeIconCorrectWrong(guessType1);
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                type1Title.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                type1Title.setForeground(Color.BLACK);
            }
        });
        guessType2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeIconCorrectWrong(guessType2);
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                type2Title.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                type2Title.setForeground(Color.BLACK);
            }
        });
        guessHeight.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeIconHigherLower(guessHeight);
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                heightTitle.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                heightTitle.setForeground(Color.BLACK);
            }
        });
        guessWeight.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeIconHigherLower(guessWeight);
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                weightTitle.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                weightTitle.setForeground(Color.BLACK);
            }
        });
    }

    private void changeIconHigherLower(JLabel label){
        switch (label.getText()){
            case "CLICK", "Correct":
                label.setText("Higher");
                label.setIcon(higherIcon);
                break;
            case "Higher":
                label.setText("Lower");
                label.setIcon(lowerIcon);
                break;
            case "Lower":
                label.setText("Correct");
                label.setIcon(correctIcon);
                break;

        }
    }

    private void changeIconCorrectWrong(JLabel label){
        switch (label.getText()){
            case "Wrong":
                label.setIcon(correctIcon);
                label.setText("Correct");
                break;
            case "Correct","CLICK":
                label.setIcon(wrongIcon);
                label.setText("Wrong");
                break;
        }
    }

    private void createButtonActions(App parentApp){
        submitButton.addActionListener(al->{

            /*System.out.println("Generation " + guessGeneration.getText() + " Type 1 " + guessType1.getText() + " Type 2 " + guessType2.getText()
               +" Weight " + guessWeight.getText() + " Height " + guessHeight.getText());*/
            if(stringToEnum(guessGeneration.getText()) != Guess.FOUND)submitGuess.evaluateGeneration(guess.getGeneration(),stringToEnum(guessGeneration.getText()));
            if(stringToEnum(guessType1.getText()) != Guess.FOUND)submitGuess.evaluateType1(guess.getType1(),stringToEnum(guessType1.getText()));
            if(stringToEnum(guessType2.getText()) != Guess.FOUND)submitGuess.evaluateType2(guess.getType2(),stringToEnum(guessType2.getText()));
            if(stringToEnum(guessWeight.getText()) != Guess.FOUND)submitGuess.evaluateWeight(guess.getWeight(),stringToEnum(guessWeight.getText()));
            if(stringToEnum(guessHeight.getText()) != Guess.FOUND)submitGuess.evaluateHeight(guess.getHeight(),stringToEnum(guessHeight.getText()));
            parentApp.dexSet(submitGuess.getDex());
            parentApp.switchCard("guessCard");
            //updateGame();





        });
        exitButton.addActionListener(al->{
            System.exit(0);
        });
        restartButton.addActionListener(al->{
            parentApp.switchCard("welcomeCard");
        });
    }
    private void writeToConsole (Pokemon refPokemon){
        console.append("Turn " + turn);
        console.append(" - " + refPokemon.getName() + " [ " + refPokemon.getDexNo() + " ]" + "\nWeight : "+refPokemon.getWeight()
                + " kg\nHeight : " + refPokemon.getHeight() + " m\nTYPING : " + refPokemon.getType1() +" - " + ((Objects.equals(refPokemon.getType2(), "")) ? "NONE" : refPokemon.getType2()));
        //Console's newest text should be shown at the bottom of the console
        console.setCaretPosition(console.getDocument().getLength());

    }

    private Guess stringToEnum(String text){
        return switch (text) {
            case "Correct", "CLICK" -> Guess.CORRECT;
            case "Wrong" -> Guess.WRONG;
            case "Higher" -> Guess.HIGHER;
            case "Lower" -> Guess.LOWER;
            default -> Guess.FOUND;
        };
    }
    public void updateGame(Pokemon newGuess){
        guess = newGuess;

        int mask = submitGuess.getMask();
        if((mask & 0b10000) != 0){
            //if correct answer was found
            guessGeneration.setIcon(foundIcon);
            guessGeneration.setText(String.valueOf(submitGuess.getCorrectGeneration()));
            guessGeneration.setForeground(Color.GREEN);
        }
        else{
            guessGeneration.setIcon(correctIcon);
            guessGeneration.setText("CLICK");
        }
        if((mask & 0b01000) != 0){
            //if correct answer was found
            guessType1.setIcon(foundIcon);
            guessType1.setText(submitGuess.getCorrectType1());
            guessType1.setForeground(Color.GREEN);
        }
        else{
            guessType1.setIcon(correctIcon);
            guessType1.setText("CLICK");
        }
        if((mask & 0b00100) != 0){
            //if correct answer was found
            guessType2.setIcon(foundIcon);
            guessType2.setText(submitGuess.getCorrectType2());
            guessType2.setForeground(Color.GREEN);
        }
        else{
            guessType2.setIcon(correctIcon);
            guessType2.setText("CLICK");
        }
        if((mask & 0b00010) != 0){
            //if correct answer was found
            guessWeight.setIcon(foundIcon);
            guessWeight.setText(String.valueOf(submitGuess.getCorrectWeight()));
            guessWeight.setForeground(Color.GREEN);
        }
        else{
            guessWeight.setIcon(correctIcon);
            guessWeight.setText("CLICK");
        }
        if((mask & 0b00001) != 0){
            //if correct answer was found
            guessHeight.setIcon(foundIcon);
            guessHeight.setText(String.valueOf(submitGuess.getCorrectHeight()));
            guessHeight.setForeground(Color.GREEN);
        }
        else{
            guessHeight.setIcon(correctIcon);
            guessHeight.setText("CLICK");
        }

        turn++;
        turnLabel.setText("Turn " + turn);
        guessLabel.setText(guess.getName());


        pokemonPicture.setIcon(App.getPokemonIcon(guess.getName(),64,64));

        console.append("\n");
        writeToConsole(guess);
        System.out.println(submitGuess.progress() + "possible guesses");

    }

}
