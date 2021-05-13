package Data;

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
       for (Command command: commands){

       }
       return macro;
    }
}
