
import Business.*;
import Data.*;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PackageCompilerTest {

    private ImportManager importManager;
    private CommunicationManager communicationManager;
    private ExperimentCompiler experimentCompiler;
    private PackageCompiler packageCompiler;
    private CommandCompiler commandCompiler;

    private ExperimentManager experimentManager;
    private ComponentManager componentManager;
    private SupplyManager supplyManager;


    void initiate() {
        importManager = new ImportManager();
        communicationManager = new CommunicationManager();
        commandCompiler = new CommandCompiler();
        experimentCompiler = new ExperimentCompiler(commandCompiler);
        packageCompiler = new PackageCompiler(communicationManager);

        experimentManager = new ExperimentManager();
        componentManager = new ComponentManager();
        supplyManager = new SupplyManager();

        supplyManager.setSupplies(importManager.importSupplies());
        HashSet<Component> set = new HashSet<>(importManager.importComponents().values());
        componentManager.setComponents(set);

        experimentCompiler.registerObserver(experimentManager);
        experimentCompiler.registerObserver(componentManager);
        experimentCompiler.registerObserver(supplyManager);

        experimentCompiler.registerManager(experimentManager);
        experimentCompiler.registerManager(componentManager);
        experimentCompiler.registerManager(supplyManager);

        packageCompiler.registerObserver(experimentManager);
        packageCompiler.registerObserver(componentManager);
        packageCompiler.registerObserver(supplyManager);

        packageCompiler.registerManager(experimentManager);
        packageCompiler.registerManager(componentManager);
        packageCompiler.registerManager(supplyManager);

        communicationManager.registerObserver(experimentManager);
        communicationManager.registerObserver(componentManager);
        communicationManager.registerObserver(supplyManager);
    }
    @org.junit.jupiter.api.Test
    public void testSendExperiment() {
        initiate();
        Reagent reagent1 = new Reagent("H2", "ads", "asdf", 1, 1);
        ArrayList<ArrayList<String>> reagents = new ArrayList<>();
        ArrayList<String> reagent = new ArrayList<>();
        reagent.add("H2");
        reagent.add("2");
        reagent.add("2");
        reagent.add("2");
        reagent.add("2");
        reagents.add(reagent);
        experimentCompiler.compileReagentExperiment("Reagent Exp 1", "R1", reagents);
        packageCompiler.sendExperiment(experimentManager.getExperiments());
        experimentManager.getExperiments().forEach(experiment -> assertNotSame("Entered" ,experiment.getStatus()));
    }
    @org.junit.jupiter.api.Test
    public void testSendFailForNonExistingReagent() {
        initiate();
        Reagent reagent1 = new Reagent("H2", "ads", "asdf", 1, 1);
        ArrayList<ArrayList<String>> reagents = new ArrayList<>();
        ArrayList<String> reagent = new ArrayList<>();
        reagent.add("2");
        reagent.add("2");
        reagent.add("2");
        reagent.add("2");
        reagent.add("2");
        reagents.add(reagent);
        experimentCompiler.compileSampleOnly("S Ex", "S1", "something",
                2, "2", "s");
        experimentCompiler.compileReagentExperiment("Reagent Exp 1", "R1", reagents);
        packageCompiler.sendExperiment(experimentManager.getExperiments());
        experimentManager.getExperiments().forEach(experiment -> assertSame("Entered" ,experiment.getStatus()));
    }
    @org.junit.jupiter.api.Test
    public void testSendFailForUsingTooMuchReagent() {
        initiate();
        Reagent reagent1 = new Reagent("H2", "ads", "asdf", 1, 1);
        ArrayList<ArrayList<String>> reagents = new ArrayList<>();
        ArrayList<String> reagent = new ArrayList<>();
        reagent.add("H2");
        reagent.add("350");
        reagent.add("2");
        reagent.add("2");
        reagent.add("2");
        reagents.add(reagent);
        experimentCompiler.compileSampleOnly("S Ex", "S1", "something",
                2, "2", "s");
        experimentCompiler.compileReagentExperiment("Reagent Exp 1", "R1", reagents);
        packageCompiler.sendExperiment(experimentManager.getExperiments());
        experimentManager.getExperiments().forEach(experiment -> assertSame("Entered" ,experiment.getStatus()));
    }
    @org.junit.jupiter.api.Test
    public void testSendComplexExperiment() {
        initiate();

        experimentCompiler.compileComplexExperiment("Complex 1", "Complex 2", "C1,1,2,3;C20");
        packageCompiler.sendExperiment(experimentManager.getExperiments());
        experimentManager.getExperiments().forEach(experiment -> assertNotSame("Entered" ,experiment.getStatus()));
    }
    @org.junit.jupiter.api.Test
    public void testSendComplexExperimentWithMacro() {
        initiate();
        initiate();
        commandCompiler.addBasicCommand("Macro1" ,"C1", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C19", "");
        commandCompiler.addBasicCommand("Macro1", "C20", "");
        experimentCompiler.compileComplexExperiment("Complex 1", "Complex 2", "C1,1,2,3;C20;Macro1");
        packageCompiler.sendExperiment(experimentManager.getExperiments());
        experimentManager.getExperiments().forEach(experiment -> assertNotSame("Entered" ,experiment.getStatus()));
    }

}