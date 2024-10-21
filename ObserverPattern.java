import java.util.ArrayList;

interface Observer {
    public void update(float spo2, float temp, float heartRate);
}

interface Screen {
    public void display();
}

interface Subject{
    public void registerObserver (Observer o);
    public void removeObserver (Observer o);
    public void notifyObservers ( );
}

class SmartWatch implements Subject{
    private ArrayList<Observer> observers;
    private float spo2;
    private float temp;
    private float heartRate;

    public SmartWatch(){
        observers = new ArrayList<>();
    }

    @Override
    public void removeObserver (Observer o){
        int i = observers.indexOf(o);
        if(i>=0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers (){
        for(int i=0; i< observers.size(); i++){
            Observer observer = (Observer) observers.get(i);
            observer.update(spo2,temp,heartRate);
        }
    }

    @Override
    public void registerObserver (Observer o){
        observers.add(o);
    }

    public void setMeasurements(float spo2, float temp, float heartRate) {
        this.spo2 = spo2;
        this.temp = temp;
        this.heartRate = heartRate;
        notifyObservers(); 
    }
}


class TempretureScreen implements Observer, Screen {
    private float temp;
    private Subject smartwatch;

    public TempretureScreen(Subject smartwatch){
        this.smartwatch= smartwatch;
        smartwatch.registerObserver(this);
    }
    @Override
    public void update(float spo2, float temp, float heartRate) {
        this.temp = temp;
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Your body temp is: "+ temp + "C");
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        SmartWatch smartWatch = new SmartWatch();
        TempretureScreen tempretureScreen = new TempretureScreen(smartWatch);

        smartWatch.setMeasurements(20, 30, 40);

    }
}
