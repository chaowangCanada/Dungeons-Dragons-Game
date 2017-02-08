package Items;
import Character.Defense;

public class Armor {

	private Defense armor;	
	
	public Armor(){
		armor = new Defense();
	}
	
	public Armor(int defens){
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
