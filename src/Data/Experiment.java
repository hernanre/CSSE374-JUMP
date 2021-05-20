package Data;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public interface Experiment{
    public void updateStatus();
    public int getPriority();
    public String getLabel();
    public String getID();
    public void setStatus(String status);
    public String getStatus();
    public JSONObject getJSONObject();
    public ArrayList<String> getComponentsNeeded();
    public HashMap<String, Integer> getSupplyNeeded();
}
