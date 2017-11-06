import java.util.Random;

public class Treasure {
    private String item1;
    private String item2;
    private String item3;

    /**
     * Treasure: constructor
     * @param String item1: the name of the first item 
     * @param String item2: the name of the second item
     * @param String item3: the name of the third item
     */
    public Treasure(String item1, String item2, String item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    /**
     * getNextTreasure: a method that randomly chooses one of the three items 
     * @return
     */
    public String getNextTreasure() {
        Random rand = new Random();
        int i = rand.nextInt(3);
        if(i == 0) {
            return item1;
        } else if(i == 1) {
            return item2;
        } else {
            return  item3;
        }
    }

}