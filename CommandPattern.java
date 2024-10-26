import java.util.Stack;

interface Command {
    public void excute();

    public void retreat();
}

class Paramedic {
    public void assessSituation() {
        System.out.println("Assess the situation and ensure safety.");
    }

    public void callForHelp() {
        System.out.println("Call for help or emergency services.");
    }

    public void provideFirstAid() {
        System.out.println("Provide first aid as necessary.");
    }

    public void monitorPatient() {
        System.out.println("Monitor the patient until help arrives.");
    }

    public void falseInjury() {
        System.out.println("Paramedic return to positions");
    }
}

class Firefighter {
    public void assessFire() {
        System.out.println("Assess the fire situation and plan the approach.");
    }

    public void equipGear() {
        System.out.println("Put on protective gear before entering the fire zone.");
    }

    public void suppressFire() {
        System.out.println("Use extinguishing tools to suppress the fire.");
    }

    public void falseAlarm() {
        System.out.println("Fire fighter return to positions");
    }
}

class FirstAidCommand implements Command {
    Paramedic paramedic;

    public FirstAidCommand(Paramedic paramedic) {
        this.paramedic = paramedic;
    }

    @Override
    public void excute() {
        paramedic.assessSituation();
        paramedic.callForHelp();
        paramedic.provideFirstAid();
        paramedic.monitorPatient();
    }

    @Override
    public void retreat() {
        paramedic.falseInjury();

    }
}

class FireExtinguishCommand implements Command {
    Firefighter firefighter;

    public FireExtinguishCommand(Firefighter firefighter) {
        this.firefighter = firefighter;
    }

    @Override
    public void excute() {
        firefighter.assessFire();
        firefighter.equipGear();
        firefighter.suppressFire();
    }

    @Override
    public void retreat() {
        firefighter.falseAlarm();
    }
}

class NoCommand implements Command {
    @Override
    public void excute() {
    }

    @Override
    public void retreat() {
    }
}

class SafetyManager {
    Command[] commands;
    Stack<Command> retreates = new Stack<>();

    public SafetyManager() {
        commands = new Command[3];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 3; i++) {
            commands[i] = noCommand;
        }
    }

    public void setCommand(int index, Command command) {
        commands[index] = command;
    }

    public void alarmFired(int index) {
        commands[index].excute();
        retreates.push(commands[index]);
    }

    public void retreatingCommands() {
        if (!retreates.isEmpty()) {
            Command command = retreates.pop();
            command.retreat();
        }
    }
}

public class CommandPattern {

    public static void main(String[] args) {
        // invoker
        SafetyManager mySafetyManager = new SafetyManager();

        // recever
        Paramedic myParamedic = new Paramedic();
        Firefighter myFirefighter = new Firefighter();

        // commands
        FirstAidCommand firstAidCommand = new FirstAidCommand(myParamedic);
        FireExtinguishCommand fireExtinguishCommand = new FireExtinguishCommand(myFirefighter);

        // setting commands
        mySafetyManager.setCommand(0, firstAidCommand);
        mySafetyManager.setCommand(1, fireExtinguishCommand);

        // invocer calls commands
        mySafetyManager.alarmFired(0);
        mySafetyManager.alarmFired(1);

        mySafetyManager.retreatingCommands();

    }
}
