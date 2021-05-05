package kchandra423.actors.players.enemies;

import kchandra423.actors.players.Player;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;

public class EnemyAI {
    private Enemy e;

    public EnemyAI(Enemy enemy) {
        e = enemy;
    }

    public float makeMovementDecision(Room r) {
//        Random rand = new Random();
//        return new boolean[]{rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean()};
        return (float) Calculator.calculateAngle(e.getImage().getX(), e.getImage().getY(), r.getPlayer().getImage().getX(), r.getPlayer().getImage().getY());
    }

    private boolean[] getDirectionTowardsPlayer(Room room) {
        Player p = room.getPlayer();
        boolean[] answer = new boolean[4];
        if (e.getImage().getX() < p.getImage().getX()) {
            answer[0] = false;
            answer[1] = true;
        } else {
            answer[1] = false;
            answer[0] = true;
        }
        if (e.getImage().getY() < p.getImage().getY()) {
            answer[2] = false;
            answer[3] = true;
        } else {
            answer[3] = false;
            answer[2] = true;
        }
        return answer;

    }
}
