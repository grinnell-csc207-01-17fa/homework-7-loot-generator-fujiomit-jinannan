import java.util.Random;
public class Prefix implements Affix{
    private String name;
    private String modcode;
    private int modmin;
    private int modmax;
    
    /**
     * Prefix: constructor
     * @param String name: the prefix
     * @param String modcode: the modcode
     * @param int modmin: the minimum value of the mode status
     * @param int modmax: the maximum value of the mode status
     */
    public Prefix(String name, String modcode, int modmin, int modmax) {
        this.name = name;
        this.modcode = modcode;
        this.modmin = modmin;
        this.modmax = modmax;
    }

    /**
     * getName: a getter method that gets the prefix
     * @return String
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * affixStats: produces a string in the form of a randomly produced number 
     * within the range of modmin and modmax (inclusive) and the modcode
     * @return String
     */
    public String affixStats() {
        Random rand = new Random();
        int stats = rand.nextInt(modmax - modmin + 1) + modmin;
        return stats + " " + modcode + "\n";
    }
}
