/**
 * An exception that is thrown when a candidate is voted for more than once by the same person (in the same vote
 */
public class MoreThanOnceException extends Exception{

    /**
     * Constructs a MoreThanOnceExcpetion using a candidate's name
     * @param candidateName
     */
    public MoreThanOnceException(String candidateName){
        super(candidateName + " was voted for more than once in a single vote");
    }
}
