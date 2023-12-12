/**
 * An exception that is thrown when a candidate is redundantly nominated
 */
public class AlreadyNominatedException extends Exception {

    /**
     * Constructs an AlreadyNominated exception using a candidate's name
     * @param candidateName
     */
    public AlreadyNominatedException(String candidateName) {
        super(candidateName + " was already nominated");
    }
}