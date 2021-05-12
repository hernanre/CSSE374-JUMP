package Data;

import org.json.simple.JSONObject;

public class Command_2 implements Command{

    private final String commandID = "C2";
    private final String CommandName = "Move Internal Arm";
    private int x;
    private int y;
    private int z;

    public Command_2 (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
