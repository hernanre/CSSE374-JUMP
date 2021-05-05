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
                    return false;
                } else if (supplies.get("caps").getQuantityAvailable() < 1) {
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
                    if(!supplies.containsKey(r.getReagentName()))
                        return false;
                    if (supplies.get(r.getReagentName()).getQuantityAvailable() < r.getQuantity())
                        return false;
                    if(sumOfNeeds.containsKey(r.getReagentName()))
                        sumOfNeeds.put(r.getReagentName(), sumOfNeeds.get(r.getReagentName()) + r.getQuantity());
                    else
                        sumOfNeeds.put(r.getReagentName(), r.getQuantity());
                    flaskNum++;
                }
                if (supplies.get("sealed flask").getQuantityAvailable() < flaskNum)
                    return false;
                sumOfNeeds.put("sealed flask", flaskNum);
            }
        }
        for(String str : sumOfNeeds.keySet()) {
            supplies.get(str).decreaseQuantityAvailable(sumOfNeeds.get(str));
        }
        return true;
    }

    public void addSupply(Supply supply) {
        this.supplies.put(supply.getName(), supply);
    }
}
