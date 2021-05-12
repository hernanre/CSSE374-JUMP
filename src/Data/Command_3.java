package Data;

import org.json.simple.JSONObject;

public class Command_3 implements Command{

    private final String commandID = "C3";
    private final String CommandName = "Grasp Object";
    private int x;
    private int y;
    private int z;

    public Command_3 (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
