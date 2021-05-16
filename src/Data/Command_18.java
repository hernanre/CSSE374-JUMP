package Data;

import org.json.simple.*;

public class Command_18 implements Command{
    private final String commandID = "C18";
    private final String CommandName = "Deploy Collector";
    private String mode;

    public Command_18 (String mode) {
        this.mode = mode;
    }

    @Override
    public JSONObject toJson() {
        JSONArray array = new JSONArray();
        array.add(mode);

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
