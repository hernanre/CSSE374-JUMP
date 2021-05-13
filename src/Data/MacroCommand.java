package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class MacroCommand implements Command{
    ArrayList<Command> commands;
    public String name;

    public MacroCommand(String name){
        this.name = name;
        commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public JSONObject toJson() {
        JSONObject macro = new JSONObject();
        JSONArray coms =  new JSONArray();
       for (Command command: commands){
        coms.add(command.toJson());
       }
       macro.put(name, coms);
       return macro;
    }
}
