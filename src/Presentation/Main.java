package Presentation;

import Business.*;
import Data.Component;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        ImportManager importManager = new ImportManager();
        CommandCompiler commandCompiler = new CommandCompiler();
        CommunicationManager communicationManager = new CommunicationManager();
        ExperimentCompiler experimentCompiler = new ExperimentCompiler(commandCompiler);
        PackageCompiler packageCompiler = new PackageCompiler(communicationManager);

        ExperimentManager experimentManager = new ExperimentManager();
        ComponentManager componentManager = new ComponentManager();
        SupplyManager supplyManager = new SupplyManager();

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

        new JUMPUI(experimentCompiler, packageCompiler, experimentManager, commandCompiler);
    }

}
