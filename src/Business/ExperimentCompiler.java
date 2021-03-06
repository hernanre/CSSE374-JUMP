package Business;

import Data.*;

import java.util.ArrayList;
import java.util.HashSet;

public class ExperimentCompiler implements Subject {

    private HashSet<Observer> observers;
    private HashSet<Manager> managers;
    private CommandCompiler commandCompiler;

    public ExperimentCompiler(CommandCompiler commandCompiler) {
        this.observers = new HashSet<>();
        this.managers = new HashSet<>();
        this.commandCompiler = commandCompiler;
    }

    public void compileSampleOnly(String experimentName, String experimentID,
                                  String sampleName, double quantity, String unit, String location) {
        Experiment e = new SampleExperiment(experimentName, experimentID,
                sampleName, quantity, unit, location);
        boolean valid = true;
        if (quantity <= 0) {
            valid = false;
        } else {

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

    public void compileReagentExperiment(String experimentName, String experimentID,
                                         ArrayList<ArrayList<String>> reagentStringList)
            throws NumberFormatException, IndexOutOfBoundsException{
        ArrayList<Reagent> reagents = new ArrayList<>();
        for(ArrayList<String> reagentStrings : reagentStringList) {
            if (reagentStrings.size() != 5)
                throw new IndexOutOfBoundsException();
            System.out.println("Data.Reagent " + reagentStrings.get(0));
                Reagent reagent = new Reagent(reagentStrings.get(0), reagentStrings.get(3), reagentStrings.get(4),
                        Integer.valueOf(reagentStrings.get(1)), Integer.valueOf(reagentStrings.get(2)));
                reagents.add(reagent);
        }
        Experiment experiment = new ReagentExperiment(experimentName, experimentID, reagents);
        for (Manager manager : managers) {
            manager.addExperiment(experiment);
        }
    }

    public void compileComplexExperiment(String ID, String name, String str) {
//
        ArrayList<Command> cmds = new ArrayList<>();
        String[] sep = str.split(";", -1);
        for (String cmd: sep){
            String[] first = cmd.split(",", 2);
            Command toadd = null;
            try {
                if (first.length > 1) {
                    toadd = commandCompiler.parseBasicCommand(first[0].trim(), first[1].trim());

                } else {
                    toadd = commandCompiler.parseBasicCommand(first[0].trim(), "");
                }
            }catch (NumberFormatException e){
                System.out.println(e);
                return;
            }catch (IndexOutOfBoundsException e){
                System.out.println(e);
                return;
            }
            cmds.add(toadd);
        }
        ComplexExperiment cpxEx = new ComplexExperiment(ID, name, cmds);
        for (Command command : cpxEx.getCommandList()) {
            System.out.println(command.toJson().toJSONString());
        }
        for (Manager manager : managers) {
            manager.addExperiment(cpxEx);
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
    public void notifyObservers() {

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
