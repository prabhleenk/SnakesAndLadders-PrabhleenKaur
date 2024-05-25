package SnakeAndLadder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
class Player {
    private String playerName;
    private int position;

    Player(String playerName, int position) {
        this.playerName = playerName;
        this.position = position;
    }
}
