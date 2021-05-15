package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ComplexExperiment implements Experiment {
    private String ID, name, status;
    private ArrayList<Command> commandList;
    private int priority;

    public ComplexExperiment(ArrayList<Command> commandList) {
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
        return null;
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

    public ArrayList<Command> getCommandList() {
        return commandList;
    }
}

