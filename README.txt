=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: carolgao
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
    I used a 2D array to store the current state of each square on this 4 x 4 board. I used 0 to represent blank square
    and for squares that have certain numbers, I used those numbers (like 2 or 4) to represent them.
    When the game starts, all the squares (except for one square with a randomly generated number 2) would be 0 or blank.

  2. Maps
    I stored all subtype squares with different attributes such as numbers and corresponding colors in a map.
    So that every time when there is an update on the number in a square, I can change the color of the square.

  3. File I/O
    I used File I/O to store the game state of the board. I used a text file to store the highest score and update on it
    when current store is higher than the highest score.

  4. Inheritance
    There is the Tile superclass that is able to set the offsets of x and y values. It has two subtypes -- the EmptyTile
    class and the NumberedTile class. The EmptyTile class draws the empty tiles with white color. On the other hand, the
    NumberedTile subtype adds numbers and colors, and draws the squares based on their corresponding colors and draws
    the numbers in the middle of the square too.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
    Tile: This is an abstract class that provides basic functions, such as the position setters, for tiles.
    EmptyTile: Subtype of Tile class. EmptyTile are responsible for empty tiles and draw the empty tiles with white
                color on the position according to their offset values.
    NumberedTile: Subtype of Tile class. Responsible for tiles that actually have numbers. It adds numbers and colors,
                 and draws the squares with corresponding colors and numbers in the middle.
    ScoreListener: This is a listener interface that provides functions to update scores and whether the current game
                   state has lost or not.
    GameInstruction: This class is responsible for the game instruction window that pops up when I run the game. After I
                     press "start game" button, it will run the RunTwentyFortyEight class and run the game.
    GameCourt: This class implements the overall game logic and event-handling from the GUI. There is also the logic of
               how to calculate and update scores and the numbers in the squares. It handles keyboard events (eg: up) to
               trigger board updates.
    RunTwentyFortyEight: This class maintains the global panel and layout of the game. It handles user data, reads the
                        highest past score, and determines whether to rewrite it or not depending on the current scores.
                        It implements and keeps track of the current and highest scores, and the current game status.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
    One of my challenges is to deal with the situation when I press a key and this move is not valid, then I have to
    check whether there is a move happened on the board before I continue to generate a random 2 on the board.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
    In the RunTwentyFortyEight class, I separated the main panel (include the display with the scores and reset button)
    and game board, and implemented the IO so that it is only within this class.
    Additionally, each tile in the Tile class draws itself, and this is separate from the game court.

    In the current game, there is private data in a class that can be accessed by another class. I created the
    ScoreListener interface so that data can be referenced/passed on to another class.

    I would like to refine the UI layout of the board to make the border smoother. For the instruction page, I would
    like to include images too, in addition to text instruction.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

  N/A
