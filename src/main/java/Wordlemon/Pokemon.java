package Wordlemon;

public class Pokemon {
    private final String name;
    private  final int dexNo;
    private final int generation;
    private final String type1;
    private final String type2;
    private final double weight;
    private final double height;

    public Pokemon(String name, int generation, String type1, String type2, double weight, double height, int dexNo) {
        this.name = name;
        this.generation = generation;
        this.type1 = type1;
        this.type2 = type2;
        this.weight = weight;
        this.height = height;
        this.dexNo = dexNo;
    }

    public String getName() {
        return name;
    }

    public int getGeneration() {
        return generation;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
    public int getDexNo(){
        return dexNo;
    }
}
