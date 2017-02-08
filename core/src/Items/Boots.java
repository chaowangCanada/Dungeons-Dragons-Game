package Items;

import Character.Abilities;
import Character.Defense;

public class Boots {
	private Defense armor;
	private Abilities ability;
	
	
	public Boots(){
		armor = new Defense();
		ability = new Abilities();
	}
	
	public Boots(int dexterity , int defens){
		this();
		this.ability.setDexterity(dexterity);
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
