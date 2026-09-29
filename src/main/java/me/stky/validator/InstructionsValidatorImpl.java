package me.stky.validator;

import me.stky.commands.MoveCommand;
import me.stky.commands.MoveForwardCommand;
import me.stky.commands.TurnLeftCommand;
import me.stky.commands.TurnRightCommand;

import java.util.ArrayList;
import java.util.List;

public class InstructionsValidatorImpl implements InstructionsValidator {

    public InstructionsValidatorImpl() {
    }

    public List<MoveCommand> validateInstructions(String instructions) {
        List<MoveCommand> commands = new ArrayList<>();

        for (int idx = 0; idx < instructions.length(); idx++) {
            char currentCommand = instructions.charAt(idx);
            switch (currentCommand) {
                case 'L':
                    commands.add(new TurnLeftCommand());
                    break;
                case 'R':
                    commands.add(new TurnRightCommand());
                    break;
                case 'M':
                    commands.add(new MoveForwardCommand());
                    break;
                default:
                    // Ignore invalid instructions
                    break;

            }
        }
        return commands;
    }
}
