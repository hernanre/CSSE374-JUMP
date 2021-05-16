package Data;

import org.json.simple.JSONObject;

public class Command_7 implements Command{

    private final String commandID = "C7";
    private final String CommandName = "Eject Flask";

    public Command_7 () {
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
