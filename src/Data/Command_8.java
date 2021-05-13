package Data;

import org.json.simple.JSONObject;

public class Command_8 implements Command{

    private final String commandID = "C8";
    private final String CommandName = "Position New Flask";

    public Command_8 () {
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("command", commandID);
        return json;
    }
}
