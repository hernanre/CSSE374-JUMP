import java.util.ArrayList;
import java.util.Comparator;
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
}
