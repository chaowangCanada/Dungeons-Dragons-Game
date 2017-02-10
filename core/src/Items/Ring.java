package Items;

import Character.Abilities;

public class Ring {
	private int armor;
	private Abilities ability;
	
	
	public Ring(){
		armor = 1;
		ability = new Abilities();
	}
	
	public Ring(int strength, int constitution, int wisdom, int charisma, int defens){
		this();
		this.ability.setStrength(strength);
		this.ability.setConstitution(constitution);
		this.ability.setWisdom(wisdom);
		this.ability.setCharisma(charisma);
		this.armor=(defens);
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public Abilities getAbility() {
		return ability;
	}

	public void setAbility(Abilities ability) {
		this.ability = ability;
	}
	
	
}
