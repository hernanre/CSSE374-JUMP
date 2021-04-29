import java.util.Comparator;
import java.util.PriorityQueue;

public class ExperimentManager implements Observer, Manager{

    private PriorityQueue<Experiment> experiments;

    public ExperimentManager() {
        this.experiments = new PriorityQueue<>(new Comparator<Experiment>() {
            @Override
            public int compare(Experiment o1, Experiment o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });
    }

    @Override
    public void addExperiment(Experiment e) {
        experiments.add(e);
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public boolean validExperiment(Experiment experiment) {
        return true;
    }
}
