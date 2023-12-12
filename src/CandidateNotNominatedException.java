/**
 * An exception that is thrown when a candidate is voted for but has not been nominated
 */
public class CandidateNotNominatedException extends Exception{

    /**
     * String to hold the candidate's name
     */
    private String candidateName;

    /**
     *  Constructs a new CandidateNotNominatedException using a candidate's name
     * @param candidateName
     */
    public CandidateNotNominatedException(String candidateName){
        super (candidateName + " was not nominated");
        this.candidateName = candidateName;
    }

    /**
     * Gets the candidate's name
     * @return the candidate's name
     */
    public String getCandidate(){
        return candidateName;
    }
}
