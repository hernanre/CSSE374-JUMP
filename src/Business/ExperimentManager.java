package Business;

import Data.Experiment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ExperimentManager implements Observer, Manager{

    private PriorityQueue<Experiment> experiments;

    public ExperimentManager() {
        this.experiments = new PriorityQueue<>((o1, o2) -> o1.getPriority() - o2.getPriority());
    }

    @Override
    public void addExperiment(Experiment e) {
        experiments.add(e);
    }

    @Override
    public void notifyObserver() {

    }

    public PriorityQueue<Experiment> getExperiments() {
        return experiments;
    }

    @Override
    public boolean validExperiments(ArrayList<Experiment> experiments) {
        return true;
    }

    @Override
    public void extractResults(JSONObject data) {
        System.out.println("\n");
        JSONArray experimentsResults = (JSONArray) data.get("experiment_status");
        for (Experiment e: experiments){
            for (int i = 0 ; i < experimentsResults.size(); i++) {
                JSONObject obj = (JSONObject) experimentsResults.get(i);
                String status = (String) obj.get(e.getID());
                if (status != null){
                    e.setStatus(status);
                }
            }
        }
        for(Experiment experiment : experiments) {
            System.out.println(experiment.getLabel() + " " + experiment.getStatus());
        }
        System.out.println("\n");
    }
}
