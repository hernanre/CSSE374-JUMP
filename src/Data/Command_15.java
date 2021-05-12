package Data;

import org.json.simple.JSONObject;

public class Command_15 implements Command{
    private final String commandID = "C15";
    private final String CommandName = "Internal photo";
    private int x;
    private int y;
    private int z;

    public Command_15 (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
