
import Business.*;
import Data.*;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MacroCreationTest {
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
    public void createMacroCommandWithAllBasic() {
        initiate();
        commandCompiler.addBasicCommand("Macro1" ,"C1", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C2", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C3", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C4", "");
        commandCompiler.addBasicCommand("Macro1" ,"C5", "1");
        commandCompiler.addBasicCommand("Macro1" ,"C6", "H2, 2");
        commandCompiler.addBasicCommand("Macro1" ,"C7", "");
        commandCompiler.addBasicCommand("Macro1" ,"C8", "");
        commandCompiler.addBasicCommand("Macro1" ,"C9", "add");
        commandCompiler.addBasicCommand("Macro1" ,"C10", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C11", "");
        commandCompiler.addBasicCommand("Macro1" ,"C12", "1,2,3,4");
        commandCompiler.addBasicCommand("Macro1" ,"C13", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C14", "1,2,3,4");
        commandCompiler.addBasicCommand("Macro1" ,"C15", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C16", "add");
        commandCompiler.addBasicCommand("Macro1" ,"C17", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C18", "add");
        commandCompiler.addBasicCommand("Macro1" ,"C19", "");
        commandCompiler.addBasicCommand("Macro1", "C20", "");
        MacroCommand macroCommand = (MacroCommand) commandCompiler.getMacro("Macro1");
        assertEquals(macroCommand.getCommands().size(), 20);

    }
    @org.junit.jupiter.api.Test
    public void createMacroCommand () {
        initiate();
        commandCompiler.addBasicCommand("Macro1" ,"C1", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C19", "");
        commandCompiler.addBasicCommand("Macro1", "C20", "");
        MacroCommand macroCommand = (MacroCommand) commandCompiler.getMacro("Macro1");
        assertEquals("{\"Macro1\":[{\"params\":[1,2,3],\"command\":\"C1\"},{\"command\":\"C19\"},{\"command\":\"C20\"}]}",
                macroCommand.toJson().toJSONString());
    }

    @org.junit.jupiter.api.Test
    public void deleteMacroCommand(){
        initiate();
        commandCompiler.addBasicCommand("Macro1" ,"C1", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C19", "");
        commandCompiler.addBasicCommand("Macro1", "C20", "");
        commandCompiler.deleteMacro("Macro1");
        assertEquals(null, commandCompiler.getMacro("Macro1"));
    }
    @org.junit.jupiter.api.Test
    public void editMacroCommand(){
        initiate();
        commandCompiler.addBasicCommand("Macro1" ,"C1", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C19", "");
        commandCompiler.addBasicCommand("Macro1", "C20", "");
        commandCompiler.deleteMacro("Macro1");
        commandCompiler.addBasicCommand("Macro1" ,"C1", "2,3,4");
        commandCompiler.addBasicCommand("Macro1" ,"C19", "");
        commandCompiler.addBasicCommand("Macro1", "C20", "");
        MacroCommand macroCommand = (MacroCommand) commandCompiler.getMacro("Macro1");
        assertEquals("{\"Macro1\":[{\"params\":[2,3,4],\"command\":\"C1\"},{\"command\":\"C19\"},{\"command\":\"C20\"}]}",
                macroCommand.toJson().toJSONString());
    }
    @org.junit.jupiter.api.Test
    public void createMacroCommandWithMacroCommand () {
        initiate();
        commandCompiler.addBasicCommand("Macro1" ,"C1", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C1", "1,2,3");
        commandCompiler.addBasicCommand("Macro1" ,"C19", "");
        commandCompiler.addBasicCommand("Macro1", "C20", "");
        commandCompiler.addBasicCommand("Macro2", "C20", "");
        commandCompiler.addBasicCommand("Macro2", "Macro1", "");
        MacroCommand macroCommand = (MacroCommand) commandCompiler.getMacro("Macro2");
        assertEquals("{\"Macro2\":[{\"command\":\"C20\"},{\"Macro1\":[{\"params\":[1,2,3],\"command\":\"C1\"},{\"params\":[1,2,3],\"command\":\"C1\"},{\"command\":\"C19\"},{\"command\":\"C20\"}]}]}",
                macroCommand.toJson().toJSONString());
    }
}
