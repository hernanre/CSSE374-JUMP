package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Command_6 implements Command{

    private final String commandID = "C6";
    private final String CommandName = "Add";
    private String reagent;
    private int amount;

    public Command_6 (String reagent, int amount) {
        this.reagent = reagent;
        this.amount = amount;
    }

    @Override
    public JSONObject toJson() {
        JSONArray array = new JSONArray();
        array.add(reagent);
        array.add(amount);

        JSONObject json = new JSONObject();
        json.put("command", commandID);
        json.put("params" ,array);
        return json;
    }
}
