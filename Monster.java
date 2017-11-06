
public class Monster {
    private String name;
    private String type;
    private String level;
    private String treasureClass;
    
    /**
     * Monster: constructor
     * @param String name: name of the monster
     * @param String type: type of the monster
     * @param String level: level of the monster
     * @param String treasureClass: treasure class of the monster
     */
    public Monster(String name, String type, String level, String treasureClass){
        this.name = name;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }
    
    /**
     * getName: a getter method that gets the name of the monster
     * @return String
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * getTreasure: a getter method that gets the treasure class of the monster
     * @return String
     */
    public String getTreasure() {
        return this.treasureClass;
    }
    
}
