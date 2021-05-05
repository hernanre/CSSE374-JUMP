import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class ComponentManager implements Observer, Manager{

    private HashSet<Component> components;

    public ComponentManager() {
        this.components = new HashSet<>();
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public boolean validExperiments(ArrayList<Experiment> experiments ) {
        int check = 0;
        for(Experiment experiment : experiments) {
            if (experiment instanceof SampleExperiment &&
                    check != 1 && check != 3 && check != 5 && check != 7) {
                for (Component c : components) {
                    if (c.getType().equals("ARM") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getName().equals("Sample Ejector") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getId().equals("T1") || c.getId().equals("T2")
                            || c.getId().equals("T3")) {
                        if (c.getStatus().equals("Failed"))
                            return false;
                    } else if (c.getId().equals("HPGC-1") && c.getStatus().equals("Failed")) {
                        return false;
                    }
                }
                check += 1;
            } else if (experiment instanceof ReagentExperiment&&
                    check != 2 && check != 3 && check != 6 && check != 7) {
                for (Component c : components) {
                    if (c.getType().equals("ARM") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getType().equals("Ejector") && c.getStatus().equals("Failed")) {
                        return false;
                    } else if (c.getType().equals("Tool") && c.getStatus().equals("Failed")) {
                        return false;
                    }
                }
                check += 2;
            }
            //We only have sample only  and reagent based
            if(check == 3) {
                break;
            }
        }
        return true;
    }

    @Override
    public void extractResults(JSONObject data) {
        JSONArray componentsResults = (JSONArray) data.get("capabilities");
        for (Component c: components){
            for (int i = 0 ; i < componentsResults.size(); i++) {
                JSONObject obj = (JSONObject) componentsResults.get(i);
                String status = (String) obj.get(c.getId());
                if (status != null){
                    c.setStatus(status);
                }
            }
        }
    }

    @Override
    public void addExperiment(Experiment e) {
        //do nothing
    }

    public void addComponent(Component component) {
        components.add(component);
    }
}
