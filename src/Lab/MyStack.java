package Lab;

import dataStructures.ArrayStack;
import java.util.*;

public class MyStack extends ArrayStack {

    private int size = 0; // Field to track stack size

    // Size method to return the current size of the stack
    public int size() {
        return size;
    }

    // Overriding push to increase size
    @Override
    public void push(Object theElement) {
        super.push(theElement);
        size++;
    }

    // Overriding pop to decrease size
    @Override
    public Object pop() {
        if (empty()) throw new EmptyStackException();
        size--;
        return super.pop();
    }

    // 1. rand() - Randomize the order of elements in the stack
    public MyStack rand() {
        List<Object> elements = new ArrayList<>();
        while (!empty()) {
            elements.add(pop());
        }
        Collections.shuffle(elements);
        for (Object element : elements) {
            push(element);
        }
        return this;
    }

    // 2. unique() - Remove duplicate elements, keeping the first instance
    public MyStack unique() {
        Set<Object> seen = new HashSet<>();       // To track elements we've already added
        List<Object> tempList = new ArrayList<>(); // To maintain stack order for unique elements

        // Pop all elements from stack
        while (!empty()) {
            Object element = pop();
            // Only add first occurrence to tempList
            if (seen.add(element)) {
                tempList.add(element);
            }
        }

        // Reverse list to maintain original order in the stack
        Collections.reverse(tempList);
        for (Object element : tempList) {
            push(element);
        }

        return this;
    }

    // 3. addRange(Object[] elements) - Add all elements from an array to the stack
    public MyStack addRange(Object[] elements) {
        for (Object element : elements) {
            push(element);
        }
        return this;
    }

    // 4. toArray() - Convert stack elements to an array
    public Object[] toArray() {
        Object[] array = new Object[size()];
        ArrayStack tempStack = new ArrayStack();

        int index = 0;
        while (!empty()) {
            Object item = pop();
            array[index++] = item;
            tempStack.push(item);
        }

        while (!tempStack.empty()) {
            push(tempStack.pop());
        }

        return array;
    }

    // 5. exists(Object element) - Check if a specific element is in the stack
    public boolean exists(Object element) {
        boolean found = false;
        ArrayStack tempStack = new ArrayStack();

        while (!empty()) {
            Object item = pop();
            if (item.equals(element)) {
                found = true;
            }
            tempStack.push(item);
        }

        while (!tempStack.empty()) {
            push(tempStack.pop());
        }

        return found;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyStack stack1 = new MyStack();

        // Initial input for stack elements
        try {
            System.out.println("Enter the number of elements for the stack:");
            int elementSize = sc.nextInt();
            if (elementSize < 0) throw new IllegalArgumentException("Number of elements cannot be negative.");

            System.out.println("Enter the elements:");
            for (int i = 0; i < elementSize; i++) {
                int element = sc.nextInt();
                stack1.push(element);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid integers.");
            sc.next(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Menu for choosing operations
        System.out.println("Choose an operation:");
        System.out.println("0. Exit");
        System.out.println("1. Push");
        System.out.println("2. Pop");
        System.out.println("3. toArray()");
        System.out.println("4. addRange()");
        System.out.println("5. rand()");
        System.out.println("6. unique()");
        System.out.println("7. exists()");

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("Exiting the program.");
                        sc.close();
                        return;

                    case 1:
                        // Push an element
                        System.out.print("Enter the element to push: ");
                        Object element = sc.next();
                        stack1.push(element);
                        System.out.println("Element pushed: " + element);
                        break;

                    case 2:
                        // Pop an element
                        if (stack1.empty()) {
                            System.out.println("Stack is empty, cannot pop.");
                        } else {
                            System.out.println("Popped element: " + stack1.pop());
                        }
                        break;

                    case 3:
                        // Convert stack to array and print
                        Object[] array = stack1.toArray();
                        System.out.println("Stack as array: " + Arrays.toString(array));
                        break;

                    case 4:
                        // Add range of elements
                        System.out.print("Enter the number of elements to add: ");
                        int n = sc.nextInt();
                        Object[] elements = new Object[n];
                        System.out.println("Enter the elements:");
                        for (int i = 0; i < n; i++) {
                            elements[i] = sc.next();
                        }
                        stack1.addRange(elements);
                        System.out.println("Elements added to the stack.");
                        break;

                    case 5:
                        // Randomize the stack
                        stack1.rand();
                        System.out.println("Stack elements randomized.");
                        break;

                    case 6:
                        // Remove duplicate elements
                        stack1.unique();
                        System.out.println("Duplicates removed from the stack.");
                        break;

                    case 7:
                        // Check if an element exists
                        System.out.print("Enter the element to check: ");
                        Object checkElement = sc.next();
                        boolean exists = stack1.exists(checkElement);
                        System.out.println("Element " + checkElement + " exists in stack: " + exists);
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input type. Please enter valid integers.");
                sc.next(); // Clear invalid input
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
