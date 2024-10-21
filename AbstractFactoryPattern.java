
abstract class CharacterItem {
    String name;
    String description;
    int strength;

    public CharacterItem(String name, String description, int strength) {
        this.name = name;
        this.description = description;
        this.strength = strength;
    }
}

class NordicHelmet extends CharacterItem {
    public NordicHelmet() {
        super("Gjermundbu helmet", "heavy metal helmet with horns", 20);
    }
}

class SaxonHelmet extends CharacterItem {
    public SaxonHelmet() {
        super(" Roman ridge helmet", "metal helmet with leather", 15);
    }
}

class NordicWeapon extends CharacterItem {
    public NordicWeapon() {
        super("Viking Axe", "A large axe used by Vikings", 20);
    }
}

class SaxonWeapon extends CharacterItem {
    public SaxonWeapon() {
        super("Saxon Sword", "A sturdy sword used by Saxons", 25);
    }
}

class NordicShield extends CharacterItem {
    public NordicShield() {
        super("Viking Shield", "Round shield decorated with symbols", 15);
    }
}

class SaxonShield extends CharacterItem {
    public SaxonShield() {
        super("Saxon Kite Shield", "Long shield for better protection", 15);
    }
}

interface CharacterItemFactory {
    public CharacterItem createHeadwear();

    public CharacterItem createWeapon();

    public CharacterItem createShield();

    public String createName();
}

class NordicWarriorItemFactory implements CharacterItemFactory {
    public CharacterItem createHeadwear() {
        return new NordicHelmet();
    }

    public CharacterItem createWeapon() {
        return new NordicWeapon();
    }

    public CharacterItem createShield() {
        return new NordicShield();
    }

    public String createName() {
        return "Nordic Warrior";
    }
}

class SaxonWarriorItemFactory implements CharacterItemFactory {
    public CharacterItem createHeadwear() {
        return new SaxonHelmet();
    }

    public CharacterItem createWeapon() {
        return new NordicWeapon();
    }

    public CharacterItem createShield() {
        return new SaxonShield();
    }

    public String createName() {
        return "Saxon Warrior";
    }
}

abstract class AbFacCharacter {
    int cost;
    String name;
    CharacterItem headwear;
    CharacterItem weapon;
    CharacterItem shield;

    abstract void create();

    public void move() {
        System.out.println(name + " is moving.");
    }

    public void jump() {
        System.out.println(name + " is jumping.");
    }
}

class Warrior extends AbFacCharacter {
    CharacterItemFactory characterItemFactory;

    public Warrior(CharacterItemFactory characterItemFactory) {
        this.characterItemFactory = characterItemFactory;
    }

    int cost = 150;

    void create() {
        name = characterItemFactory.createName();
        headwear = characterItemFactory.createHeadwear();
        weapon = characterItemFactory.createWeapon();
        shield = characterItemFactory.createShield();
    }
}

public class AbstractFactoryPattern {

    public static void main(String[] args) {
        CharacterItemFactory nordicFactory = new NordicWarriorItemFactory();
        AbFacCharacter nordicWarrior = new Warrior(nordicFactory);
        nordicWarrior.create();
        System.out.println(nordicWarrior.name + " wearing " + nordicWarrior.headwear.name + ", " + nordicWarrior.weapon.name + ", and a  " + nordicWarrior.shield.name);
        
        CharacterItemFactory saxonFactory = new SaxonWarriorItemFactory();
        AbFacCharacter saxonWarrior = new Warrior(saxonFactory);
        saxonWarrior.create();
        System.out.println(saxonWarrior.name + " wearin " + saxonWarrior.headwear.name + ", " + saxonWarrior.weapon.name + ", and a " + saxonWarrior.shield.name);
    }

}
