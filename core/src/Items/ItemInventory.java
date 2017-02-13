package Items;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ItemInventory {
    private ArrayList<Item> itemPack;

    public ItemInventory() {
        this.itemPack = new ArrayList<Item>();
    }

    public ArrayList<Item> getItemPack() {
        return itemPack;
    }

    public void setItemPack(ArrayList<Item> itemPack) {
        this.itemPack = itemPack;
    }

    public boolean addToInventory(Item item){
        return itemPack.add(item);
    }
    public  void readFile() throws IOException {
        File file = new File("itemInventory.txt");
        file.createNewFile(); // if file already exists will do nothing

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while( line != null && line !="" ) {
            String[] itemArray = line.split("\\|",-1);
            addToInventory(new Item(Item.ItemType.valueOf(itemArray[0]), itemArray[1], Integer.parseInt(itemArray[2]), EnchantedAbility.valueOf(itemArray[3])));
            line = reader.readLine();
        }

    }

    public void saveToFile(){
        FileHandle file = Gdx.files.local("itemInventory.txt");
        file.write(false);
        for (Item i : this.itemPack){
            String str  = i.getItemType().toString() +"|" + i.getName() +"|" +  i.getLevel() +"|" + i.getEnchantedAbility().toString() + "\r\n";
            file.writeString(str,true);
        }
    }

}
