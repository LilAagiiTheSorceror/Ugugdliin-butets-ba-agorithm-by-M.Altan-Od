package Lab;
import dataStructures.ArrayLinearList;

import java.util.NoSuchElementException;
import java.util.Scanner;

// Lab 1 B222270023 М.Алтан-Од

public class MyArrayLinearList extends ArrayLinearList {

    public MyArrayLinearList(int initCapacity) {
        super(initCapacity);
    }

    public MyArrayLinearList() {
        super(10);
    }

    public MyArrayLinearList(MyArrayLinearList mylist) {
        super(mylist.size());
        for(int i=0;i<mylist.size();i++) {
            this.add(i, mylist.element[i]);
        }
    }

    public void add(Object element) {
        this.add(this.size(), element);
    }

    public void set(int index, Object element) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        this.element[index] = element;
    }

    public boolean contains(Object obj) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public MyArrayLinearList reverse() {
        MyArrayLinearList reversedList = new MyArrayLinearList(this.size());
        for (int i = this.size() - 1; i >= 0; i--) {
            reversedList.add(this.get(i));
        }
        return reversedList;
    }

    public int max() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        int maxVal = (int) this.get(0);
        for (int i = 1; i < this.size(); i++) {
            if ((int) this.get(i) > maxVal) {
                maxVal = (int) this.get(i);
            }
        }
        return maxVal;
    }

    public int min() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        int minVal = (int) this.get(0);
        for (int i = 1; i < this.size(); i++) {
            if ((int) this.get(i) < minVal) {
                minVal = (int) this.get(i);
            }
        }
        return minVal;
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < this.size(); i++) {
            sum += (int) this.get(i);
        }
        return sum;
    }

    public double average() {
        if (this.isEmpty()) {
            throw new ArithmeticException("List is empty.");
        }
        return (double) this.sum() / this.size();
    }

    public MyArrayLinearList removeOdd() {
        MyArrayLinearList tempList = new MyArrayLinearList();
        for (int i = 0; i < this.size(); i++) {
            if ((int) this.get(i) % 2 == 0) {
                tempList.add(this.get(i));
            }
        }
        return tempList;
    }

    public MyArrayLinearList sort() {
        MyArrayLinearList sortedList = new MyArrayLinearList(this);
        int size = sortedList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if ((int) sortedList.get(j) > (int) sortedList.get(j + 1)) {
                    int temp = (int) sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    public MyArrayLinearList unique() {
        MyArrayLinearList uniqueList = new MyArrayLinearList();
        for (int i = 0; i < this.size(); i++) {
            if (!uniqueList.contains(this.get(i))) {
                uniqueList.add(this.get(i));
            }
        }
        return uniqueList;
    }

    public MyArrayLinearList merge(MyArrayLinearList arrayList) {
        MyArrayLinearList mergedList = new MyArrayLinearList(this);
        for (int i = 0; i < arrayList.size(); i++) {
            mergedList.add(arrayList.get(i));
        }
        return mergedList;
    }

    public MyArrayLinearList rand() {
        MyArrayLinearList randomList = new MyArrayLinearList(this);
        java.util.Collections.shuffle(java.util.Arrays.asList(randomList.element));
        return randomList;
    }

    public static void main(String[] args) {
        MyArrayLinearList l1 = new MyArrayLinearList();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements:");
        int a = sc.nextInt();
        System.out.println("Enter the elements:");
        for (int i = 0; i < a; i++) {
            int b = sc.nextInt();
            l1.add(i, b);
        }

        System.out.println("Choose an operation: \n1. max \n2. min \n3. sum \n4. average \n5. removeOdd \n6. sort \n7. reverse \n8. unique \n9. rand \n10. merge");

        while (true) {
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Exiting the program.");
                    sc.close();
                    return;
                case 1:
                    System.out.println("max: " + l1.max());
                    break;
                case 2:
                    System.out.println("min: " + l1.min());
                    break;
                case 3:
                    System.out.println("sum: " + l1.sum());
                    break;
                case 4:
                    System.out.println("average: " + l1.average());
                    break;
                case 5:
                    MyArrayLinearList evenList = l1.removeOdd();
                    System.out.println("List after removing odd elements: " + evenList);
                    break;
                case 6:
                    MyArrayLinearList sortedList = l1.sort();
                    System.out.println("Sorted List: " + sortedList);
                    break;
                case 7:
                    System.out.println("reverse: " + l1.reverse());
                    break;
                case 8:
                    System.out.println("unique: " + l1.unique());
                    break;
                case 9:
                    System.out.println("rand: " + l1.rand());
                    break;
                case 10:
                    MyArrayLinearList l2 = new MyArrayLinearList();
                    System.out.println("Enter the amount of elements for second list to merge:");
                    int c = sc.nextInt();
                    System.out.println("Enter the elements for second list to merge:");
                    for (int i = 0; i < c; i++) {
                        int d = sc.nextInt();
                        l2.add(i, d);
                    }
                    MyArrayLinearList mergedList = l1.merge(l2);
                    System.out.println("Merged List: " + mergedList);
                    break;
                case 11:

                default:
                    System.out.println("Wrong input! Try again.");
            }
        }
    }
}
