package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Command_14 implements Command{
    private final String commandID = "C14";
    private final String CommandName = "Internal video";
    private int x;
    private int y;
    private int z;
    private int time;

    public Command_14 (int x, int y, int z, int time) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
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
