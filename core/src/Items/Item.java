package Items;


public class Item {

    public enum ItemType {
        HELMET(new EnchantedAbility[]{EnchantedAbility.INTELLIGENCE, EnchantedAbility.ARMORCLASS, EnchantedAbility.WISDOM}),
        ARMOR(new EnchantedAbility[]{EnchantedAbility.ARMORCLASS}),
        SHIELD(new EnchantedAbility[]{EnchantedAbility.ARMORCLASS}),
        RING(new EnchantedAbility[]{EnchantedAbility.ARMORCLASS, EnchantedAbility.STRENGTH, EnchantedAbility.WISDOM, EnchantedAbility.CONSTITUTION, EnchantedAbility.CHARISMA}),
        BELT(new EnchantedAbility[]{EnchantedAbility.CONSTITUTION,EnchantedAbility.STRENGTH}),
        BOOTS(new EnchantedAbility[]{EnchantedAbility.ARMORCLASS,EnchantedAbility.DEXTERITY}),
        WEAPON(new EnchantedAbility[]{EnchantedAbility.ATTACKBONUS,EnchantedAbility.DAMAGEBONUS});

        EnchantedAbility[] abilityArr;

        private ItemType(EnchantedAbility[] abilityArr) {
            this.abilityArr = abilityArr;
        }

        public EnchantedAbility[] getEnchantedAbility() {
            return abilityArr;
        }
    };

    private int level;
    private String name;
    private ItemType itemType;

    public Item(ItemType type ,int level, String name) {
        this.itemType = type;
        this.level = level;
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
