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
            mergeHashMap(sumOfNeeds, experiment.getSupplyNeeded());
        }
        for(String str : sumOfNeeds.keySet()) {
            if (!supplies.containsKey(str)){
                System.out.println("No supply named " + str);
                return false;
            }
            if(supplies.get(str).getQuantityAvailable() < sumOfNeeds.get(str)) {
                System.out.println("Not enough supply named " + str + " in total");
                return false;
            }
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
