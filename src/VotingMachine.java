import java.sql.SQLOutput;
import java.util.Optional;
import java.util.Scanner;
public class VotingMachine {

    private static ElectionData eData = new ElectionData(new MostFirstVotesStrategy());

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Boolean doLoop = true;

        while (doLoop){
            System.out.println("Current Candidates are: " + eData.getCandidates());
            System.out.println("Do you want to [n]ominate someone, [v]ote for someone, change winner [s]trategy, see who [w]on, or [q]uit?");
            String input = sc.nextLine();

            switch (input){
                case "n":
                    System.out.println("Who do you want to nominate? ");
                    String candidate = sc.nextLine();

                    try {
                        eData.nominateCandidate(candidate);
                    } catch (AlreadyNominatedException e){
                        System.out.println(e);
                    }
                    break;
                case "v":
                    System.out.println("Who is your first choice? ");
                    String candidate1 = sc.nextLine();
                    System.out.println("Who is your second choice? ");
                    String candidate2 = sc.nextLine();
                    System.out.println("Who is your third choice? ");
                    String candidate3 = sc.nextLine();

                    try{
                        eData.submitVote(candidate1, candidate2, candidate3);
                    } catch (CandidateNotNominatedException e){
                        System.out.println(e);
                        System.out.println("would like to nominate the candidate [y]es/[n]o");
                        String yOrN = sc.nextLine();
                        if (yOrN.equals("y")){
                            try {
                                eData.nominateCandidate(e.getCandidate());
                            } catch (AlreadyNominatedException e2){
                                System.out.println(e2);
                            }
                        }
                    } catch (MoreThanOnceException e){
                        System.out.println(e);
                    }
                    break;
                case "s":
                    System.out.println("Which strategy would you like to use? most [f]irst votes or most [a]greeable?");
                    String newStrat = sc.nextLine();
                    if (newStrat.startsWith("f")){
                        eData.setStrategy(new MostFirstVotesStrategy());
                    } else if (newStrat.startsWith("a")){
                        eData.setStrategy(new MostAgreeableStrategy());
                    } else System.out.println("Invalid Input");
                    break;
                case "w":
                    Optional<String> winner = eData.calculateWinner();
                    if (winner.isPresent()){
                        System.out.println(winner.get() + " has won the election!");
                } else System.out.println("No clear winner under the current strategy.");
                    break;
                case "q":
                    doLoop = false;
                    break;
            }
        }
        sc.close();
    }
}
