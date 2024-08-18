package Utils;

import Wordlemon.App;
import Wordlemon.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Submit {
    private  List<Pokemon> guessList = new ArrayList<Pokemon>();
    //mask of 5 bits rather than 5 boolean values [gen,type1,type2,weight,height]
    private int guessMask = 0b00000;
    private int correctGeneration;
    private double correctWeight,correctHeight;
    private String correctType1,correctType2;

    public Submit(){
        guessList = App.getPokedex();
    }

    public void evaluateGeneration(int guessedGeneration, Guess guessInfo){
        switch (guessInfo){
            case CORRECT:
                guessMask |= 0b10000;
                correctGeneration = guessedGeneration;
                guessList.removeIf(mon -> mon.getGeneration() != guessedGeneration);
                break;
            case HIGHER:
                guessList.removeIf(mon -> mon.getGeneration() <= guessedGeneration);
                break;
            case LOWER:
                guessList.removeIf(mon -> mon.getGeneration() >= guessedGeneration);
                break;
        }
    }
    public void evaluateType1(String guessedType1, Guess guessInfo){
        switch (guessInfo) {
            case CORRECT:
                guessMask |= 0b01000;
                correctType1 = guessedType1;
                guessList.removeIf(mon -> !Objects.equals(mon.getType1(), guessedType1));
                break;
            case WRONG:
                guessList.removeIf(mon -> Objects.equals(mon.getType1(), guessedType1));
                break;
        }
    }
    public void evaluateType2(String guessedType2, Guess guessInfo){
        switch (guessInfo) {
            case CORRECT:
                guessMask |= 0b00100;
                correctType2 = (!Objects.equals(guessedType2, "")) ? guessedType2 : "NONE";
                guessList.removeIf(mon -> (!Objects.equals(mon.getType2(), guessedType2)));
                break;
            case WRONG:
                guessList.removeIf(mon -> Objects.equals(mon.getType2(), guessedType2));
                break;
        }
    }
    public void evaluateWeight(double guessedWeight, Guess guessInfo){
        switch (guessInfo){
            case CORRECT:
                guessMask |= 0b00010;
                correctWeight = guessedWeight;
                guessList.removeIf(mon -> mon.getWeight() != guessedWeight);
                break;
            case HIGHER:
                guessList.removeIf(mon -> mon.getWeight() <= guessedWeight);
                break;
            case LOWER:
                guessList.removeIf(mon -> mon.getWeight() >= guessedWeight);
                break;
        }
    }
    public void evaluateHeight(double guessedHeight, Guess guessInfo){

        switch (guessInfo){
            case CORRECT:
                guessMask |= 0b00001;
                correctHeight = guessedHeight;
                guessList.removeIf(mon -> mon.getHeight() != guessedHeight);
                break;
            case HIGHER:
                guessList.removeIf(mon -> mon.getHeight() <= guessedHeight);
                break;
            case LOWER:
                guessList.removeIf(mon -> mon.getHeight() >= guessedHeight);
                break;
        }
    }

    public List<Pokemon> getDex (){
        return this.guessList;
    }

    public int progress(){
        return guessList.size();
    }

    public int getMask(){
        return guessMask;
    }

    public int getCorrectGeneration() {
        return correctGeneration;
    }

    public double getCorrectWeight() {
        return correctWeight;
    }

    public double getCorrectHeight() {
        return correctHeight;
    }

    public String getCorrectType1() {
        return correctType1;
    }

    public String getCorrectType2() {
        return correctType2;
    }
}
