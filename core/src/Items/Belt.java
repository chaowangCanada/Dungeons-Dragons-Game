package Items;

import Character.Abilities;

public class Belt {
	private Abilities ability;
	
	
	public Belt(){
		ability = new Abilities();
	}
	
	public Belt(int constitution, int strength){
		this();
		this.ability.setConstitution(constitution);
		this.ability.setStrength(strength);
	}


	public Abilities getAbility() {
		return ability;
	}

	public void setAbility(Abilities ability) {
		this.ability = ability;
	}
	
}
