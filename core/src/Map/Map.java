package Map;


import com.badlogic.gdx.math.Vector2;
import com.chaowang.ddgame.PublicParameter;

import java.util.ArrayList;
import java.util.HashMap;

import Items.Item;
import Character.Character;

public class Map {

    private int level = 1;
    private int size;
    private String name;
    private EntryDoor entryDoor;
    private ExitDoor exitDoor;
    private ArrayList<Wall> wallLocationList;
    private HashMap<Vector2,Item> itemLocationList;
    private HashMap<Vector2,Character> friendLocationList;
    private HashMap<Vector2,Character> enemyLocationList;

    private int[][]  locationMatrix;

    public  Map(){
        this.level = 0 ;
        this.name = "";
        this.size = 0;
        locationMatrix = new int[1][1];
        locationMatrix[0][0] = 0;
        itemLocationList = new HashMap<Vector2, Item>();
        friendLocationList = new HashMap<Vector2, Character>();
        enemyLocationList = new HashMap<Vector2, Character>();
    }

    public Map(int level, int size, String name) {
        this.level = level;
        this.size = size;
        this.name = name;
        locationMatrix = new int[size][size];
        for (int i = 0; i < size ;i++){
            for (int j =0; j < size; j++)
                locationMatrix[i][j] = 0;
        }
        itemLocationList = new HashMap<Vector2, Item>();
        friendLocationList = new HashMap<Vector2, Character>();
        enemyLocationList = new HashMap<Vector2, Character>();
    }

    public Map(int level, int size, String name, EntryDoor entryEntryDoor, ExitDoor exitEntryDoor) {
        this(level, size, name);
        this.entryDoor = entryEntryDoor;
        this.exitDoor = exitEntryDoor;
    }

    public void addWallLocationList(int i, int j){
        Wall wall = new Wall(new Vector2(j* PublicParameter.mapPixelSize, i*PublicParameter.mapPixelSize));
        wallLocationList.add(wall);
    }

    public void removeWallLocationList(int i, int j){
        for (int k = 0; k< wallLocationList.size() ; k++) {
            if(wallLocationList.get(k).getPosition().x == j*PublicParameter.mapPixelSize
                    && wallLocationList.get(k).getPosition().y == i*PublicParameter.mapPixelSize ){
                wallLocationList.remove(k);
            }
        }
    }

    public void addItemLocationList(int i, int j, Item item ){
        itemLocationList.put(new Vector2(j * PublicParameter.mapPixelSize, i * PublicParameter.mapPixelSize), item);
    }

    public Item removeItemLocationList(int i, int j){
       return itemLocationList.remove(new Vector2(j * PublicParameter.mapPixelSize, i * PublicParameter.mapPixelSize));
    }

    public void addFriendLocationList(int i, int j, Character character ){
        friendLocationList.put(new Vector2(j * PublicParameter.mapPixelSize, i * PublicParameter.mapPixelSize), character);
    }

    public Character removeFriendLocationList(int i, int j){
        return friendLocationList.remove(new Vector2(j * PublicParameter.mapPixelSize, i * PublicParameter.mapPixelSize));
    }

    public void addEnemyLocationList(int i, int j, Character character ){
        enemyLocationList.put(new Vector2(j * PublicParameter.mapPixelSize, i * PublicParameter.mapPixelSize), character);
    }

    public Character removeEnemyLocationList(int i, int j ){
        return enemyLocationList.remove(new Vector2(j * PublicParameter.mapPixelSize, i * PublicParameter.mapPixelSize));
    }

    public int[][] getLocationMatrix() {
        return locationMatrix;
    }

    public void setSize(int size) {
        this.size = size;
        locationMatrix = new int[size][size];
        for (int i = 0; i < size ;i++){
            for (int j =0; j < size; j++)
                locationMatrix[i][j] = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public EntryDoor getEntryEntryDoor() {
        return entryDoor;
    }

    public void setEntryEntryDoor(EntryDoor entryDoor) {
        this.entryDoor = entryDoor;
    }

    public ExitDoor getExitDoor() {
        return exitDoor;
    }

    public void setExitDoor( ExitDoor exitDoor) {
        this.exitDoor = exitDoor;
    }

    public ArrayList<Wall> getWallLocationList() {
        return wallLocationList;
    }

    public void setWallLocationList(ArrayList<Wall> wallLocationList) {
        this.wallLocationList = wallLocationList;
    }

    public HashMap<Vector2, Item> getItemLocationList() {
        return itemLocationList;
    }

    public void setItemLocationList(HashMap<Vector2, Item> itemLocationList) {
        this.itemLocationList = itemLocationList;
    }

    public HashMap<Vector2, Character> getFriendLocationList() {
        return friendLocationList;
    }

    public void setFriendLocationList(HashMap<Vector2, Character> enemyLocationList) {
        this.friendLocationList = enemyLocationList;
    }

    public int getSize() {
        return size;
    }
}
