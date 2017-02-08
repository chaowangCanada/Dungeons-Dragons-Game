package Items;

public class Weapon {
	private int attackBonus;
	private int damageBonus;
	
	public Weapon(){
		attackBonus = 0;
		damageBonus = 0;
	}
	
	public Weapon(int attack, int damage){
		this.attackBonus = attack;
		this.damageBonus = damage;
	}

	public int getAttackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	public int getDamageBonus() {
		return damageBonus;
	}

	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}
	
	

}
