import java.io.Console;
import java.util.concurrent.atomic.AtomicBoolean;

// in this code we will make the singlton pattern work with multithreads
// we wil use the double checked locing to make sure before synchronizing the code

// the aim is to use lazy creating instance with minimum delay in code of the synchronized

// we will not use the eagrly creatied instancde
//private static Singleton uniqueInstance = new Singleton();

class NuclearRactor {
    private AtomicBoolean cool = new AtomicBoolean(true);
    private AtomicBoolean radiat = new AtomicBoolean(false);

    private volatile static NuclearRactor uniqueInstance;

    private NuclearRactor(){
    }

    public static NuclearRactor getUniqueInstance(){
        if(uniqueInstance== null){  
            synchronized (NuclearRactor.class){
                if(uniqueInstance== null){
                    uniqueInstance= new NuclearRactor();
                }
            }
        }
        return uniqueInstance;
    }
    

    public synchronized void nuclearReaction(){
        if ( cool.get() && radiat.get() ){
           System.out.println("radiation is done");
           cool.set(false);
        }else if(!cool.get()){
           System.out.println("reactor is still hot ");
        }else{
           System.out.println("load the radiate ");
        }
    }

    
    public synchronized void cooling(){
        System.out.println("cooling");
        cool.set(true);
 }

    public synchronized void load(){
        if ( !radiat.get() && cool.get()){
           System.out.println("loading radiate material");
           radiat.set(true);
        }else if(radiat.get()){
            System.out.println("reactor is already filled");
         }else{
            System.out.println("reactor is still hot ");
         }
    }

    public synchronized void unload(){
        if ( cool.get()  ){
           System.out.println("unloading wastes");
           radiat.set(false);
        }else{
            System.out.println("reactor is still hot ");
         }
    }

}



class ReactorTask1 implements Runnable {
    @Override
    public void run() {
        NuclearRactor reactor = NuclearRactor.getUniqueInstance();
        
        reactor.load();
        reactor.nuclearReaction();
        reactor.cooling();
        reactor.unload();
    }
}

class ReactorTask2 implements Runnable {
    @Override
    public void run() {
        NuclearRactor reactor = NuclearRactor.getUniqueInstance();
        
        reactor.load();
        reactor.nuclearReaction();
        reactor.nuclearReaction();
    }
}


public class SingletonPattern {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ReactorTask1());
        Thread thread2 = new Thread(new ReactorTask2());

        thread1.start();
        thread2.start();
       

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }
}