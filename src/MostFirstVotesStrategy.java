import java.util.HashMap;
import java.util.Optional;

public class MostFirstVotesStrategy implements I3VoteStrategy{
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        return Optional.empty();
    }
}
