import java.util.ArrayList;

public class ComplexExperiment implements Experiment {

    private ArrayList<ArrayList<String>> commandList;
    private int priority;

    public ComplexExperiment(ArrayList<ArrayList<String>> commandList) {
        this.commandList = commandList;
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getLabel() {
        return null;
    }
}

