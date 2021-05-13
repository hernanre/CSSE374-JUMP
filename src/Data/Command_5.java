package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Command_5 implements Command{
    private final String commandID = "C5";
    private final String CommandName = "Eject Sample";
    private int sampleID;

    public Command_5 (int sampleID) {
        this.sampleID = sampleID;
    }

    @Override
    public JSONObject toJson() {
        JSONArray array = new JSONArray();
        array.add(sampleID);


        JSONObject json = new JSONObject();
        json.put("command", commandID);
        json.put("params" ,array);
        return json;
    }
}
