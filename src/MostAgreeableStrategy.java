import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * A strategy which calculates the winner by who has the most votes in a single category
 */
public class MostAgreeableStrategy implements I3VoteStrategy{

    /**
     * Calculates the winner by who has the most votes in a single category
     * @param votes a hashmap of candidates and votes
     * @return the winner with the most votes in a single category, or empty
     */
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {

        int mostVotes = 0;
        Optional<String> candidateWithMostVotes = Optional.empty();

        for (String key : votes.keySet()){
            Votes v = votes.get(key);
            for (Integer numVotes : List.of(v.getFirstVotes(), v.getSecondVotes(), v.getThirdVotes())){
                if (numVotes > mostVotes){
                    mostVotes = numVotes;
                    candidateWithMostVotes = Optional.of(key);
                }
            }
        }
        return candidateWithMostVotes;
    }
}
