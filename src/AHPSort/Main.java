package AHPSort;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String mode;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println();
            System.out.println("Please indicate the operation you want to perform: \n");
            System.out.println("Press 0 to finish the program.");
            System.out.println("Press 1 to to solve the practice exercises.");
            System.out.println("Press 2 to enter the matrices of the AHP problem manually.");
            mode = input.nextLine();

            if (mode.equals("1")) {

                System.out.println("Exercise 1: ");
                System.out.println();

                AhpSort ahpSort = new AhpSort(5, 6);

                ahpSort.criteria = new double[][]{
                        {1, 7, 4, 7, 8},
                        {1 / 7d, 1, 1 / 4d, 1, 3},
                        {1 / 4d, 4, 1, 4, 5},
                        {1 / 7d, 1, 1 / 4d, 1, 3},
                        {1 / 8d, 1 / 3d, 1 / 5d, 1 / 3d, 1},
                };

                ArrayList<double[]> alternatives = new ArrayList<>();

                alternatives.add(new double[]{9, 9, 8, 1 / 9d, 1 / 8d, 1 / 9d});
                alternatives.add(new double[]{9, 9, 9, 1 / 9d, 1 / 9d, 1 / 9d});
                alternatives.add(new double[]{9, 9, 9, 1, 1, 1 / 9d});
                alternatives.add(new double[]{9, 9, 9, 1, 1 / 5d, 1 / 9d});
                alternatives.add(new double[]{9, 9, 9, 1, 1 / 5d, 1 / 9d});

                ahpSort.alternatives = alternatives;

                double consistencyRatio = ahpSort.calculateConsistencyRatio();
                System.out.println("Consistency ratio: " + consistencyRatio);
                System.out.println();
                System.out.println("Results: ");
                System.out.println();

                boolean[] candidates = ahpSort.eigenvalueMethod();
                String[] names = new String[]{"F&F", "DeLorean", "Transporter", "Carmaggedon", "Cars", "Driver"};

                for (int i = 0; i < candidates.length; ++i) {
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
                        {1 / 7d, 1, 1 / 4d, 1, 3},
                        {1 / 4d, 4, 1, 4, 5},
                        {1 / 7d, 1, 1 / 4d, 1, 3},
                        {1 / 8d, 1 / 3d, 1 / 5d, 1 / 3d, 1},
                };

                alternatives = new ArrayList<>();

                alternatives.add(new double[]{7, 9, 1 / 6d, 8, 1 / 7d, 6});
                alternatives.add(new double[]{1 / 7d, 9, 1 / 9d, 1 / 4d, 1 / 6d, 8});
                alternatives.add(new double[]{9, 1 / 9d, 1 / 9d, 7, 1 / 3d, 6});
                alternatives.add(new double[]{1 / 3d, 7, 1 / 9d, 8, 1 / 7d, 9});
                alternatives.add(new double[]{8, 9, 1 / 4d, 1 / 2d, 1 / 6d, 6});

                ahpSort.alternatives = alternatives;

                consistencyRatio = ahpSort.calculateConsistencyRatio();
                System.out.println("Consistency ratio: " + consistencyRatio);
                System.out.println();
                System.out.println("Results: ");
                System.out.println();

                candidates = ahpSort.eigenvalueMethod();
                names = new String[]{"F&F", "DeLorean", "Transporter", "Carmaggedon", "Cars", "Driver"};

                for (int i = 0; i < candidates.length; ++i) {
                    if (candidates[i]) {
                        System.out.println(names[i] + " : approved.");
                    } else {
                        System.out.println(names[i] + " : not approved.");
                    }
                }

            } else if (mode.equals("2")) {

                int numberOfCriteria, numberOfAlternatives;
                String data;


                System.out.println("Number of criteria: ");
                data = input.nextLine();
                if (!StringUtils.isNumeric(data) || Integer.parseInt(data) < 0) {
                    do {
                        System.out.println("Please enter a valid number.");
                        data = input.nextLine();
                    } while (!StringUtils.isNumeric(data) || data.contains(".") || data.contains(","));
                }
                numberOfCriteria = Integer.parseInt(data);
                System.out.println();

                System.out.println("Number of alternatives:");
                data = input.nextLine();
                if (!StringUtils.isNumeric(data) || Integer.parseInt(data) < 0) {
                    do {
                        System.out.println("Please enter a valid number.");
                        data = input.nextLine();
                    } while (!StringUtils.isNumeric(data) || data.contains(".") || data.contains(","));
                }
                numberOfAlternatives = Integer.parseInt(data);
                System.out.println();

                AhpSort ahpSort = new AhpSort(numberOfCriteria, numberOfAlternatives);
                double[][] criteriaMatrix = new double[numberOfCriteria][numberOfCriteria];

                for (int i = 0; i < numberOfCriteria; ++i) {
                    String values[];
                    boolean validValues;
                    do {
                        validValues = true;
                        System.out.println("Please enter row " + (i + 1) + " of the criteria matrix by separating each value with ';': \n");
                        values = input.nextLine().split(";");

                        if (values.length != numberOfCriteria) {
                            validValues = false;
                            System.out.println("The number of values should be " + numberOfCriteria + ".");
                        } else {
                            if (values[i].equals("1")) {
                                for (String value : values) {
                                    if (!StringUtils.isNumeric(value)) {
                                        if (value.contains("\\.")) {
                                            String[] parts = value.split("\\.");
                                            validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                        }
                                        if (value.contains("/")) {
                                            String[] parts = value.split("/");
                                            validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("The reciprocal value of the row must be 1.");
                                validValues = false;
                            }
                        }
                    } while (!validValues);

                    for (int j = 0; j < numberOfCriteria; ++j) {
                        if (!values[j].contains("/")) {
                            criteriaMatrix[i][j] = Double.parseDouble(values[j]);
                        } else {
                            String[] parts = values[j].split("/");
                            criteriaMatrix[i][j] = Double.valueOf(parts[0]) / Double.valueOf(parts[1]);
                        }
                    }

                }

                System.out.println();
                ahpSort.criteria = criteriaMatrix;
                ArrayList<double[]> alternatives = new ArrayList<>();

                for (int i = 0; i < numberOfCriteria; ++i) {
                    System.out.println("Please enter the alternative's comparision matrices.");
                    double[] alternativeMatrix = new double[numberOfAlternatives];

                    String values[];
                    boolean validValues;
                    do {
                        validValues = true;
                        System.out.println("Please enter the alternative comparision matrix of the criteria " + (i + 1) + " by separating each value with ';': \n");
                        values = input.nextLine().split(";");

                        if (values.length != numberOfAlternatives) {
                            System.out.println("The number of values should be " + numberOfAlternatives + ".");
                            validValues = false;
                        } else {
                            for (String value : values) {
                                if (!StringUtils.isNumeric(value)) {
                                    if (value.contains("\\.")) {
                                        String[] parts = value.split("\\.");
                                        validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                    }
                                    if (value.contains("/")) {
                                        String[] parts = value.split("/");
                                        validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                    }
                                }
                            }
                        }
                    } while (!validValues);

                    for (int j = 0; j < numberOfAlternatives; ++j) {
                        if (!values[j].contains("/")) {
                            alternativeMatrix[j] = Double.parseDouble(values[j]);
                        } else {
                            String[] parts = values[j].split("/");
                            alternativeMatrix[j] = Double.valueOf(parts[0]) / Double.valueOf(parts[1]);
                        }
                    }

                    alternatives.add(alternativeMatrix);
                }

                ahpSort.alternatives = alternatives;

                double consistencyRatio = ahpSort.calculateConsistencyRatio();
                System.out.println("Consistency ratio: " + consistencyRatio);
                System.out.println();
                System.out.println("Results: ");
                System.out.println();

                boolean[] candidates = ahpSort.eigenvalueMethod();

                for (int i = 0; i < candidates.length; ++i) {
                    if (candidates[i]) {
                        System.out.println("Alternative " + i + " : approved.");
                    } else {
                        System.out.println("Alternative " + i +  " : not approved.");
                    }
                }
            }
        } while (!mode.equals("0"));
    }
}