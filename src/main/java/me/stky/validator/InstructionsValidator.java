package me.stky.validator;

import me.stky.commands.MoveCommand;

import java.util.List;

public interface InstructionsValidator {
    public List<MoveCommand> validateInstructions(String instructions);
}
