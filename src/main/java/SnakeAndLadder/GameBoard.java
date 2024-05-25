package SnakeAndLadder;

import java.util.List;
import java.util.Map;
import java.util.Queue;

class GameBoard {
    private Dice dice;
    private Queue<Player> nextTurn;
    private List<Jumper> snakes;
    private List<Jumper> ladders;
    private Map<String, Integer> playersCurrentPosition;
    private int boardSize;

    GameBoard(Dice dice, Queue<Player> nextTurn, List<Jumper> snakes, List<Jumper> ladders, Map<String, Integer> playersCurrentPosition, int boardSize) {
        this.dice = dice;
        this.nextTurn = nextTurn;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playersCurrentPosition = playersCurrentPosition;
        this.boardSize = boardSize;
    }


    void startGame() {
        while (!nextTurn.isEmpty()) {
            Player player = nextTurn.poll();
            int currentPosition = playersCurrentPosition.get(player.getPlayerName());
            int diceValue = dice.rollDice();
            int nextCell = currentPosition + diceValue;
            if (nextCell <= boardSize) {
                int[] nextPosition = new int[]{nextCell};
                boolean[] ladderPresent = new boolean[]{false};

                snakes.forEach(v -> {
                    if (v.getStartPoint() == nextCell) {
                        nextPosition[0] = v.getEndPoint();
                    }
                });
                if (nextPosition[0] != nextCell) {
                    System.out.println(player.getPlayerName() + " bitten by snake at " + nextCell + " and moved from " + nextCell + " to " + nextPosition[0]);
                }
                ladders.forEach(v -> {
                    if (v.getStartPoint() == nextCell) {
                        nextPosition[0] = v.getEndPoint();
                        ladderPresent[0] = true;
                    }
                });
                if (nextPosition[0] != nextCell && ladderPresent[0]) {
                    System.out.println(player.getPlayerName() + " climbed the ladder at " + nextCell + " and moved from " + nextCell + " to " + nextPosition[0]);
                }

                if (playersCurrentPosition.containsValue(nextPosition[0])) {
                    System.out.println("Collision detected at position " + nextPosition[0] + ". Moving player " + player.getPlayerName() + " back to starting position.");
                    nextPosition[0] = player.getPosition();
                }

                playersCurrentPosition.put(player.getPlayerName(), nextPosition[0]);
                System.out.println(player.getPlayerName() + " rolled a " + diceValue + " and moved from " + currentPosition + " to " + nextPosition[0]);

                if (nextPosition[0] >= boardSize) {
                    System.out.println(player.getPlayerName() + " won the game");
                    return;
                }
                nextTurn.offer(player);
            }
        }
    }
}

