package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ComplexExperiment implements Experiment {
    private String ID, name, status;
    private ArrayList<Command> commandList;
    private int priority;

    public ComplexExperiment(String ID, String name, ArrayList<Command> commandList) {
        this.ID = ID;
        this.name = name;
        this.commandList = commandList;
        this.status = "Entered";
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getLabel() {
        return "Complex Ex ID: " + ID + ", Name: " + name;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject experimentInfo = new JSONObject();
        experimentInfo.put("experiment_id", this.getID());
        experimentInfo.put("experiment_name","Test Complex Experiment");
        experimentInfo.put("experiment_type", "Complex");

        System.out.println(this.commandList.toString());
        JSONArray commands = new JSONArray();

//        JSONObject command1 = new JSONObject();
//        command1.put("command", "C19");
//
//
//        ArrayList<String> params = new ArrayList<>();
//        params.add("HPGC-1");
//
//
////       JSONWriter
//        JSONObject command2 = new JSONObject();
//        command2.put("command", "C8");
//
//
//        JSONObject command3 = new JSONObject();
//        command3.put("command", "C4");
//        JSONObject command4 = new JSONObject();
//
//        //this adds all of the commands that require the reagents name and quantity.
//        for(int i = 0; i < this.reagents.size(); i++){
//            commands.add(this.reagents.get(i).getJSONReagentCommand());
//        }
//
//        command4.put("param", params.toString());
//        command4.put("command", "C15");
//        JSONObject command5 = new JSONObject();
//        command5.put("command", "C7");
        for(int i = 0; i < this.commandList.size(); i++) {
            commands.add(this.commandList.get(i).toJson());
        }
        experimentInfo.put("experiment_commands", commands);
        return experimentInfo;
    }

    @Override
    public ArrayList<String> getComponentsNeeded() {
        ArrayList<String> result = new ArrayList<>();
        for (Command c : commandList) {
            result.addAll(checkCommandComponent(c));
        }
        return result;
    }

    @Override
    public HashMap<String, Integer> getSupplyNeeded() {
        HashMap<String, Integer> sum = new HashMap<>();
        for (Command command : commandList) {
            mergeHashMap(sum, checkCommandSupply(command));
        }
        return sum;
    }

    public ArrayList<Command> getCommandList() {
        return commandList;
    }

    private ArrayList<String> checkCommandComponent(Command c) {
        ArrayList<String> result = new ArrayList<>();
        String commandID = c.getCommandID();
        switch (commandID) {
            case "C1":
                result.add("Arm-1");
                break;
            case "C2":
            case "C10":
            case "C11":
                result.add("Arm-2");
                break;
            case "C3":
                result.add("Arm-3");
                break;
            case "C4":
                result.add("T4");
                break;
            case "C5":
                result.add("E1");
                break;
            case "C6":
                result.add("Arm-2");
                result.add("Arm-1");
                break;
            case "C7":
                result.add("E2");
                break;
            case "C8":
                result.add("Arm-2");
                result.add("E2");
                break;
            case "C9":
                result.add("Arm-2");
                Command_9 command_9 = (Command_9) c;
                result.add(command_9.getTool());
                break;
            case "C12":
            case "C13":
            case "C14":
            case "C15":
            case "C17":
                break;
            case "C16":
                Command_16 command_16 = (Command_16) c;
                result.add(command_16.getSensor());
                break;
            case "C18":
            case "C19":
                result.add("Arm-1");
                result.add("C-1");
            case "C20":
                result.add("C-1");
                break;
            default:
                MacroCommand macroCommand = (MacroCommand) c;
                for (Command command : macroCommand.getCommands()) {
                    result.addAll(checkCommandComponent(command));
                }
        }
        return result;
    }

    private HashMap<String, Integer> checkCommandSupply (Command command) {
        HashMap<String, Integer> result = new HashMap<>();
        if (command instanceof Command_6) {
            Command_6 command_6 = (Command_6) command;
            result.put(command_6.getReagent(), command_6.getAmount());
            result.put("sealed flask", 1);
        } else if (command instanceof Command_7) {
            result.put("sealed flask", 1);
        } else if (command instanceof Command_20) {
            result.put("test tube", 1);
            result.put("caps", 1);
        } else if (command instanceof MacroCommand) {
            MacroCommand macroCommand = (MacroCommand) command;
            for (Command c : macroCommand.getCommands()) {
                mergeHashMap(result, checkCommandSupply(c));
            }
        }
        return result;
    }

    private void mergeHashMap(HashMap<String, Integer> target, HashMap<String, Integer> toAdd) {
        for (String str : toAdd.keySet()) {
            if (target.containsKey(str))
                target.put(str, target.get(str) + toAdd.get(str));
            else
                target.put(str, toAdd.get(str));
        }
    }
}

