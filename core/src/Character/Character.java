package Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

import Classes.Class.ClassType;
import Items.*;
import Races.Race.RaceType;

public class Character {

	private ClassType classType;
	private RaceType raceType;
	private String name;
	private int level;
	
	private Abilities abilities;
	private int attackBonus;
	private int damageBonus;
	private int hitPoints;
	private int armorClass;
    private Texture texture;

    private HashMap<Item.ItemType,Item> equipment;


	public Character(){
		this("Default");
	};
	
	public Character(String name) {
		this(name, 1, RaceType.HUMAN);
	}

	public Character(String name, int level, RaceType raceType) {
		this.setName(name);
		this.classType = ClassType.FIGHTER;
		this.raceType  = raceType;
		this.hitPoints = 10;
		this.attackBonus =10;
		this.damageBonus =10;
		this.armorClass = 10;
		this.abilities = new Abilities(10);
		this.level = level;
        this.equipment = new HashMap<Item.ItemType, Item>();
        updateTexture(raceType);
    }

    private void updateTexture(RaceType raceType) {
        switch (raceType){
            case HUMAN:
                texture = new Texture(Gdx.files.internal("android/assets/races/human.jpg"));
                break;
            case DWARF:
                texture = new Texture(Gdx.files.internal("android/assets/races/dwarf.jpg"));
                break;
            case ELF:
                texture = new Texture(Gdx.files.internal("android/assets/races/elf.jpg"));
                break;
            case ORC:
                texture = new Texture(Gdx.files.internal("android/assets/races/orc.jpg"));
                break;
            case TAUREN:
                texture = new Texture(Gdx.files.internal("android/assets/races/tauren.jpg"));
                break;
            case TROLL:
                texture = new Texture(Gdx.files.internal("android/assets/races/troll.jpg"));
                break;
            case UNDEAD:
                texture = new Texture(Gdx.files.internal("android/assets/races/undead.jpg"));
                break;
        }
    }

    public boolean nextRace(){
        if(raceType.getIndex() >= 6 ){
            return false;
        }
        else{
            this.raceType = raceType.getRaceType(this.raceType.getIndex()+1);
            updateTexture(this.raceType);
            return true;
        }
    }

    public boolean previousRace(){
        if(raceType.getIndex() <=0 ){
            return false;
        }
        else{
            this.raceType = raceType.getRaceType(this.getRaceType().getIndex() -1);
            updateTexture(this.raceType);
            return true;
        }
    }

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public HashMap<Item.ItemType, Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(HashMap<Item.ItemType, Item> equipment) {
        this.equipment = equipment;
    }

    public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int defense) {
		this.armorClass = defense;
	}

	public int getAttackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	public int getDamageBonus() {
		return damageBonus;
	}

	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void decrementHP(int hp) {
		hitPoints -=  hp;
	}


	public void setHP(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getHP() {
		
		if(hitPoints == 0) {
			return 0;
		}
		
		int constitutionModifier = abilities.getModifier(abilities.getConstitution());		
		if(hitPoints+ constitutionModifier < 1) {
			return 1;
		}
		
		return hitPoints + constitutionModifier;
	}

    public  String toString(){
        return "Name: "+this.name + "| Race Type: " + this.raceType.toString()+ "| Level: "+this.level+"| Ability: "+this.abilities.toString();
    }


    public boolean isDead() {
		return getHP() <= 0;
	}


	public int getStrength() {
		return abilities.getStrength();
	}

	public int getDexterity() {
		return abilities.getDexterity();
	}

	public int getConstitution() {
		return abilities.getConstitution();
	}

	public int getWisdom() {
		return abilities.getWisdom();
	}

	public int getIntelligence() {
		return abilities.getIntelligence();
	}

	public int getCharisma() {
		return abilities.getCharisma();
	}

	public void setStrength(int strength) {
		abilities.setStrength(strength);		
	}

	public void setDexterity(int dexterity) {
		abilities.setDexterity(dexterity);
	}

	public void setConstitution(int constitution) {
		abilities.setConstitution(constitution);
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	public RaceType getRaceType() {
		return raceType;
	}

	public void setRaceType(RaceType raceType) {
		this.raceType = raceType;
	}


}
