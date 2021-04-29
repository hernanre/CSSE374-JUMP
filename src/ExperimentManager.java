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

    @Override
    public boolean validExperiment(Experiment experiment) {
        return true;
    }
}
