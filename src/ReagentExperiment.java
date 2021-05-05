import java.util.ArrayList;
import org.json.simple.*;

public class ReagentExperiment implements Experiment {


    private String ID, name, status;
    private ArrayList<Reagent> reagents;

    public ReagentExperiment(String ID, String name, ArrayList<Reagent> reagents){
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
        return "Sample ID: " + ID + ", Name: " + name;
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
        experimentInfo.put("experiment_id", this.name);
        experimentInfo.put("experiment_name",this.getID());
        experimentInfo.put("experiment_type", "Reagent-Based");

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

}
