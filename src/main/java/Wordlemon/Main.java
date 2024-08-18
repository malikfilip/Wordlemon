package Wordlemon;

import Panels.GamePanel;
import Panels.WelcomePanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, FontFormatException {
        createCustomFont();
        App Wordle = new App();
    }

    static void createCustomFont() throws IOException, FontFormatException {
        final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final File fontFile = new File("src/main/resources/font.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GE.registerFont(customFont);
    }
}
