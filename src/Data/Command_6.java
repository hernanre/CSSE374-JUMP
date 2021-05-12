package Data;

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
        return null;
    }
}
