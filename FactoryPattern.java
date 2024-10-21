// Superclass Character
abstract class Character {
    int id;
    String name;
    double weight;
    double height;
    double attackSpeed;
    double defenseSpeed;
    int level;
    int weapon;
    int shield;
    String helmet;
    String boot;

    public Character(String name, double weight, double height, double attackSpeed, double defenseSpeed, int level,
            int weapon, int shield, String helmet, String boot) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.attackSpeed = attackSpeed;
        this.defenseSpeed = defenseSpeed;
        this.level = level;
        this.weapon = weapon;
        this.shield = shield;
        this.helmet = helmet;
        this.boot = boot;
    }

    public abstract void train();

    public abstract void AttackPosition();

    public abstract void DefensePosition();

    public void move() {
        System.out.println(name + " is moving.");
    }

    public void jump() {
        System.out.println(name + " is jumping.");
    }
}

// Nordic Characters
class NordicWarrior extends Character {
    public NordicWarrior() {
        super("The Nordic Warrior", 90, 1.85, 7, 5, 10, 8, 7, "Steel Helmet", "Leather Boots");
    }

    @Override
    public void train() {
        System.out.println(name + " is training in the snowy mountains.");
    }

    @Override
    public void AttackPosition() {
        System.out.println(name + " charges with his axe.");
    }

    @Override
    public void DefensePosition() {
        System.out.println(name + " takes a defensive stance with his shield.");
    }
}

class NordicArcher extends Character {
    public NordicArcher() {
        super("The Nordic Archer", 70, 1.75, 8, 6, 8, 6, 5, "Feathered Helmet", "Light Boots");
    }

    @Override
    public void train() {
        System.out.println(name + " is training by shooting arrows from long range.");
    }

    @Override
    public void AttackPosition() {
        System.out.println(name + " shoots a flurry of arrows.");
    }

    @Override
    public void DefensePosition() {
        System.out.println(name + " evades attacks with agile movements.");
    }
}

class NordicMage extends Character {
    public NordicMage() {
        super("The Nordic Mage", 65, 1.7, 6, 5, 12, 3, 2, "Wizard Hat", "Magic Boots");
    }

    @Override
    public void train() {
        System.out.println(name + " is mastering ancient spells in the cold.");
    }

    @Override
    public void AttackPosition() {
        System.out.println(name + " casts a powerful ice spell.");
    }

    @Override
    public void DefensePosition() {
        System.out.println(name + " conjures a magical barrier.");
    }
}

// Saxon Characters
class SaxonWarrior extends Character {
    public SaxonWarrior() {
        super("The Saxon Warrior", 95, 1.9, 6, 6, 11, 9, 8, "Iron Helmet", "Heavy Boots");
    }

    @Override
    public void train() {
        System.out.println(name + " is training in the Saxon war camps.");
    }

    @Override
    public void AttackPosition() {
        System.out.println(name + " swings his sword with brutal force.");
    }

    @Override
    public void DefensePosition() {
        System.out.println(name + " blocks with a large iron shield.");
    }
}

class SaxonArcher extends Character {
    public SaxonArcher() {
        super("The Saxon Archer", 68, 1.72, 9, 5, 9, 7, 5, "Leather Helmet", "Quick Boots");
    }

    @Override
    public void train() {
        System.out.println(name + " is practicing archery in the Saxon forests.");
    }

    @Override
    public void AttackPosition() {
        System.out.println(name + " launches a volley of arrows.");
    }

    @Override
    public void DefensePosition() {
        System.out.println(name + " swiftly dodges incoming attacks.");
    }
}

class SaxonMage extends Character {
    public SaxonMage() {
        super("The Saxon Mage", 60, 1.65, 7, 5, 13, 4, 3, "Mystic Hood", "Enchanted Boots");
    }

    @Override
    public void train() {
        System.out.println(name + " is studying forbidden Saxon magic.");
    }

    @Override
    public void AttackPosition() {
        System.out.println(name + " unleashes a fireball.");
    }

    @Override
    public void DefensePosition() {
        System.out.println(name + " conjures a fire shield.");
    }
}

// CastleFactory Superclass
abstract class CastleFactory {
    public abstract Character createCharacter(String type);

    public Character callForDefence(String type) {
        Character character = createCharacter(type);
        character.train();
        character.DefensePosition();
        return character;
    }

    public Character callForAttack(String type) {
        Character character = createCharacter(type);
        character.train();
        character.AttackPosition();
        return character;
    }
}

// NordicCastle Factory (Concrete Factory)
class NordicCastle extends CastleFactory {
    @Override
    public Character createCharacter(String type) {
        switch (type.toLowerCase()) {
            case "warrior":
                return new NordicWarrior();
            case "archer":
                return new NordicArcher();
            case "mage":
                return new NordicMage();
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}

// SaxonCastle Factory (Concrete Factory)
class SaxonCastle extends CastleFactory {
    @Override
    public Character createCharacter(String type) {
        switch (type.toLowerCase()) {
            case "warrior":
                return new SaxonWarrior();
            case "archer":
                return new SaxonArcher();
            case "mage":
                return new SaxonMage();
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}

// Main class demonstrating the factory pattern
public class FactoryPattern {
    public static void main(String[] args) {
        CastleFactory nordicCastle = new NordicCastle();
        CastleFactory saxonCastle = new SaxonCastle();

        // Nordic Characters
        System.out.println("Nordic Castle Defence:");
        nordicCastle.callForDefence("warrior");
        nordicCastle.callForDefence("archer");
        nordicCastle.callForDefence("mage");

        System.out.println("\nNordic Castle Attack:");
        nordicCastle.callForAttack("warrior");
        nordicCastle.callForAttack("archer");
        nordicCastle.callForAttack("mage");

        // Saxon Characters
        System.out.println("\nSaxon Castle Defence:");
        saxonCastle.callForDefence("warrior");
        saxonCastle.callForDefence("archer");
        saxonCastle.callForDefence("mage");

        System.out.println("\nSaxon Castle Attack:");
        saxonCastle.callForAttack("warrior");
        saxonCastle.callForAttack("archer");
        saxonCastle.callForAttack("mage");
    }
}
