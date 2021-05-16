package Business;

import Data.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SupplyManager implements Observer, Manager{

    //May change to HashMap
    private HashMap<String, Supply> supplies;

    public SupplyManager() {
        this.supplies = new HashMap<>();
    }

    @Override
    public void addExperiment(Experiment e) {
        //do nothing
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public boolean validExperiments(ArrayList<Experiment> experiments) {
        HashMap<String, Integer> sumOfNeeds = new HashMap<>();
        for(Experiment experiment : experiments) {
            if (experiment instanceof SampleExperiment) {
                if (supplies.get("test tube").getQuantityAvailable() < 1) {
                    System.out.println("No enough test tubes");
                    return false;
                } else if (supplies.get("caps").getQuantityAvailable() < 1) {
                    System.out.println("No enough caps");
                    return false;
                }
                if(sumOfNeeds.containsKey("test tube"))
                    sumOfNeeds.put("test tube", sumOfNeeds.get("test tube") + 1);
                else
                    sumOfNeeds.put("test tube", 1);
                if(sumOfNeeds.containsKey("caps"))
                    sumOfNeeds.put("caps", sumOfNeeds.get("caps") + 1);
                else
                    sumOfNeeds.put("caps", 1);
            } else if (experiment instanceof ReagentExperiment) {
                ReagentExperiment reagentExperiment = (ReagentExperiment) experiment;
                int flaskNum = 0;
                for (Reagent r : reagentExperiment.getReagents()) {
                    if(!supplies.containsKey(r.getReagentName())) {
                        System.out.println("There is no reagent named " + r.getReagentName());
                        return false;
                    }
                    if (supplies.get(r.getReagentName()).getQuantityAvailable() < r.getQuantity()) {
                        System.out.println("No enough reagent named " + r.getReagentName());
                        return false;
                    }
                    if(sumOfNeeds.containsKey(r.getReagentName()))
                        sumOfNeeds.put(r.getReagentName(), sumOfNeeds.get(r.getReagentName()) + r.getQuantity());
                    else
                        sumOfNeeds.put(r.getReagentName(), r.getQuantity());
                    flaskNum++;
                }
                if (supplies.get("sealed flask").getQuantityAvailable() < flaskNum) {
                    System.out.println("No enough sealed flask");
                    return false;
                }
                sumOfNeeds.put("sealed flask", flaskNum);
            } else if (experiment instanceof ComplexExperiment) {
                ComplexExperiment complexExperiment = (ComplexExperiment) experiment;
                HashMap<String, Integer> sum = new HashMap<>();
                for (Command command : complexExperiment.getCommandList()) {
                    mergeHashMap(sum, checkCommand(command));
                }
                for (String str : sum.keySet()) {
                    if (!supplies.containsKey(str)) {
                        System.out.println("There is no " + str);
                        return false;
                    } else if (supplies.get(str).getQuantityAvailable() < sum.get(str)) {
                        System.out.println("There is not enough " + str);
                        return false;
                    }
                }
                mergeHashMap(sumOfNeeds, sum);
            }
        }
        for(String str : sumOfNeeds.keySet()) {
            if(supplies.get(str).getQuantityAvailable() < sumOfNeeds.get(str)) {
                System.out.println("Not enough supply named " + str + " in total");
            }
        }
        for(String str : sumOfNeeds.keySet()) {
            supplies.get(str).decreaseQuantityAvailable(sumOfNeeds.get(str));
        }
        return true;
    }

    public void mergeHashMap (HashMap<String, Integer> target, HashMap<String, Integer> toAdd) {
        for (String str : toAdd.keySet()) {
            if (target.containsKey(str))
                target.put(str, target.get(str) + toAdd.get(str));
            else
                target.put(str, toAdd.get(str));
        }
    }

    public HashMap<String, Integer> checkCommand (Command command) {
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
                mergeHashMap(result, checkCommand(c));
            }
        }
        return result;
    }

    public void addSupply(Supply supply) {
        this.supplies.put(supply.getName(), supply);
    }

    public void setSupplies(HashMap<String, Supply> supplies) {
        this.supplies = supplies;
    }

    public boolean setSupply (String name, int quantity) {
        if(supplies.containsKey(name)) {
            supplies.get(name).setQuantityAvailable(quantity);
            return true;
        } else {
            System.out.println("Unknown Data.Supply");
            return false;
        }
    }

    @Override
    public void extractResults(JSONObject data) {
        JSONArray suppliesResults = (JSONArray) data.get("inventory");
        for (int i = 0 ; i < suppliesResults.size(); i++) {
            JSONObject obj = (JSONObject) suppliesResults.get(i);
            for (Object o : obj.keySet()) {
                long quantity = (long) obj.get((String) o);
                supplies.get((String) o).setQuantityAvailable((int) quantity);
            }
        }
        System.out.println("Data.Supply Business.Manager is notified");
        for(Supply s : supplies.values()) {
            System.out.println(s);
        }
    }


}
