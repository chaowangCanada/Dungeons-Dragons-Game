package Items;

import Character.Abilities;

public class Helmet {
	private int armor;
	private Abilities ability;
	
	
	public Helmet(){
		armor = 1;
		ability = new Abilities();
	}
	
	public Helmet(int intelligence, int wisdom, int defens){
		this();
		this.ability.setIntelligence(intelligence);
		this.ability.setWisdom(wisdom);
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
