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
- Collision check when 2 rovers run into each other, the rover will then stop at its position

## Input file

First line describe the plateau height and width  
For each 2 next lines  
-The rover is described by it's coordinates and facing direction following this format : `x y direction`  
-Then its set of instructions that are either `L` `M` or `R`  
Exemple :

```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

## Output

The position of each rover following this format : `x y direction`  
Exemple :  
```1 2 N```  
For rover at x:1, y:2 and facing north

## Others

This was made in IntelliJ with Java 25 using SDK : Microsoft OpenJDK 25.0.4