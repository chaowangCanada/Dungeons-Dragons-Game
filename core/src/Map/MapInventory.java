package Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


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

    }

    public void saveToFile(){

    }

}
