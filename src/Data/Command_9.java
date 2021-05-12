package Data;

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
        return null;
    }
}
