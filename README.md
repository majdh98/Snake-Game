# Snake Game
>  Author: Majd Hamdan

> January, 2019
## Overview
A java applet that re-creates the snake game. Use keyboard arrows to move the snake. Map is reconstructed every 10 seconds. Walls are added to map randomly. Location of walls is randomized every map reconstruction. There are 3 types of food:
- Green: Extends the length of the snake by one unit. Re-generate at a random place on the map after eating it. 
- Yellow: Extends the length of the snake by one unit and reduce the speed of the snake by half. Only regenerates when map is reconstructed.
- Purple: Extends the length of the snake by one unit and doubles the speed of the snake. Only regenerates when map is reconstructed.
- Red: Poison. Moves across the map horizontally or vertically. Position and direction of movement is randomized again upon map reconstruction. 

Snake can pass through the edge of the map and reappear at the opposite edge. Score will be logged to the screen at the end of the game.



## Content
- [Eclipse_Snake_Game](https://github.com/majdh98/Snake-Game/tree/main/Eclipse_Snake_%20Game): Entire Eclipse project of the game.
The Eclipse project contains four main classes:
- [node.java](https://github.com/majdh98/Snake-Game/blob/main/node.java): A java class to represent a node on the map (snake body node, food node, wall node...).
- [Food.java ](https://github.com/majdh98/Snake-Game/blob/main/Food.java): A java class that extends node.java. Added functionality to handle food type.
- [snakebody.java](https://github.com/majdh98/Snake-Game/blob/main/snakebody.java): A java class to represent the snake body. Contains functionality to move the snake and extend its length. Snake body is represented with an array. Each element of the array is a node (x and y coordinates). Head of the snake is always one index less than the tail of the snake. To move the snake, we overwrite the head of the snake and shift the index of head and tail node.
- [snakeApplet.java](https://github.com/majdh98/Snake-Game/blob/main/snakeApplet.java): Java applet class. Runs the snake game, reconstruct the map, detect when a snake eats food/hit walls.
   
