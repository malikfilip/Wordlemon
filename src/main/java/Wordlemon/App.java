package Wordlemon;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
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
    App(){};
    public int dexSize(){
        return pokedex.size();
    }
    public void dexPrint(){
        for(Pokemon poke : pokedex){
            System.out.printf("Pokemon %d, %s[%s - %s] - Generation %d - H = %.2f m, W = %.2f kg\n",poke.getDexNo(),poke.getName(),poke.getType1(),poke.getType2(),poke.getGeneration()
            ,poke.getHeight(),poke.getWeight());
        }
    }
}
