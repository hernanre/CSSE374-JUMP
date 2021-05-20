package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SampleExperiment implements Experiment{

    private String experimentID;
    private String experimentName;
    private String sampleName;
    private double quantity;
    private String unit;
    private String location;
    private String status;


    public SampleExperiment(String experimentName, String experimentID, String sampleName, double quantity, String unit, String location) {
        this.experimentName = experimentName;
        this.experimentID = experimentID;
        this.sampleName = sampleName;
        this.quantity = quantity;
        this.unit = unit;
        this.location = location;
        this.status = "Entered";
    }

    @Override
    public void updateStatus() {

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
    public int getPriority() {
        return 0;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getSampleName() {
        return sampleName;
    }

    public String getLocation() {
        return location;
    }

    public String getUnit() {
        return unit;
    }

    public String getExperimentID() {
        return experimentID;
    }

    public String getExperimentName() {
        return experimentName;
    }

    @Override
    public String getID() {
        return experimentID;
    }
    @Override
    public String getLabel() {
        return "Sample Ex ID: " + experimentID + ", Name: " + experimentName;
    }

    @Override
    public JSONObject getJSONObject(){
        JSONObject experimentInfo = new JSONObject();
        experimentInfo.put("experiment_id", this.experimentID);
        experimentInfo.put("experiment_name",this.experimentName);
        experimentInfo.put("experiment_type", "Sample-Only");

        JSONArray commands = new JSONArray();

        JSONObject command1 = new JSONObject();
        command1.put("command", "C19");

        JSONObject commandFirstHalf = new JSONObject();
        JSONObject command2 = new JSONObject();

        ArrayList<String> params = new ArrayList<>();
        params.add("HPGC-1");
        params.toString();
        command2.put("param", params.toString());
        command2.put("command", "C15");


//       JSONWriter
        JSONObject command3 = new JSONObject();
        command3.put("command", "C5");

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
        result.add("T1");
        result.add("T2");
        result.add("T3");
        result.add("E1");
        result.add("HPGC-1");
        return result;
    }

    @Override
    public HashMap<String, Integer> getSupplyNeeded() {
        HashMap<String, Integer> result = new HashMap<>();
        result.put("caps", 1);
        result.put("test tube", 1);
        return result;
    }
}



