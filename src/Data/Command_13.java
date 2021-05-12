package Data;

import org.json.simple.JSONObject;

public class Command_13 implements Command{
    private final String commandID = "C13";
    private final String CommandName = "External photo";
    private int x;
    private int y;
    private int z;

    public Command_13 (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
