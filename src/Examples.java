import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Examples {
    ElectionData eData;

    public Examples(){
        eData = new ElectionData(new MostFirstVotesStrategy());
    }

    @Test
    public void testOneVoteFirstVotesStrategy() {
        try {
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.of("gompei"), this.eData.calculateWinner());
    }

    @Test
    public void testOneVoteMostAgreeableStrategy() {
        ElectionData eData = new ElectionData(new MostAgreeableStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");
            eData.submitVote("gompei", "husky", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.empty(), eData.calculateWinner());
    }

    @Test
    public void testMultipleVoteFirstVotesStrategy() {
        try {
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("husky", "gompei", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.of("gompei"), this.eData.calculateWinner());
    }

    @Test
    public void testMultipleVoteMostAgreeableStrategy() {
        ElectionData eData = new ElectionData(new MostAgreeableStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");
            eData.submitVote("gompei", "husky", "bristaco");
            eData.submitVote("bristaco", "husky", "gompei");
            eData.submitVote("husky", "gompei", "bristaco");
            eData.submitVote("husky", "gompei", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.of("bristaco"), eData.calculateWinner());
    }

    @Test
    public void testTiedVoteFirstVotesStrategy() {
        try {
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("husky", "gompei", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.empty(), this.eData.calculateWinner());
    }


    @Test
    public void testSetStrategy() {
        ElectionData eData = new ElectionData(new MostAgreeableStrategy());
        eData.setStrategy(new MostFirstVotesStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");
            eData.submitVote("gompei", "husky", "bristaco");
            eData.submitVote("bristaco", "husky", "gompei");
            eData.submitVote("husky", "gompei", "bristaco");
            eData.submitVote("husky", "gompei", "bristaco");
            eData.submitVote("husky", "gompei", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.of("husky"), eData.calculateWinner());
    }

    @Test
    public void testGetCandidates() {
        ElectionData eData = new ElectionData(new MostAgreeableStrategy());
        eData.setStrategy(new MostFirstVotesStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");
            eData.submitVote("gompei", "husky", "bristaco");
            eData.submitVote("bristaco", "husky", "gompei");
            eData.submitVote("husky", "gompei", "bristaco");
            eData.submitVote("husky", "gompei", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(new HashSet<>(List.of("gompei", "husky", "bristaco")), eData.getCandidates());
    }

    @Test(expected=AlreadyNominatedException.class)
    public void testCandidateAlreadyNominated() throws AlreadyNominatedException{
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("gompei");

        fail("did not throw exception properly");
    }

    @Test(expected=CandidateNotNominatedException.class)
    public void testCandidateNotNominated() throws CandidateNotNominatedException, AlreadyNominatedException, MoreThanOnceException {
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("husky");
        this.eData.nominateCandidate("bristaco");
        this.eData.submitVote("bob", "husky", "bristaco");


        fail("did not throw exception properly");
    }

    @Test(expected=CandidateNotNominatedException.class)
    public void testBlankCandidate() throws CandidateNotNominatedException, AlreadyNominatedException, MoreThanOnceException {
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("husky");
        this.eData.nominateCandidate("bristaco");
        this.eData.submitVote("", "", "");


        fail("did not throw exception properly");
    }

    @Test
    public void noCandidateNominatedNoVote() {

       assertEquals(Optional.empty(), this.eData.calculateWinner());
    }

    @Test(expected=MoreThanOnceException.class)
    public void testMoreThanOneSubmission() throws CandidateNotNominatedException, AlreadyNominatedException, MoreThanOnceException {
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("husky");
        this.eData.nominateCandidate("bristaco");
        this.eData.submitVote("gompei", "gompei", "gompei");


        fail("did not throw exception properly");
    }

    @Test
    public void testMutatedGetCandidates() {
        ElectionData eData = new ElectionData(new MostFirstVotesStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");
            Set<String> a = eData.getCandidates();
            a.clear();
            eData.submitVote("gompei", "husky", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.of("gompei"), eData.calculateWinner());
    }

}
