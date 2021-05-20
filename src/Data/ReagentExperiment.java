package Data;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.*;

public class ReagentExperiment implements Experiment {


    private String ID, name, status;
    private ArrayList<Reagent> reagents;

    public ReagentExperiment(String name, String ID, ArrayList<Reagent> reagents){
        this.name = name;
        this.ID = ID;
        this.reagents = reagents;
        this.status = "Entered";
    }

    public ArrayList<Reagent> getReagents() {
        return reagents;
    }

    public void addReagemt(Reagent reagent) {
        reagents.add(reagent);
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
        return "Data.Reagent Ex ID: " + ID + ", Name: " + name;
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
    public JSONObject getJSONObject(){
        JSONObject experimentInfo = new JSONObject();
        experimentInfo.put("experiment_id", this.getID());
        experimentInfo.put("experiment_name",this.name);
        experimentInfo.put("experiment_type", "Data.Reagent-Based");

        JSONArray commands = new JSONArray();

        JSONObject command1 = new JSONObject();
        command1.put("command", "C19");


        ArrayList<String> params = new ArrayList<>();
        params.add("HPGC-1");


//       JSONWriter
        JSONObject command2 = new JSONObject();
        command2.put("command", "C8");


        JSONObject command3 = new JSONObject();
        command3.put("command", "C4");
        JSONObject command4 = new JSONObject();

        //this adds all of the commands that require the reagents name and quantity.
        for(int i = 0; i < this.reagents.size(); i++){
            commands.add(this.reagents.get(i).getJSONReagentCommand());
        }

        command4.put("param", params.toString());
        command4.put("command", "C15");
        JSONObject command5 = new JSONObject();
        command5.put("command", "C7");


        commands.add(command1);
        commands.add(command2);
        commands.add(command3);
        experimentInfo.put("experiment_commands", commands);
        return experimentInfo;
    }

    @Override
    public ArrayList<String> getComponentsNeeded() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Arm-1");
        result.add("Arm-2");
        result.add("Arm-3");
        result.add("E1");
        result.add("E2");
        return result;
    }

    @Override
    public HashMap<String, Integer> getSupplyNeeded() {
        HashMap<String, Integer> result = new HashMap<>();
        int flaskNum = 0;
        for (Reagent r : reagents) {
            if (result.containsKey(r.getReagentName()))
                result.put(r.getReagentName(), result.get(r.getReagentName())+r.getQuantity());
            else
                result.put(r.getReagentName(), r.getQuantity());
            flaskNum++;
        }
        result.put("sealed flask", flaskNum);
        return result;
    }

}
