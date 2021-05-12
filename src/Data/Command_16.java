package Data;

import org.json.simple.JSONObject;

public class Command_16 implements Command {
    private final String commandID = "C16";
    private final String CommandName = "Collect data";
    private String sensor;

    public Command_16 (String sensor) {
        this.sensor = sensor;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
