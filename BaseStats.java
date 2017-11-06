import java.util.Random;

public class BaseStats {
    private int minac;
    private int maxac;
    
    /**
     * BaseStats: constructor
     * @param int minac: the minimum defensing skills of the item
     * @param int maxac: the maximum defensing skills of the item
     */
    public BaseStats(int minac, int maxac) {
        this.minac = minac;
        this.maxac = maxac;
    }
    
    /**
     * generateDefense: a method that generates the value of defensing skills that is a random number between minac and maxac
     * @return int
     */
    public int generateDefense() {
        Random rand = new Random();
        return rand.nextInt(maxac - minac) + minac;
    }
}
