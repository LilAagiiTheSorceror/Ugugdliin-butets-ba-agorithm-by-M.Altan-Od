package Lab;

import dataStructures.HashTable;

import java.util.Scanner;

public class HashTableExtended extends HashTable {

    public HashTableExtended(int theDivisor) {
        super(theDivisor);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashTable table1 = new HashTableExtended(5);

    }
}
