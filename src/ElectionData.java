import java.util.*;

public class ElectionData {

    private HashMap<String, Votes> votes;

    private I3VoteStrategy strategy;


    public ElectionData(I3VoteStrategy strategy){
        this.strategy = strategy;
        this.votes = new HashMap<>();
    }

    public void setStrategy(I3VoteStrategy strategy){
        this.strategy = strategy;
    }

    public Set<String> getCandidates(){

        return new HashSet<>(votes.keySet());
    }

    public void nominateCandidate(String person) throws AlreadyNominatedException{
        if (votes.containsKey(person)){
            throw new AlreadyNominatedException(person);
        }
        votes.put(person, new Votes(0, 0 ,0));
    }

    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException{
        if (first.equals(second) || first.equals(third)){
            throw new MoreThanOnceException(first);
        }

        if (second.equals(third)){
            throw new MoreThanOnceException(second);
        }

        for (String candidate : List.of(first, second, third)){
            if (!(votes.containsKey(candidate))){
                throw new CandidateNotNominatedException(candidate);
            }
        }
        votes.get(first).voteFirst();
        votes.get(second).voteSecond();
        votes.get(third).voteThird();
    }

    public Optional<String> calculateWinner(){
        return strategy.calculateWinner(deepCopy(votes));
    }

    private HashMap<String, Votes> deepCopy(HashMap<String, Votes> toCopy){
        HashMap<String, Votes> votesCopy = new HashMap<>();
        for (Map.Entry<String, Votes> entry: toCopy.entrySet()){
            votesCopy.put(entry.getKey(),
                    new Votes(entry.getValue().getFirstVotes(),
                    entry.getValue().getSecondVotes(),
                    entry.getValue().getThirdVotes()));
        }
        return votesCopy;
    }

}
