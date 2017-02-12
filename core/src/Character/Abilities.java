package Character;

import Items.EnchantedAbility;

public class Abilities  {
	
	public enum AbilityType {
		//STRENGTH, DEXTERITY, CONSTITUTION, WISDOM, INTELLIGENCE, CHARISMA;

		STRENGTH(0), DEXTERITY(1), CONSTITUTION(2), WISDOM(3), INTELLIGENCE(4), CHARISMA(5);
		int index;

		private AbilityType(int value) {
			this.index = value;
		}

		public int getIndex() {
			return index;
		}

		public AbilityType getAbility (int index) {
			switch (index) {
				case 0:
					return STRENGTH;
				case 1:
					return DEXTERITY;
				case 2:
					return CONSTITUTION;
				case 3:
					return WISDOM;
				case 4:
					return INTELLIGENCE;
				case 5:
					return CHARISMA;
			}
			return WISDOM;
		}

	};
	
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

	public String toString(){
		return 	Integer.toString(this.strength) + " | " +Integer.toString(this.dexterity) + " | " + Integer.toString(this.constitution) + " | "
				+ Integer.toString(this.wisdom) + " | " +Integer.toString(this.intelligence) + " | " +Integer.toString(this.charisma);
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
