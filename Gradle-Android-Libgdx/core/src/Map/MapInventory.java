package Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class MapInventory {

    private Array<Map> mapPack;

    public MapInventory() {
        this.mapPack = new Array<Map>();
    }

    public Array<Map> getChatacterPack() {
        return mapPack;
    }

    public void addToInventory(Map character){
    	mapPack.add(character);
    }

    public Array<String> getCharacterListInfo(){
        Array<String> itemPackInfo = new Array<String>();
        for (int i = 0; i < mapPack.size; i++){
            itemPackInfo.add(i +"-" + mapPack.get(i).getName() + "-"+
            		mapPack.get(i).getSize()+"-"+mapPack.get(i).getLevel());
        }
        return itemPackInfo;
    }

    public  void readFile() throws IOException {
        File file = new File("mapInventory.json");
        file.createNewFile(); // if file already exists will do nothing

        Scanner scanner = new Scanner(file);
        Json json = new Json();
        String context;
        Map map;
        while (scanner.hasNext()){
            context = scanner.nextLine();
            map = json.fromJson(Map.class, context);
            addToInventory(map);
        }
        scanner.close();

    }

    public void saveToFile(){

        FileHandle file = Gdx.files.local("mapInventory.json");
        file.write(false);
        Json json = new Json();
        String context;
        for (Map i : this.mapPack){
            context = json.toJson(i) + System.getProperty("line.separator");
            file.writeString(context,true);
        }
    }

}
