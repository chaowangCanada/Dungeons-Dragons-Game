package Items;

import Character.Abilities;
import Character.Defense;

public class Helmet {
	private Defense armor;
	private Abilities ability;
	
	
	public Helmet(){
		armor = new Defense();
		ability = new Abilities();
	}
	
	public Helmet(int intelligence, int wisdom, int defens){
		this();
		this.ability.setIntelligence(intelligence);
		this.ability.setWisdom(wisdom);
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
