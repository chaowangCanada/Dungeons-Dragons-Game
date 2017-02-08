package Character;

import Classes.Class;
import Classes.Class.ClassType;
import Items.*;
import Races.Race;
import Races.Race.RaceType;

public class Character {
	public static enum Gender { MALE, FEMALE };

	private Gender gender;
	private ClassType classType;
	private RaceType raceType;
	private String name;
	private int level;
	
	private Abilities abilities;
	private int attackBonus;
	private int damageBonus;
	private int hitPoints;
	private Defense defense;
	
	private Armor armor;
	private Belt belt;
	private Boots boots;
	private Helmet helmet;
	private Ring ring;
	private Shield shield;
	private Weapon weapon;

	public Abilities getAbilities() {
		return abilities;
	}

	public void setAbilities(Abilities abilities) {
		this.abilities = abilities;
	}

	public Character(){
		this("");
	};
	
	public Character(String name) {
		this(name, ClassType.FIGHTER, RaceType.HUMAN, 1);
	}

	public Character(String name, ClassType classType, RaceType raceType, int level) {
		this.setName(name);
		this.setDefense(new Defense());
		this.classType = classType;
		this.raceType  = raceType;
		this.hitPoints = 10;
		this.abilities = new Abilities(10);
		this.level = level;
		
		this.armor = new Armor();
		this.belt = new Belt();
		this.boots = new Boots();
		this.helmet = new Helmet();
		this.ring = new Ring();
		this.shield = new Shield();
		this.weapon = new Weapon();

	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	// equipment
	public Belt getBelt() {
		return belt;
	}

	public void setBelt(Belt belt) {
		this.belt = belt;
	}

	public Boots getBoots() {
		return boots;
	}

	public void setBoots(Boots boots) {
		this.boots = boots;
	}

	public Helmet getHelmet() {
		return helmet;
	}

	public void setHelmet(Helmet helmet) {
		this.helmet = helmet;
	}

	public Ring getRing() {
		return ring;
	}

	public void setRing(Ring ring) {
		this.ring = ring;
	}

	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void setArmor(Armor armor) {
		this.armor = armor;
	}
	
	public Armor getArmor() {
		return armor;
	}

	// calculation
	
	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public Defense getDefense() {
		return defense;
	}

	public void setDefense(Defense defense) {
		this.defense = defense;
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
