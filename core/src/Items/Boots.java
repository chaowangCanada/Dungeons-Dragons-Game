package Items;

import Character.Abilities;

public class Boots {
	private int armor;
	private Abilities ability;
	
	
	public Boots(){
		armor = 1;
		ability = new Abilities();
	}
	
	public Boots(int dexterity , int defens){
		this();
		this.ability.setDexterity(dexterity);
		this.armor = (defens);
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
