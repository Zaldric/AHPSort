package AHPSort;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Exercise 1: ");
        System.out.println();

        AhpSort ahpSort = new AhpSort(5, 6);

        ahpSort.criteria = new double[][]{
                {1, 7, 4, 7, 8},
                {1/7d, 1, 1/4d, 1, 3},
                {1/4d, 4, 1, 4, 5},
                {1/7d, 1, 1/4d, 1, 3},
                {1/8d, 1/3d, 1/5d, 1/3d, 1},
        };

        ArrayList<double[]> alternatives = new ArrayList<>();

        alternatives.add(new double[]{9, 9, 8, 1/9d, 1/8d, 1/9d});
        alternatives.add(new double[]{9, 9, 9, 1/9d, 1/9d, 1/9d});
        alternatives.add(new double[]{9, 9, 9, 1, 1, 1/9d});
        alternatives.add(new double[]{9, 9, 9, 1, 1/5d, 1/9d});
        alternatives.add(new double[]{9, 9, 9, 1, 1/5d, 1/9d});

        ahpSort.alternatives = alternatives;

        double consistencyRatio = ahpSort.calculateConsistencyRatio();
        System.out.println("Consistency ratio: " + consistencyRatio);
        System.out.println();
        System.out.println("Results: ");
        System.out.println();

        boolean[] candidates = ahpSort.eigenvalueMethod();
        String[] names = new String[]{"F&F", "DeLorean", "Transporter", "Carmaggedon", "Cars", "Driver"};

        for (int i = 0; i < candidates.length; ++i ) {
            if (candidates[i]) {
                System.out.println(names[i] + " : approved.");
            } else {
                System.out.println(names[i] + " : not approved.");
            }
        }

        System.out.println();
        System.out.println("Exercise 2: ");
        System.out.println();

        ahpSort = new AhpSort(5, 6);

        ahpSort.criteria = new double[][]{
                {1, 7, 4, 7, 8},
                {1/7d, 1, 1/4d, 1, 3},
                {1/4d, 4, 1, 4, 5},
                {1/7d, 1, 1/4d, 1, 3},
                {1/8d, 1/3d, 1/5d, 1/3d, 1},
        };

        alternatives = new ArrayList<>();

        alternatives.add(new double[]{9, 9, 1/4d, 9, 9, 9});
        alternatives.add(new double[]{9, 1/4d, 1/8d, 1/4d, 9, 9});
        alternatives.add(new double[]{9, 1/6d, 1/6d, 8, 9, 9});
        alternatives.add(new double[]{9, 8, 1/3d, 9, 9, 9});
        alternatives.add(new double[]{9, 9, 1/7d, 1/9d, 9, 9});

        ahpSort.alternatives = alternatives;

        consistencyRatio = ahpSort.calculateConsistencyRatio();
        System.out.println("Consistency ratio: " + consistencyRatio);
        System.out.println();
        System.out.println("Results: ");
        System.out.println();

        candidates = ahpSort.eigenvalueMethod();
        names = new String[]{"F&F", "DeLorean", "Transporter", "Carmaggedon", "Cars", "Driver"};

        for (int i = 0; i < candidates.length; ++i ) {
            if (candidates[i]) {
                System.out.println(names[i] + " : approved.");
            } else {
                System.out.println(names[i] + " : not approved.");
            }
        }
    }
}