package Panels;

import Wordlemon.App;
import Wordlemon.Pokemon;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    Pokemon guess;
    ImageIcon guessIcon;
    int turn = 0;

    //Game header
    JPanel topPanel = new JPanel(new GridLayout(1,2));
    JPanel turnPanel = new JPanel(new GridBagLayout());
    JLabel turnLabel = new JLabel();
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
    JPanel botPanel = new JPanel();



    public GamePanel(App parentApp){

        guess = parentApp.getGuess();
        guessIcon = getPokemonIcon(guess.getName());

        //GUI

        this.setLayout(new BorderLayout());
        this.add(topPanel,BorderLayout.NORTH);
        this.add(midPanel,BorderLayout.CENTER);
        this.add(botPanel,BorderLayout.SOUTH);
        //Top panel
        topPanel.setBackground(Color.CYAN);
        botPanel.setBackground(Color.GREEN);
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
        guessPanel.add(new JLabel(guessIcon));

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
        midPanel.add(guessWeight);
        midPanel.add(guessHeight);

        midPanel.add(buttonsPanel);

    }
    private ImageIcon getPokemonIcon(String name){
        System.out.println("src/main/resources/pokeicons/" + name.toUpperCase() + ".png");
        ImageIcon bigIcon = new ImageIcon("src/main/resources/pokeicons/" + name.toUpperCase() + ".png");
        Image content = bigIcon.getImage();
        Image scaledImage = content.getScaledInstance(64,64,Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
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

        guessGeneration.setIcon(wrongIcon);
        guessGeneration.setVerticalTextPosition(JLabel.BOTTOM);
        guessGeneration.setHorizontalTextPosition(JLabel.CENTER);

        guessType1.setIcon(correctIcon);
        guessType1.setVerticalTextPosition(JLabel.BOTTOM);
        guessType1.setHorizontalTextPosition(JLabel.CENTER);

        guessType2.setIcon(foundIcon);
        guessType2.setVerticalTextPosition(JLabel.BOTTOM);
        guessType2.setHorizontalTextPosition(JLabel.CENTER);

        guessWeight.setIcon(higherIcon);
        guessWeight.setVerticalTextPosition(JLabel.BOTTOM);
        guessWeight.setHorizontalTextPosition(JLabel.CENTER);

        guessHeight.setIcon(lowerIcon);
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
}
