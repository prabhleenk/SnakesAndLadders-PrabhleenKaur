package SnakeAndLadder;

import java.util.*;

public class PlaySnakeAndLadder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the total number of snakes:");
        int totalSnakes = scanner.nextInt();
        List<Jumper> snakes = new ArrayList<>();
        for (int i = 0; i < totalSnakes; i++) {
            System.out.println("Enter snake's head and tail:");
            int head = scanner.nextInt();
            int tail = scanner.nextInt();
            snakes.add(new Jumper(head, tail));
        }

        System.out.println("Enter the total number of ladders:");
        int totalLadders = scanner.nextInt();
        List<Jumper> ladders = new ArrayList<>();
        for (int i = 0; i < totalLadders; i++) {
            System.out.println("Enter ladder's bottom and top:");
            int bottom = scanner.nextInt();
            int top = scanner.nextInt();
            ladders.add(new Jumper(bottom, top));
        }

        System.out.println("Enter the total number of players:");
        int totalPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < totalPlayers; i++) {
            System.out.println("Enter player's name and starting position:");
            String name = scanner.next();
            int position = scanner.nextInt();
            players.add(new Player(name, position));
        }

        Dice dice = new Dice(1);
        Queue<Player> allPlayers = new LinkedList<>(players);
        Map<String, Integer> playersCurrentPosition = new HashMap<>();
        for (Player player : players) {
            playersCurrentPosition.put(player.getPlayerName(), player.getPosition());
        }

        GameBoard gb = new GameBoard(dice, allPlayers, snakes, ladders, playersCurrentPosition, 100);

        gb.startGame();
    }
}
