package Data;

import org.json.simple.JSONObject;

public class Command_11 implements Command {

    private final String commandID = "C11";
    private final String CommandName = "Internal Arm return tool";

    public Command_11 () {
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
