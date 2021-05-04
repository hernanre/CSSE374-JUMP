import org.json.simple.*;

import java.util.HashSet;

public class SampleExperiment implements Experiment{

    private String experimentID;
    private String experimentName;
    private String sampleName;
    private double quantity;
    private String unit;
    private String location;
    private String status;


    public SampleExperiment(String sampleName, double quantity, String unit, String location) {
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
    public String getLabel() {
        return "Sample Ex: " + sampleName + " of " + quantity + " " + unit;
    }
    public JSONObject toJSONObject(){
        JSONObject experimentInfo = new JSONObject();
        experimentInfo.put("experiment_id", "TEST_ID");
        experimentInfo.put("experiment_name","TEST_Name");
        experimentInfo.put("experiment_type", "Sample-Only");

        JSONArray commands = new JSONArray();

        JSONObject command1 = new JSONObject();
        command1.put("command", "C19");

        JSONObject commandFirstHalf = new JSONObject();
        JSONObject command2 = new JSONObject();

        String params[] = new String[1];
        params[0] = "HPGC-1";
        params.toString();
        command2.put("param", "test");
        command2.put("command", "C15");


//       JSONWriter
        JSONObject command3 = new JSONObject();
        command3.put("command", "C19");

        commands.add(command1);
        commands.add(command2);
        commands.add(command3);
        experimentInfo.put("experiment_commands", commands);
        return experimentInfo;
    }
    }



