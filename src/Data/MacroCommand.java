package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MacroCommand implements Command{
    Command[] commands;
    public String name;
    public MacroCommand(String name, Command[] commands){
        this.name = name;
        this.commands = commands;
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
