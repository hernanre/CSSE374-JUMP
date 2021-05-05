import org.json.simple.JSONObject;

public interface Experiment{
    public void updateStatus();
    public int getPriority();
    public String getLabel();
    public String getID();
    public void setStatus(String status);
    public String getStatus();
    public JSONObject getJSONObject();
}
