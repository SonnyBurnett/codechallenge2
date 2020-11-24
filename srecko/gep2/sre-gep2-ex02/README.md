# Project Object Oriented Programing
## Expert Assignment 2
### Assignment 2: Experts
1. Before you start download the file inputs.csv from 
https://www.henrybeen.nl/wpcontent/uploads/2020/10/002-experts.txt
2. Write a program that:
    1. Contains a number of classes for moddelling a game of Tic Tac Toe
    2. Reads a tic-tac-toe board from the three lines in inputs.txt
    3. Writes a file outputs.csv that contains one line with the one of the following, first applicable:\
        1\. WINNER X &nbsp;&nbsp;&nbsp; 3. NEXTMOVE X\
        2\. WINNER O &nbsp;&nbsp;&nbsp; 4. NEXTMOVE O
        
Remember, the goal of the assignment: write an OO implementation that solves the problem,
but mostly shows off your OO skills. Do not focus only solving the problem.

### Assignment 2: Experts – example inputs and outputs
Input file:
```
XXX
OOX
OO.
```
Output file:
```
WINNER X
```
## Brainstorm
For this assignment I did incorporate some of the feedback, however not all as the slides are not fully available.
As part of the decisions in assignment 1 I dropped the use of using the main as the control flow, 
so it now it is pointing to the running method of "real main class". I do still use the recommendations on how to use
Abstraction and Interfaces as they conflict with KISS and SOLID (mostly at the Liskov substation principle). 
If full abstraction is necessary in the assignment(s) it will need to point this precedence over the suggestions of
certain methodologies or part of the methodologies' advice that fits the level of abstraction. On this I stick with 
KISS, DRY and YAGNI (this is possibly the one that's costing me points on this assignment as I am an XP supporter).

The following has been applied:
    - Only the requested functionalities are live.
    - Implementation according to the specification in the PPTX ea only using the proposed files names.
    - No implementation of a DRAW (not one of the 4 choices).
    - Only one board setup in the text file has been allowed.
    - Only one result will be written in the csv file.

 Here below I show a simplified diagram model on usages and dependencies etc. 
 (note: this is simplified and not a real UML compliant representation, due to ascii art):
```
    +-----------------------+   
    | Class | Cell          |
    +-----------------------+ 
                |
    +-----------------------+
    | Class | Board         |
    +-----------------------+
                |
    +-----------------------+       +-----------------------+
    | Class | TicTacToe     |-------| Enumeration | Player  |        
    +-----------------------+       +-----------------------+
                |
                |                   +-----------------------+
                ├-------------------| Class | GameState     |        
                |                   +-----------------------+
                |      
    +-----------------------+       +-----------------------+
    | Abstract Class | Game |-------| Interface | fileLoader|        
    +-----------------------+       +-----------------------+
                |                   +-----------------------+
                └-------------------| Interface | fileWiter |        
                                    +-----------------------+
```