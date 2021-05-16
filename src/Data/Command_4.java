package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Command_4 implements Command{

    private final String commandID = "C4";
    private final String CommandName = "Mix";
    private String c;

    public Command_4 (String c) {
        this.c = c;
    }

    @Override
    public JSONObject toJson() {
        JSONArray array = new JSONArray();
        array.add(c);


        JSONObject json = new JSONObject();
        json.put("command", commandID);
        json.put("params" ,array);
        return json;
    }
    @Override
    public String getCommandID() {
        return commandID;
    }

}
