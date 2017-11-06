
public interface Affix {
    /**
     * affixStats: produces a string in the form of a randomly produced number 
     * within the range of modmin and modmax (inclusive) and the modcode
     * @return String
     */
    public String affixStats();

    /**
     * getName: a getter method that gets the suffix
     * @return String
     */
    public String getName();
}
