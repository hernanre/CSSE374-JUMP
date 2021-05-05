import org.json.simple.JSONObject;

import java.util.ArrayList;

interface Observer {

    public void notifyObserver();
    public boolean validExperiments(ArrayList<Experiment> experiments);
    public void extractResults(JSONObject data);

}
