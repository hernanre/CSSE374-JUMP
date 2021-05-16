package Data;

import org.json.simple.JSONObject;

public class Command_20 implements Command{
    private final String commandID = "C20";
    private final String CommandName = "collect sample";

    public Command_20 () {
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("command", commandID);
        return json;
    }
    @Override
    public String getCommandID() {
        return commandID;
    }
}
