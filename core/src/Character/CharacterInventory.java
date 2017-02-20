package Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Races.Race;

public class CharacterInventory {

    private Array<Character> chatacterPack;

    public CharacterInventory() {
        this.chatacterPack = new Array<Character>();
    }

    public Array<Character> getChatacterPack() {
        return chatacterPack;
    }

    public void setChatacterPack(Array<Character> characterPack) {
        this.chatacterPack = characterPack;
    }

    public void addToInventory(Character character){
         chatacterPack.add(character);
    }

    public  void readFile() throws IOException {
        File file = new File("characterInventory.txt");
        file.createNewFile(); // if file already exists will do nothing

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while( line != null && line !="" ) {
            String[] itemArray = line.split("\\|",-1);
            String[] abilityArrTmp = itemArray[itemArray.length-1].split(",",-1);
            int[] abilityArr = new int[abilityArrTmp.length];
            for (int i = 0; i < abilityArrTmp.length; i++) {
                try {
                    abilityArr[i] = Integer.parseInt(abilityArrTmp[i]);
                } catch (NumberFormatException nfe) {
                    //NOTE: write something here if you need to recover from formatting errors
                }
            }
            addToInventory(new Character(itemArray[0], Integer.parseInt(itemArray[1]), Race.RaceType.valueOf(itemArray[2]), abilityArr));
            line = reader.readLine();
        }
        reader.close();
    }

    public void saveToFile(){
        FileHandle file = Gdx.files.local("characterInventory.txt");
        file.write(false);
        for (Character i : this.chatacterPack){
            String str  = i.getName()  +"|" +  i.getLevel() +"|" + i.getRaceType().toString() + "|"
                            +i.getStrength() + "," + i.getDexterity() + "," + i.getConstitution() + ","
                            +i.getWisdom() + "," +i.getIntelligence() + ","+i.getCharisma() + ","
                            +i.getHitPoints() + "," +i.getArmorClass() + "," +i.getAttackBonus() + "," +i.getDamageBonus() + "\r\n";
            file.writeString(str,true);
        }
    }

}
