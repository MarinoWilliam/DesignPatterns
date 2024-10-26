class TheaterLights {

    public void lightsOn() {
        System.out.println("Turning on the lights." );
    }

    public void lightsOff() {
        System.out.println("Turning off the lights." );
    }
    
}

class AudianceLights {
    public void setTemperature(String level) {
        System.out.println("Setting audiance lights to " + level );
    }
}

class AirConditioner {
    public void setTemperature(int temperature) {
        System.out.println("Setting temperature to " + temperature + " degrees.");
    }

    public void turnOff() {
        System.out.println("Turning off temperature control.");
    }
}

class Music {
    public void ambient() {
        System.out.println("Playing soft ambient music." );
    }

    public void mainTrack() {
        System.out.println("Plaing the main music." );
    }

    public void musicOff() {
        System.out.println("Turning off music" );
    }

}

class Mics {

    public void micsOn() {
        System.out.println("Turning on the mics." );
    }

    public void micsOff() {
        System.out.println("Turning off the mics." );
    }
    
}

class TheaterOperator {
    TheaterLights theaterLights;
    AudianceLights audianceLights;
    AirConditioner airConditioner;
    Music music;
    Mics mics;

    public TheaterOperator(TheaterLights theaterLights,
            AudianceLights audianceLights,
            AirConditioner airConditioner,
            Music music,
            Mics mics) {
                this.theaterLights = theaterLights;
                this.audianceLights = audianceLights;
                this.airConditioner = airConditioner;
                this.music = music;
                this.mics = mics;
    }

    public void standby() {
        theaterLights.lightsOff();
        audianceLights.setTemperature("dim");
        airConditioner.setTemperature(22); 
        music.musicOff();
        mics.micsOff();
        System.out.println("Theater is in standby mode.");
    }

    public void startTheShow() {
        theaterLights.lightsOn();
        audianceLights.setTemperature("bright");
        airConditioner.setTemperature(20);
        music.ambient();
        mics.micsOn();
        System.out.println("The show is starting!");
    }

    public void endTheShow() {
        theaterLights.lightsOff();
        audianceLights.setTemperature("dim");
        airConditioner.turnOff(); 
        music.musicOff();
        mics.micsOff();
        System.out.println("The show has ended.");
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        TheaterLights myTheaterLights = new TheaterLights();
        AudianceLights myAudianceLights = new AudianceLights();
        AirConditioner myAirConditioner =new AirConditioner();
        Music myMusicPlayer= new Music();
        Mics myMics= new Mics();

        TheaterOperator alfred = new TheaterOperator(myTheaterLights, myAudianceLights, myAirConditioner, myMusicPlayer, myMics);

        alfred.standby();
        System.out.println("__________________________________________________");
        alfred.startTheShow();
        System.out.println("__________________________________________________");
        alfred.endTheShow();

    }
}
