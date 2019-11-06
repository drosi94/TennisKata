**Tennis Kata**

Tennis challenge crafted for Wemanity.

Requires Java 8 + (JDK >1.8)

Requires Maven for running the tests & coverage

_Build_
```bash
cd /kataTennis/src/main/java
javac Main.java
```

_Run_
```bash
java Main
```

_Test & Coverage_
```bash
mvn clean test
```
target/site/jacoco/index.html to check the coverage

Used TDD approach

The cycle was:

** For each use case
* Create failing test
* Create code just to pass the test
* Refactor code and the test if it was needed

The tests ar structured with the AAA approach, using 3 blocks for Arrange-Assign-Assert.

At the end some small refactoring in code and testing.

Total Coverage 70%

_Output Example_
```bash
Give first player's name:

Vasileios


Give second player's name:

Ioannis
14:40:27.385 [main] INFO tennis.GameState - Turn: 0
Score: 0 - 0

14:40:27.388 [main] INFO tennis.GameState - Turn: 1
Score: 0 - 15

14:40:27.389 [main] INFO tennis.GameState - Turn: 2
Score: 15 - 15

14:40:27.389 [main] INFO tennis.GameState - Turn: 3
Score: 15 - 30

14:40:27.389 [main] INFO tennis.GameState - Turn: 4
Score: 30 - 30

14:40:27.389 [main] INFO tennis.GameState - Turn: 5
Score: 30 - 40

14:40:27.389 [main] INFO tennis.Game - Game winner: Ioannis
Score: 30 - 40 at turn 6


```

## About this Kata

This Kata is about implementing a simple tennis game. I came up with it while thinking about Wii tennis, where they have simplified tennis, so each set is one game.

The scoring system is rather simple:

1. Each player can have either of these points in one game 0 15 30 40

2. If you have 40 and you win the ball you win the game, however there are special rules.

3. If both have 40 the players are deuce. 
  
    a. If the game is in deuce, the winner of a ball will have advantage and game ball. 
  
    b. If the player with advantage wins the ball he wins the game 
  
    c. If the player without advantage wins they are back at deuce.

===== Alternate description of the rules per Wikipedia ( http://en.wikipedia.org/wiki/Tennis#Scoring ):

1. A game is won by the first player to have won at least four points in total and at least two points more than the opponent.

2. The running score of each game is described in a manner peculiar to tennis: scores from zero to three points are described as “love”, “fifteen”, “thirty”, and “forty” respectively.

3. If at least three points have been scored by each player, and the scores are equal, the score is “deuce”.

4. If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is “advantage” for the player in the lead.

**IMPORTANT:**  Implement the requirements focusing on **writing the best code** you can produce.
