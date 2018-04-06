
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ulusoy
 */
public class Dictionary {

    private int size = 1000000;
    private String[] dict;

    public Dictionary() {
        this.dict = new String[size];
    }

    public void save(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (int i = 0; i < dict.length; i++) {
            fw.write(dict[i] + "\n");
        }
        fw.close();
        System.out.println("Your file is saved");
    }

    public boolean search(String word) {
        int ndx = hashFunction(word);
        while (dict[ndx] != null) {
            if (dict[ndx].equals(word)) {
                return true;
            }
            ndx = (ndx + 1) % size;
        }
        return false;
    }

    public void insert(String word) {
        int ndx = hashFunction(word);
        while (dict[ndx] != null) {
            if (dict[ndx].equals(word)) {
                return;
            }
            ndx = (ndx + 1) % size;
        }
        dict[ndx] = word;
    }

    public boolean delete(String word) {
        int ndx = hashFunction(word);
        while (dict[ndx] != null) {
            if (dict[ndx].equals(word)) {
                dict[ndx] = null;
                return true;
            }
            ndx = (ndx + 1) % size;
        }
        return false;
    }

    private int hashFunction(String s) {
        int hash = 1;
        for (int i = 0; i < s.length(); i++) {
            hash = 26 * hash + s.charAt(i);
        }
        return ((hash % size) + size) % size;
    }

    public int getSize() {
        return size;
    }

    public String[] getDict() {
        return dict;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
