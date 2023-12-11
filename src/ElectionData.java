import java.util.Optional;
import java.util.Set;

public class ElectionData {

    public ElectionData(I3VoteStrategy strategy){

    }

    public void setStrategy(I3VoteStrategy strategy){

    }

    public Set<String> getCandidates(){
        return Set.of();
    }

    public void nominateCandidate(String person) throws AlreadyNominatedException{

    }

    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException{

    }

    public Optional<String> calculateWinner(){
        return Optional.empty();
    }


}
