package Items;

public class Shield {

	private int armor;
	
	public Shield(){
		armor = 1;
	}
	
	public Shield(int defens){
		this();
		this.armor = (defens);
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
	
}
