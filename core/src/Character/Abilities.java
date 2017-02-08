package Character;

public class Abilities  {
	
	public static enum AbilityType { STRENGTH, DEXTERITY, CONSTITUTION, WISDOM, INTELLIGENCE, CHARISMA };
	
	private int strength, dexterity, constitution, wisdom, intelligence, charisma;
	

	public Abilities (int defaultValue){
		strength = defaultValue;
		dexterity = defaultValue; 
		constitution = defaultValue;
		wisdom = defaultValue;
		intelligence = defaultValue;
		charisma = defaultValue;
	}
	
	public Abilities (){
		strength = 0;
		dexterity = 0; 
		constitution = 0;
		wisdom = 0;
		intelligence = 0;
		charisma = 0;
	}



	public int getStrength() {
		return strength;
	}


	public void setStrength(int strength) {
		this.strength = strength;
	}


	public int getDexterity() {
		return dexterity;
	}


	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}


	public int getConstitution() {
		return constitution;
	}


	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}


	public int getWisdom() {
		return wisdom;
	}


	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}


	public int getIntelligence() {
		return intelligence;
	}


	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}


	public int getCharisma() {
		return charisma;
	}


	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}
	
	public int getModifier(int value) {
		return value/2;
}
	
}
