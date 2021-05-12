package Data;

import org.json.simple.JSONObject;

public class Command_18 implements Command{
    private final String commandID = "C18";
    private final String CommandName = "Deploy Collector";
    private String mode;

    public Command_18 (String mode) {
        this.mode = mode;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
