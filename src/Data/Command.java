package Data;

import org.json.simple.JSONObject;

public interface Command {
    JSONObject toJson();
    String getCommandID();
}
