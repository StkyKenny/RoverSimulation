## HOW TO LAUNCH

 	java -jar rover.jar input.txt

## Program pipeline (simplified)

- Read the file, parse the data, check for invalid data
- For each rover :
    - Find new position
    - Check for collision and out of Plateau
    - Move
    - Repeat until all instructions are done
    - Then display the final position

## Choices made

- Required 1 parameter which is the path to the input file, additional parameters will be ignored
- Collision with other Rovers are detected in which case they will stop all commands
- Unrecognized rover instructions are ignored
- In case of blocking error (making it unable to proceed), the program will stop
- No advanced logging setup, so error message do not contains technical details
- Collision check when 2 rovers run into each other, the rover will then stop at its position


- Too few commands for the command pattern

## Input file

~Not described here~

## Others

This was made in IntelliJ with Java 25 using SDK : Eclipse Temurin 25.0.2