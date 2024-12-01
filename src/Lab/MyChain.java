package Lab;

import dataStructures.Chain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyChain extends Chain {

    // Convert the chain to an array
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        for (int i = 0; i < size(); i++) {
            arr[i] = this.get(i);
        }
        return arr;
    }

    // Add an array of elements to the chain
    public void addRange(Object[] elements) {
        for (int i = 0; i < elements.length; i++) {
            this.add(this.size(), elements[i]);
        }
    }

    // Union of two chains
    public MyChain union(MyChain chain) {
        MyChain result = new MyChain();

        for (int i = 0; i < this.size(); i++) {
            if (result.indexOf(this.get(i)) == -1) {
                result.add(result.size(), this.get(i));
            }
        }

        for (int i = 0; i < chain.size(); i++) {
            if (result.indexOf(chain.get(i)) == -1) {
                result.add(result.size(), chain.get(i));
            }
        }

        return result;
    }


    // Intersection of two chains
    public MyChain intersection(MyChain chain) {
        MyChain result = new MyChain();

        // Iterate through elements of this chain
        for (int i = 0; i < this.size(); i++) {
            Object element = this.get(i);
            // Check if element is present in the second chain and not already in the result
            if (chain.indexOf(element) != -1 && result.indexOf(element) == -1) {
                result.add(result.size(), element);
            }
        }

        return result;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyChain mc = new MyChain();

        try {
            // Initial input for elements in the chain
            System.out.println("Enter the number of elements for the chain:");
            int numElements = sc.nextInt();
            if (numElements < 0) throw new IllegalArgumentException("Number of elements cannot be negative.");

            System.out.println("Enter the elements:");
            for (int i = 0; i < numElements; i++) {
                int element = sc.nextInt();
                mc.add(i, element);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid integers.");
            sc.next(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Menu for choosing operations
        System.out.println("Choose an operation: \n0. Exit \n1. toArray \n2. addRange \n3. Union \n4. Intersection \n5. Add Element \n6. Remove Element");
        while (true) {
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("Exiting the program.");
                        sc.close();
                        return;

                    case 1:
                        // Convert to array and print
                        Object[] array = mc.toArray();
                        for (Object o : array) {
                            System.out.print(o + " ");
                        }
                        System.out.println();
                        break;

                    case 2:
                        // Add range of elements
                        System.out.println("Enter the number of elements to add:");
                        int n = sc.nextInt();
                        if (n < 0) throw new IllegalArgumentException("Number of elements cannot be negative.");

                        Object[] elements = new Object[n];
                        System.out.println("Enter the elements:");
                        for (int i = 0; i < n; i++) {
                            elements[i] = sc.nextInt();
                        }
                        mc.addRange(elements);
                        System.out.println("Elements added.");
                        break;

                    case 3:
                        // Union with another chain
                        MyChain mc2 = new MyChain();
                        System.out.println("Enter the number of elements for the second chain:");
                        int numElements2 = sc.nextInt();
                        System.out.println("Enter the elements for the second chain:");
                        for (int i = 0; i < numElements2; i++) {
                            int element = sc.nextInt();
                            mc2.add(i, element);
                        }
                        MyChain unionChain = mc.union(mc2);
                        System.out.println("Union result: " + unionChain);
                        break;

                    case 4:
                        // Intersection with another chain
                        MyChain mc3 = new MyChain();
                        System.out.println("Enter the number of elements for the second chain:");
                        int numElements3 = sc.nextInt();
                        System.out.println("Enter the elements for the second chain:");
                        for (int i = 0; i < numElements3; i++) {
                            int element = sc.nextInt();
                            mc3.add(i, element);
                        }
                        MyChain intersectionChain = mc.intersection(mc3);
                        System.out.println("Intersection result: " + intersectionChain);
                        break;

                    case 5:
                        // Add an individual element
                        System.out.println("Enter the index where the element should be added:");
                        int index = sc.nextInt();
                        if (index < 0 || index > mc.size()) throw new IndexOutOfBoundsException("Invalid index.");

                        System.out.println("Enter the element to add:");
                        int newElement = sc.nextInt();
                        mc.add(index, newElement);
                        System.out.println("Element added at index " + index);
                        break;

                    case 6:
                        // Remove an element by index
                        System.out.println("Enter the index of the element to remove:");
                        int removeIndex = sc.nextInt();
                        if (removeIndex < 0 || removeIndex >= mc.size()) throw new IndexOutOfBoundsException("Invalid index.");

                        Object removedElement = mc.remove(removeIndex);
                        System.out.println("Removed element: " + removedElement);
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input type. Please enter valid integers.");
                sc.next(); // Clear invalid input
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
