package kchandra423.actors.players.enemies;

import kchandra423.actors.players.Player;
import kchandra423.levels.Room;

public class EnemyAI {
    private Enemy e;
    public EnemyAI(Enemy enemy){
        e = enemy;
    }
    public boolean[] makeDecision(Room r) {
//        Random rand = new Random();
//        return new boolean[]{rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean()};
        return getDirectionTowardsPlayer(r);
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
