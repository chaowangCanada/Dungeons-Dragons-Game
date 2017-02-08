package Items;

import Character.Abilities;
import Character.Defense;

public class Ring {
	private Defense armor;
	private Abilities ability;
	
	
	public Ring(){
		armor = new Defense();
		ability = new Abilities();
	}
	
	public Ring(int strength, int constitution, int wisdom, int charisma, int defens){
		this();
		this.ability.setStrength(strength);
		this.ability.setConstitution(constitution);
		this.ability.setWisdom(wisdom);
		this.ability.setCharisma(charisma);
		this.armor.setArmor(defens);
	}

	public Defense getArmor() {
		return armor;
	}

	public void setArmor(Defense armor) {
		this.armor = armor;
	}

	public Abilities getAbility() {
		return ability;
	}

	public void setAbility(Abilities ability) {
		this.ability = ability;
	}
	
	
}
