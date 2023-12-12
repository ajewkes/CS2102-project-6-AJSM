import java.util.HashMap;
import java.util.Optional;

/**
 * A strategy that calculates the winner based on first votes
 */
public class MostFirstVotesStrategy implements I3VoteStrategy{

    /**
     * Calculates the winner by who has the most first votes, and the majority votes
     * @param votes
     * @return the name of the winner in String form or empty  if there is no winner
     */
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {

        int totalVotes = 0;
        for(Votes v : votes.values()){
            totalVotes += v.getFirstVotes();
        }

        for(String key : votes.keySet()){
            if (votes.get(key).getFirstVotes() > totalVotes / 2){
                return Optional.of(key);
            }
        }
        return Optional.empty();
    }
}
