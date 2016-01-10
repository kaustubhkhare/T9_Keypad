package t9.keypad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Trie {
    int nodeCount;
    Node root = null;
    Map<Character, Integer> map = new HashMap<Character, Integer>();

    public Trie() {

        this.nodeCount = 0;
        this.root = new Node(0);

        this.map.put('a', 2);
        this.map.put('b', 2);
        this.map.put('c', 2);
        this.map.put('d', 3);
        this.map.put('e', 3);
        this.map.put('f', 3);
        this.map.put('g', 4);
        this.map.put('h', 4);
        this.map.put('i', 4);
        this.map.put('j', 5);
        this.map.put('k', 5);
        this.map.put('l', 5);
        this.map.put('m', 6);
        this.map.put('n', 6);
        this.map.put('o', 6);
        this.map.put('p', 7);
        this.map.put('q', 7);
        this.map.put('r', 7);
        this.map.put('s', 7);
        this.map.put('t', 8);
        this.map.put('u', 8);
        this.map.put('v', 8);
        this.map.put('w', 9);
        this.map.put('x', 9);
        this.map.put('y', 9);
        this.map.put('z', 9);

    }

    class Node {

        int nodeDigit;
        ArrayList<String> words = new ArrayList<String>();
        Node[] digits = new Node[10];

        public Node() {
            this.nodeDigit = -1;
        }

        public Node(int nodeDig) {
            this.nodeDigit = nodeDig;
        }

    }

    int[] getKeyPattern(String word) {
        int[] pattern = new int[word.length()];
        int i = 0;
        //System.out.println(word.length());
        while(i < word.length()) {
            pattern[i] = this.map.get(Character.toLowerCase(word.charAt(i)));
            i++;
        }
        /*for(int j = 0; j < word.length(); j++) {
            System.out.print(pattern[j]);
        }*/
        //System.out.println();
        return pattern;
    }

    void insert(String word) {
        int[] keyPattern = getKeyPattern(word);
        //keyPattern = getKeyPattern(word);
        this.nodeCount++;
        int level = 0;
        Node pNode = this.root;
        for(level = 0; level < word.length(); level++) {
            //System.out.println(level);
            if(pNode.digits[keyPattern[level]] == null) {
                pNode.digits[keyPattern[level]] = new Node(keyPattern[level]);
            }
            pNode = pNode.digits[keyPattern[level]];
        }
        pNode.words.add(word);
    }

    ArrayList<String> getWords(int[] ipPattern) {
        Node pNode = this.root;
        ArrayList<String> retArray = new ArrayList<String>();
        int level = 0;
        for (level = 0; level < ipPattern.length; level++) {
            if(pNode.digits[ipPattern[level]] == null) {
                retArray.clear();
                return retArray;
            }
            pNode = pNode.digits[ipPattern[level]];
        }
        if(pNode.words.size() != 0) {
            retArray = new ArrayList<String>(pNode.words);
        }
        return retArray;
    }

    void buildTrie(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                this.insert(line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        String fileNam = "dict.txt";
        Trie t = new Trie();
        t.buildTrie(fileNam);
        int[] pattern = {3, 9, 2, 6};
        int[] pattern2 = {3,8,4,2,6,3,3,4,8,4,4,6,5};
        ArrayList<String> printArray = t.getWords(pattern);
        for(int i = 0; i < printArray.size(); i++) {
            System.out.println(printArray.get(i));
        }
        int j = 0;
        while (j < 100) {
            ArrayList<String> printArray2 = t.getWords(pattern2);
            for (int i = 0; i < printArray2.size(); i++) {
                System.out.println(printArray2.get(i));
            }
            j++;
        }
    }*/
}