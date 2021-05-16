package Business;

import Data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ComponentManager implements Observer, Manager{

    private HashMap<String, Component> components;

    public ComponentManager() {
        this.components = new HashMap<>();
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public boolean validExperiments(ArrayList<Experiment> experiments ) {
        int check = 0;
        for(Experiment experiment : experiments) {
            if (experiment instanceof SampleExperiment &&
                    check != 1 && check != 3) {
                for (Component c : components.values()) {
                    if (c.getType().equals("ARM") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getName().equals("Sample Ejector") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getId().equals("T1") || c.getId().equals("T2")
                            || c.getId().equals("T3")) {
                        if (c.getStatus().equals("Failed"))
                            return false;
                    } else if (c.getId().equals("HPGC-1") && c.getStatus().equals("Failed")) {
                        return false;
                    }
                }
                check += 1;
            } else if (experiment instanceof ReagentExperiment &&
                    check != 2 && check != 3) {
                for (Component c : components.values()) {
                    if (c.getType().equals("ARM") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getType().equals("Ejector") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getType().equals("Tool") && c.getStatus().equals("Failed")) {
                        return false;
                    }
                }
                check += 2;
            } else if (experiment instanceof ComplexExperiment) {
                ComplexExperiment complexExperiment = (ComplexExperiment) experiment;
                ArrayList<Command> commands = complexExperiment.getCommandList();
                for (Command c : commands) {
                    if (!checkCommand(c)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    private boolean checkCommand(Command c) {
        String commandID = c.getCommandID();
        switch (commandID) {
            case "C1":
                if (components.get("Arm-1").getStatus().equals("Failed"))
                    return false;
                break;
            case "C2":
            case "C10":
            case "C11":
                if (components.get("Arm-2").getStatus().equals("Failed"))
                    return false;
                break;
            case "C3":
                if (components.get("Arm-3").getStatus().equals("Failed"))
                    return false;
                break;
            case "C4":
                if (components.get("T4").getStatus().equals("Failed"))
                    return false;
                break;
            case "C5":
                if (components.get("E1").getStatus().equals("Failed"))
                    return false;
                break;
            case "C6":
                if (components.get("Arm-2").getStatus().equals("Failed"))
                    return false;
                if (components.get("Arm-1").getStatus().equals("Failed"))
                    return false;
                break;
            case "C7":
                if (components.get("E2").getStatus().equals("Failed"))
                    return false;
                break;
            case "C8":
                if (components.get("Arm-2").getStatus().equals("Failed"))
                    return false;
                if (components.get("E2").getStatus().equals("Failed"))
                    return false;
                break;
            case "C9":
                if (components.get("Arm-2").getStatus().equals("Failed"))
                    return false;
                Command_9 command_9 = (Command_9) c;
                if (!components.containsKey(command_9.getTool()) &&
                        components.get(command_9.getTool()).getStatus().equals("Failed"))
                    return false;
                break;
            case "C12":
            case "C13":
            case "C14":
            case "C15":
            case "C17":
                break;
            case "C16":
                Command_16 command_16 = (Command_16) c;
                if (!components.containsKey(command_16.getSensor()) &&
                        components.get(command_16.getSensor()).getStatus().equals("Failed"))
                    return false;
                break;
            case "C18":
            case "C19":
                if (components.get("Arm-1").getStatus().equals("Failed"))
                    return false;
                if (components.get("C-1").getStatus().equals("Failed"))
                    return false;
            case "C20":
                if (components.get("C-1").getStatus().equals("Failed"))
                    return false;
            break;
            default:
                MacroCommand macroCommand = (MacroCommand) c;
                for (Command command : macroCommand.getCommands()) {
                    if(!checkCommand(command))
                        return false;
                }
        }
        return true;
    }

    @Override
    public void extractResults(JSONObject data) {
        System.out.println("\n");
        JSONArray componentsResults = (JSONArray) data.get("capabilities");
        for (Component c: components.values()){
            for (int i = 0 ; i < componentsResults.size(); i++) {
                JSONObject obj = (JSONObject) componentsResults.get(i);
                String status = (String) obj.get(c.getId());
                if (status != null){
                    c.setStatus(status);
                }
            }
        }

        for(Component c  : components.values()) {
            System.out.println(c.getName() + " " + c.getStatus());
        }
        System.out.println("\n");
    }

    @Override
    public void addExperiment(Experiment e) {
        //do nothing
    }

    public void addComponent(Component component) {
        components.put(component.getId(), component);
    }

    public void setComponents(HashSet<Component> components) {
        for (Component c : components) {
            this.components.put(c.getId(), c);
        }
    }
}
