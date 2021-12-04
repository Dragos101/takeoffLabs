package ro.info.uaic;

import java.util.*;

public class Main {
    public static Scanner in = new Scanner(System.in);

    /**
     * First problem
     * 1. Given a list of n natural numbers ranging between 1 and 1,000,000,000,
     * find the most popular k scores in the descending order of their frequency.
     * Input: n = 11, k = 3, numbers = [6, 5, 2, 6, 6, 2, 1, 7, 3, 3, 3] Output: [6, 3, 2]
     */
    public static void firstProblem(){
        int n, number, k, index;
        Map<Integer, Integer> frequence = new HashMap<>();


        /**
         * get the n and k values as input
         */
        System.out.print("n = ");
        n = in.nextInt();
        System.out.print("k = ");
        k = in.nextInt();

        /**
         * Read n numbers and add them in frequence
         * if the key is already in the map add 1 + the value of the key
         * otherwise add the key and make the value for it 1
         */
        for(int i = 0; i < n; ++i){
            number = in.nextInt();
            frequence.put(number, frequence.containsKey(number) ? frequence.get(number) + 1 : 1);
        }

        /**
         * Convert the map into an Array then sort it by value. Then select the last k keys from the list and print them
         */
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(frequence.entrySet());
        list.sort(Map.Entry.comparingByValue());
        index = 1;
        while(k != 0){
            System.out.println(list.get(list.size() - index).getKey());
            k--;
            index++;
        }

    }

    /**
     * Second Problem
     * In a company’s hierarchy, some employees manage other employees, who can in turn manage other employees.
     * The input (as a string) format for the hierarchy is (name, sub-hierarchy_1, sub-hierarchy_2, … , sub-hierarchy_n), with every sub-hierarchy having the same format, recursively.
     * Your task is to write code that parses the hierarchy, removes employees that are marked as “Unavailable” along with all the employees they manage and prints the resulting hierarchy.
     * Input: (“John”, (“Jasmine”, (“Jay”), (“Unavailable”)), (“Unavailable”, (“Jack”, (“Jeremy”))), (“Johanna”))
     * Output: (“John”, (“Jasmine”, (“Jay”)), (“Johanna”))
     * Note: The input be read as a string
     */
    public static void secondProblem(){
        String hierarchy = in.nextLine();
        /**
         * Split the string in pieces
         */
        String[] text = hierarchy.split(" ");
        int parCounter = 0;
        for(int i = 0; i < text.length; ++i) {
            /**
             * Check if the word is unavailable if so I must be in one of these cases:
             * 1. name -> so I have to delete the sub_hierarchy that contains that Unavailable
             * 2. sub_hierarchy containing only the name
             * 3. sub_hierarchy containing the name + other members
             */
            if (text[i].contains("Unavailable"))
                /**
                 * 3
                 */
                if (text[i].contains(")),")) {
                    text[i] = "),";
                    text[i - 1] = text[i - 1].replace(",", "");
                } else if (text[i].contains("),")) text[i] = ""; /** 2 */
                /**
                 * 1
                 * Knowing that Unavailable it' the name we have to delete every member of the sub_hierarchy
                 * So in 'parCounter' we add 1 for every '(' found and we decrease 1 for every ')' found
                 * Until parCounter it's not 0 I delete every string
                 */
                else if (text[i].contains(",") && !text[i].contains(")")) {
                    parCounter = Math.toIntExact(text[i].chars().filter(ch -> ch == '(').count()) + Math.toIntExact(text[i].chars().filter(ch -> ch == ')').count());
                    text[i] = "";
                    while (parCounter != 0) {
                        i++;
                        parCounter += Math.toIntExact(text[i].chars().filter(ch -> ch == '(').count()) - Math.toIntExact(text[i].chars().filter(ch -> ch == ')').count());
                        text[i] = "";
                    }
                }
        }
        for(String x : text)
            System.out.print(x);
    }

    public static void main(String[] args) {
        //firstProblem();
        //secondProblem();
    }
}
