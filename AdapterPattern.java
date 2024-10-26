interface DomesticCat{
    void meow();
    void eat();
} 

interface WildCat{
    void roar();
    void prey();
} 

class SiameseCat implements DomesticCat{
   
    @Override
    public void meow() {
        System.out.println("meow meow");
    }

    @Override
    public void eat() {
        System.out.println("soft licking");
    }

}

class Lion implements WildCat{

    @Override
    public void roar() {
        System.out.println("ROAR ROAR");
    }

    @Override
    public void prey() {
        System.out.println("Eating vigorously");
    }
    
}

class DomesticAdaptWild implements WildCat{

    DomesticCat kitty;

    public DomesticAdaptWild(DomesticCat domesticCat){
        this.kitty =domesticCat;
    }
    @Override
    public void roar() {
        kitty.meow();
        System.out.println("that is the loadest I can do");
    }

    @Override
    public void prey() {
        kitty.eat();
        System.out.println("licking is also wild you know");
    }
    
} 



public class AdapterPattern {
    public static void main (String[] args){
        SiameseCat harhor = new SiameseCat();
        WildCat wildHarhor = new DomesticAdaptWild(harhor);
        wildHarhor.roar();
        wildHarhor.prey();
    }
}
