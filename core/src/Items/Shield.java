package Items;

import Character.Defense;

public class Shield {

	private Defense armor;	
	
	public Shield(){
		armor = new Defense();
	}
	
	public Shield(int defens){
		this();
		this.armor.setArmor(defens);
	}

	public Defense getArmor() {
		return armor;
	}

	public void setArmor(Defense armor) {
		this.armor = armor;
	}
	
}
