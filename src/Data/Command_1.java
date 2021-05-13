package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Command_1 implements Command{

    private final String commandID = "C1";
    private final String CommandName = "Move External Arm";
    private int x;
    private int y;
    private int z;

    public Command_1 (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public JSONObject toJson() {
        JSONArray array = new JSONArray();
        array.add(x);
        array.add(y);
        array.add(z);

        JSONObject json = new JSONObject();
        json.put("command", commandID);
        json.put("params" ,array);
        return json;
    }
}
