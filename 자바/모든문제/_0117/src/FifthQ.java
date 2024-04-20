import java.util.HashMap;
import java.util.Map;

public class FifthQ {
    public String[] solution(String[] players, String[] callings) {
        int numOfPlayers = players.length;
        Map<String, Integer> ranking = new HashMap<>();

        for (int i = 0; i < numOfPlayers; i++) {
            ranking.put(players[i], i);
        }

        for (String player : callings) {
            int playRanking = ranking.get(player);

            String frontPlayer = players[playRanking - 1];

            ranking.replace(frontPlayer, playRanking);
            players[playRanking] = frontPlayer;

            ranking.replace(player, playRanking - 1);
            players[playRanking - 1] = player;
        }
        return players;
    }
}
