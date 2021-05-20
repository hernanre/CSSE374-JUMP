package Business;

import Data.*;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandCompiler {

    private HashMap<String, MacroCommand> macros;

    public CommandCompiler() {
        macros = new HashMap<>();
    }

    public Command getMacro(String commandName) {
        return macros.get(commandName.trim());
    }

    public void addBasicCommand(String macronName, String commandID, String inputs) throws NumberFormatException,
            IndexOutOfBoundsException{
        if (!macros.containsKey(macronName.trim())) {
            macros.put(macronName, new MacroCommand(macronName.trim()));
        }
        macros.get(macronName).addCommand(parseBasicCommand(commandID.trim(), inputs.trim()));
    }

    public Command parseBasicCommand (String commandID, String inputs) throws NumberFormatException, IndexOutOfBoundsException{
        Command command = null;
        ArrayList<Integer> integers = new ArrayList<>();
        switch (commandID){
            case "C1":
                integers = parseInput(inputs);
                if(integers.size() != 3)
                    throw new IndexOutOfBoundsException();
                command = new Command_1(integers.get(0), integers.get(1), integers.get(2));
                break;
            case "C2":
                integers = parseInput(inputs);
                if(integers.size() != 3)
                    throw new IndexOutOfBoundsException();
                command = new Command_2(integers.get(0), integers.get(1), integers.get(2));
                break;
            case "C3":
                integers = parseInput(inputs);
                if(integers.size() != 3)
                    throw new IndexOutOfBoundsException();
                command = new Command_3(integers.get(0), integers.get(1), integers.get(2));
                break;
            case "C4":
                command = new Command_4(inputs.trim());
                break;
            case "C5":
                command = new Command_5(Integer.valueOf(inputs));
                break;
            case "C6":
                String[] strs = inputs.split(",");
                command = new Command_6(strs[0].trim(), Integer.valueOf(strs[1].trim()));
                break;
            case "C7":
                command = new Command_7();
                break;
            case "C8":
                command = new Command_8();
                break;
            case "C9":
                command = new Command_9(inputs.trim());
                break;
            case "C10":
                integers = parseInput(inputs);
                if(integers.size() != 3)
                    throw new IndexOutOfBoundsException();
                command = new Command_10(integers.get(0), integers.get(1), integers.get(2));
                break;
            case "C11":
                command = new Command_11();
                break;
            case "C12":
                integers = parseInput(inputs);
                if(integers.size() != 4)
                    throw new IndexOutOfBoundsException();
                command = new Command_12(integers.get(0), integers.get(1), integers.get(2), integers.get(3));
                break;
            case "C13":
                integers = parseInput(inputs);
                if(integers.size() != 3)
                    throw new IndexOutOfBoundsException();
                command = new Command_13(integers.get(0), integers.get(1), integers.get(2));
                break;
            case "C14":
                integers = parseInput(inputs);
                if(integers.size() != 4)
                    throw new IndexOutOfBoundsException();
                command = new Command_14(integers.get(0), integers.get(1), integers.get(2), integers.get(3));
                break;
            case "C15":
                integers = parseInput(inputs);
                if(integers.size() != 3)
                    throw new IndexOutOfBoundsException();
                command = new Command_15(integers.get(0), integers.get(1), integers.get(2));
                break;
            case "C16":
                command = new Command_16(inputs.trim());
                break;
            case "C17":
                integers = parseInput(inputs);
                if(integers.size() != 3)
                    throw new IndexOutOfBoundsException();
                command = new Command_17(integers.get(0), integers.get(1), integers.get(2));
                break;
            case "C18":
                command = new Command_18(inputs.trim());
                break;
            case "C19":
                command = new Command_19();
                break;
            case "C20":
                command = new Command_20();
                break;
            default:
                if(macros.containsKey(commandID)) {
                    command = macros.get(commandID);
                }
                break;
        }
        if(command != null) {
            System.out.println(command.toJson().toJSONString().toString());
        }
        return command;
    }

    public ArrayList<Integer> parseInput(String input) throws NumberFormatException{
        ArrayList<Integer> result = new ArrayList<>();
        String[] strings = input.split(",");
        if(strings.length != 3 && strings.length != 4) {
            throw new IndexOutOfBoundsException();
        }
        result.add(Integer.valueOf(strings[0].trim()));
        result.add(Integer.valueOf(strings[1].trim()));
        result.add(Integer.valueOf(strings[2].trim()));
        if (strings.length == 4)
            result.add(Integer.valueOf(strings[3].trim()));
        return result;
    }

    public void deleteMacro (String macroName) {
        if (!macros.containsKey(macroName)) {
            System.out.println("No Macro named " + macroName);
        } else {
            macros.remove(macroName);
        }
    }
}
