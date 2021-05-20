package Business;

import Data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ComponentManager implements Observer, Manager{

    private HashMap<String, Component> components;

    public ComponentManager() {
        this.components = new HashMap<>();
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public boolean validExperiments(ArrayList<Experiment> experiments ) {
        for(Experiment experiment : experiments) {
            for (String str : experiment.getComponentsNeeded()) {
                if (!components.containsKey(str)) {
                    return false;
                } else {
                    if (components.get(str).equals("Failed"))
                        return false;
                }
            }
        }
        return true;
    }

    @Override
    public void extractResults(JSONObject data) {
        System.out.println("\n");
        JSONArray componentsResults = (JSONArray) data.get("capabilities");
        for (Component c: components.values()){
            for (int i = 0 ; i < componentsResults.size(); i++) {
                JSONObject obj = (JSONObject) componentsResults.get(i);
                String status = (String) obj.get(c.getId());
                if (status != null){
                    c.setStatus(status);
                }
            }
        }

        for(Component c  : components.values()) {
            System.out.println(c.getName() + " " + c.getStatus());
        }
        System.out.println("\n");
    }

    @Override
    public void addExperiment(Experiment e) {
        //do nothing
    }

    public void addComponent(Component component) {
        components.put(component.getId(), component);
    }

    public void setComponents(HashSet<Component> components) {
        for (Component c : components) {
            this.components.put(c.getId(), c);
        }
    }
}
