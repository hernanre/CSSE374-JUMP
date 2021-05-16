package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Command_9 implements Command{

    private final String commandID = "C9";
    private final String CommandName = "Internal Arm Grasp Tool";
    private String tool;

    public Command_9 (String tool) {
        this.tool = tool;
    }

    @Override
    public JSONObject toJson() {

        JSONArray array = new JSONArray();
        array.add(tool);

        JSONObject json = new JSONObject();
        json.put("command", commandID);
        json.put("params" ,array);
        return json;
    }

    public String getTool() {
        return tool;
    }
    @Override
    public String getCommandID() {
        return commandID;
    }
}
