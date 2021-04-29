import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ExperimentCompiler implements Subject {

    private HashSet<Observer> observers;
    private HashSet<Manager> managers;

    public ExperimentCompiler() {
        this.observers = new HashSet<>();
        this.managers = new HashSet<>();
    }

    public void compileSampleOnly(String sampleName, double quantity, String unit, String location) {
        Experiment e = new SampleExperiment(sampleName, quantity, unit, location);
        boolean valid = true;
        if (quantity <= 0) {
            valid = false;
        } else {
            for (Observer observer : observers) {
                if (!observer.validExperiment(e)) {
                    valid = false;
                }
            }
        }
        if (valid) {
            for (Manager manager : managers) {
                manager.addExperiment(e);
            }
            System.out.println("Added");
        } else {
            System.out.println("Failed to add");
        }
    }

    public void compileComplexExperiment(String str) {
        ArrayList<ArrayList<String>> commandList = new ArrayList<>();
        try {
             commandList = parseCommandList(str);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Failed to add");
            return;
        }
//        for (ArrayList<String> a : commandList) {
//            for (String s : a)
//                System.out.print(s + ";");
//            System.out.println();
//        }
        boolean valid = true;
        Experiment e = new ComplexExperiment(commandList);
        for (Observer observer : observers) {
            if (!observer.validExperiment(e)) {
                valid = false;
            }
        }
        if (valid) {
            for (Manager manager : managers) {
                manager.addExperiment(e);
            }
            System.out.println("Added");
        } else {
            System.out.println("Failed to add");
        }
    }

    public void registerManager(Manager manager) {
        managers.add(manager);
    }

    public void removeManager(Manager manager) {
        managers.remove(manager);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Experiment e) {

    }

    private ArrayList<ArrayList<String>> parseCommandList(String str) throws IndexOutOfBoundsException{
        ArrayList<ArrayList<String>> commandList = new ArrayList<>();
        String[] commands = str.split("\n");
//        for (int i = 0; i < commands.length; i++)
//            System.out.println(commands[i]);
        for (String s : commands) {
            ArrayList<String> command = new ArrayList<>();
            s = s.substring(1);
            int start = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',' || s.charAt(i) == '}') {
                    command.add(s.substring(start, i).trim());
                    start = i + 1;
                }
            }
            if(command.size() != 4)
                throw new IndexOutOfBoundsException();
            commandList.add(command);
        }
        return commandList;
    }
}
