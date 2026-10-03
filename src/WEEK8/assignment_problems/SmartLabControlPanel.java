package WEEK8.assignment_problems;

import java.util.ArrayList;

interface Capability {
    void setValue(int value);
    String getName();
}

class PowerCapability implements Capability {
    boolean on = false;

    public void setValue(int value) {
        if (value == 1)
            on = true;
        else if (value == 0)
            on = false;
    }

    public String getName() {
        return "Power";
    }
}

class BrightnessCapability implements Capability {
    int brightness = 0;

    public void setValue(int value) {
        if (value < 0 || value > 100) {
            System.out.println("Rejected: Brightness must be between 0% and 100%.");
            return;
        }

        brightness = value;
    }

    public String getName() {
        return "Brightness";
    }
}

class TemperatureCapability implements Capability {
    int temperature;

    public void setValue(int value) {
        if (value < 16 || value > 30) {
            System.out.println("Rejected: Lab AC temperature must be between 16°C and 30°C.");
            return;
        }

        temperature = value;
    }

    public String getName() {
        return "Temperature";
    }
}

class Device {
    String name;
    ArrayList<Capability> capabilities = new ArrayList<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability c) {
        capabilities.add(c);
        System.out.println(name + ": " + c.getName() + " capability added.");
    }

    Capability getCapability(String name) {
        for (Capability c : capabilities)
            if (c.getName().equals(name))
                return c;

        return null;
    }

    void set(String capability, int value) {
        Capability c = getCapability(capability);

        if (c != null) {
            c.setValue(value);

            if (capability.equals("Power"))
                System.out.println(name + ": " + (value == 1 ? "ON" : "OFF") + ".");
            else if (capability.equals("Brightness"))
                System.out.println(name + ": brightness set to " + value + "%.");
            else
                System.out.println(name + ": temperature set to " + value + "°C.");
        }
    }
}

class SceneStep {
    String capability;
    int value;

    SceneStep(String capability, int value) {
        this.capability = capability;
        this.value = value;
    }
}

class Scene {
    String name;
    ArrayList<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(String capability, int value) {
        steps.add(new SceneStep(capability, value));
    }

    void run(ArrayList<Device> devices) {

        System.out.println("Scene '" + name + "' started.");

        int count = 0;

        for (SceneStep step : steps) {
            for (Device d : devices) {

                if (d.getCapability(step.capability) != null) {
                    d.set(step.capability, step.value);
                    count++;
                }
            }
        }

        System.out.println("Scene '" + name +
                "' completed: " + count + " actions applied.");
    }
}

public class SmartLabControlPanel {

    public static void main(String[] args) {

        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        ArrayList<Device> devices = new ArrayList<>();
        devices.add(ac);
        devices.add(lights);
        devices.add(projector);

        Scene lecture = new Scene("Lecture Mode");

        lecture.addStep("Power", 1);
        lecture.addStep("Brightness", 40);
        lecture.addStep("Temperature", 24);

        lecture.run(devices);

        ac.set("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        projector.set("Brightness", 70);
    }
}