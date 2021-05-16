package Data;

import org.json.simple.JSONObject;

public class Command_19 implements Command{
    private final String commandID = "C19";
    private final String CommandName = "Stow Collector";

    public Command_19 () {
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
