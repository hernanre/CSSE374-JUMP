package Data;

import org.json.simple.JSONObject;

public class Command_10 implements Command{

    private final String commandID = "C10";
    private final String CommandName = "Internal Arm Use tool";
    private int x;
    private int y;
    private int z;

    public Command_10 (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
