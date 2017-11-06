import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {

    /**
     * pickMonster: a method that randomly picks a monster from a given ArrayList of monsters
     * @param ArrayList<Mosnter> monsters: an ArrayList of monsters
     * @return Monster
     */
    public static Monster pickMonster(ArrayList<Monster> monsters) {
        Random rand = new Random();
        return monsters.get(rand.nextInt(monsters.size()));
    }

    /**
     * fetchTreasureClass: a method that produces a treasureClass from the monster
     * @param Monster monster: the monster the user is fighting against
     * @param HashMap<String, Treasure> treasures: a hashMap of Treasures
     * @return Treasure
     */
    public static Treasure fetchTreasureClass(Monster monster, HashMap<String, Treasure> treasures) {
        return treasures.get(monster.getTreasure());
    }

    /**
     * generateBaseItem: a method that generates the base item (the item the monster drops)
     * @param HashMap<String, Treasure> treasures: a hashMap of strings and treasures
     * @param String treasureClass: the name of the treasureClass
     * @return String
     */
    public static String generateBaseItem(HashMap<String, Treasure> treasures, String treasureClass) {
        if(!(treasures.containsKey(treasureClass))){
            return treasureClass;
        } else {
            return generateBaseItem(treasures, treasures.get(treasureClass).getNextTreasure());
        }
    }

     /**
     * generateBaseStats: a method that produces a string in the form of "Defense" and the randomly gotten value of baseItem 
     * @param HashMap<String, BaseStats> baseStats: a hashMap of String and BaseStats
     * @param String baseItem: the name of the baseItem
     * @return String
     */
    public static String generateBaseStats(HashMap<String, BaseStats> baseStats, String baseItem) {
        return "Defense: " + baseStats.get(baseItem).generateDefense();
    }

    /**
     * generateAffix: a method that gets a random affix from a given ArrayList of Affixes
     * @param ArralyList<Affix> affixes: an ArrayList of affixes
     * @return Affix
     */
    public static Affix generateAffix(ArrayList<Affix> affixes){ 
        Random rand = new Random();
        return affixes.get(rand.nextInt(affixes.size()));
    }

    /**
     * generateFullItemName: a method that generates a string 
     * that contains all the necessary information about the dropped item
     * @param HasMap<String, BaseStats> baseStats: a hashMap of BaseStats
     * @param String baseItem: the name of the baseItem
     * @param ArrayList<Affix> prefixes: an ArrayList of prefixes
     * @param ArrayList<Affix> suffixes: an ArrayList of suffixes
     * @return String
     */
    public static String generateFullItemName(HashMap<String, BaseStats> baseStats,String baseItem, ArrayList<Affix> prefixes, ArrayList<Affix> suffixes){ 
        Random rand = new Random();

        boolean hasPre = rand.nextInt(2) <1? true : false;
        boolean hasSuf = rand.nextInt(2) <1? true : false;

        Affix pre, suf;
        String preName = "";
        String preStats = "";
        String sufName = "";
        String sufStats = "";
        if(hasPre) {
            pre = generateAffix(prefixes);
            preName += pre.getName() + " ";
            preStats = pre.affixStats();
        }

        if(hasSuf) {
            suf = generateAffix(suffixes);
            sufName += " "+ suf.getName();
            sufStats = suf.affixStats();
        }

        String full = preName + baseItem + sufName +"\n";
        full += generateBaseStats(baseStats, baseItem)+"\n";
        full += preStats + sufStats;
        return full;
    }

    /**
     * fightAgain: a method that produces boolean value based on the user input 
     * @param Scanner in: a scanner
     * @return boolean
     */
    public static boolean fightAgain(Scanner in) {
        String input = in.nextLine().toLowerCase();
        while(!(input.equals("n") || (input.equals("y")))) {
            System.out.print("Fight again [y/n]?");
            input = in.nextLine().toLowerCase();
        }
        if(input.equals("y")) {
            System.out.println("----------Fighting----------");
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args)  throws IOException{
        
        //Generate the HashMap of the monsters
        File file = new File("monstats.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        ArrayList<Monster> monsterList = new ArrayList<>();
        while(line != null){
            String[] monsterInfo = line.split("	");
            Monster monster = new Monster(monsterInfo[0], monsterInfo[1], monsterInfo[2], monsterInfo[3]);
            monsterList.add(monster);
            line = br.readLine();
        }
        br.close();

        //Generate the HashMap of the treasure
        HashMap<String, Treasure> treasures = new HashMap<>();
        file = new File("TreasureClassEx.txt");
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        line = br.readLine();
        while(line != null){
            String[] treasureInfo = line.split("	");
            Treasure treasure = new Treasure(treasureInfo[1], treasureInfo[2], treasureInfo[3]);
            treasures.put(treasureInfo[0], treasure);
            line = br.readLine();
        }
        br.close();

        //Generate the HashMap of the baseStats
        HashMap<String, BaseStats> baseStats = new HashMap<>();
        file = new File("armor.txt");
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        line = br.readLine();
        while(line != null){
            String[] baseStatsInfo = line.split("	");
            BaseStats baseStat = new BaseStats(Integer.parseInt(baseStatsInfo[1]), Integer.parseInt(baseStatsInfo[2]));
            baseStats.put(baseStatsInfo[0], baseStat);
            line = br.readLine();
        }
        br.close();

        //Generate the ArrayList for the prefixes
        file = new File("MagicPrefix.txt");
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        line = br.readLine();
        ArrayList<Affix> prefixList = new ArrayList<>();
        while(line != null){
            String[] prefixInfo = line.split("	");
            Prefix prefix = new Prefix(prefixInfo[0], prefixInfo[1], Integer.parseInt(prefixInfo[2]), Integer.parseInt(prefixInfo[3]));
            prefixList.add(prefix);
            line = br.readLine();
        }
        br.close();
        
        //Generate the ArrayList for the suffixes
        file = new File("MagicSuffix.txt");
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        line = br.readLine();
        ArrayList<Affix> suffixList = new ArrayList<>();
        while(line != null){
            String[] suffixInfo = line.split("	");
            Suffix suffix = new Suffix(suffixInfo[0], suffixInfo[1], Integer.parseInt(suffixInfo[2]), Integer.parseInt(suffixInfo[3]));
            suffixList.add(suffix);
            line = br.readLine();
        }
        br.close();

        //Repetitively fight the Monster
        boolean fight = true;
        Scanner in = new Scanner(System.in);
        while(fight) {
            Monster randMonster = pickMonster(monsterList);
            String monster = randMonster.getName();

            Treasure treasure = fetchTreasureClass(randMonster, treasures);
            String baseItem = generateBaseItem(treasures, treasure.getNextTreasure());
            System.out.println("Fighting " + monster);
            System.out.println("You have slain " + monster);
            System.out.println(monster + " dropped:" +"\n");

            System.out.print(generateFullItemName(baseStats, baseItem, prefixList, suffixList));
            System.out.print("Fight again [y/n]?");
            fight = fightAgain(in);
        }
    }
}
