package AHPSort;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;

class AhpSort {

    double[][] criteria;
    ArrayList<double[]> alternatives;
    private boolean[] selectedAlternatives;

    AhpSort(int numberOfCriteria, int numberOfAlternatives) {

        criteria = new double[numberOfCriteria][];
        alternatives = new ArrayList<>();
        selectedAlternatives = new boolean[numberOfAlternatives];

        for (int i = 0; i < numberOfCriteria; ++i) {
            double[] alternativeCriteriaComparison = new double[numberOfAlternatives];
            alternatives.add(alternativeCriteriaComparison);
        }
    }

    /**
     * Calculates the alternative's ranking using the eigenvalue method.
     *
     * @return an array with the selected alternatives.
     */
    boolean[] eigenvalueMethod() {

        double[] criteriaWeights = normalizeVector(calculateMatrixWeightsEigenvalue(criteria));
        ArrayList<ArrayList<double[]>> alternativesWeights = new ArrayList<>();
        int i = 0;
        for (double[] alternative : alternatives) {
            //comparisonMatrix
            ArrayList<double[]> alternativeWeights = new ArrayList<>();



            for (double value : alternative) {
                double[][] comparisonMatrix = new double[2][2];
                comparisonMatrix[0][0] = 1;
                comparisonMatrix[0][1] = value;
                comparisonMatrix[1][0] = 1 / value;
                comparisonMatrix[1][1] = 1;
                if (i == 4) {
                    String a = "h";
                }
                ++i;
                alternativeWeights.add(normalizeVector(calculateMatrixWeightsEigenvalue(comparisonMatrix)));
            }
            alternativesWeights.add(alternativeWeights);
        }

        aggregation(criteriaWeights, alternativesWeights);
        return selectedAlternatives;
    }

    /**
     * Aggregates the two matrices to obtain the ranking of the alternatives.
     *
     * @param criteriaWeights the criteria priorities.
     * @param alternativesWeights an array that contains the local priorities of the alternatives for each criteria.
     */
    private void aggregation(double[] criteriaWeights, ArrayList<ArrayList<double[]>> alternativesWeights) {

        for (int i = 0; i < alternativesWeights.get(0).size(); ++i) {
            double a = 0, b = 0;
            int criteriaNumber = 0;
            for (ArrayList<double[]> alternativesWeight : alternativesWeights) {
                a += alternativesWeight.get(i)[0] * criteriaWeights[criteriaNumber];
                b += alternativesWeight.get(i)[1] * criteriaWeights[criteriaNumber];
                ++criteriaNumber;
            }
            selectedAlternatives[i] = (a >= b);
        }
    }

    /**
     * Calculates the matrix's weights using the eigenvalue method.
     *
     * @return an array with the matrix's weight.
     */
    private double[] calculateMatrixWeightsEigenvalue(double[][] matrix) {

        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);


        EigenDecomposition decomposition = new EigenDecomposition(realMatrix);
        double[] eigenValues = decomposition.getRealEigenvalues();

        int pos = 0;

        for (int i = 1; i < eigenValues.length; ++i) {
            if (eigenValues[i] > eigenValues[pos]) {
                pos = i;
            }
        }

        return decomposition.getEigenvector(pos).toArray();
    }

    /**
     * Normalizes the vector by dividing each element by the sum of all vector's elements.
     *
     * @param vector the vector to normalize.
     * @return an array with normalized elements.
     */
    private double[] normalizeVector(double[] vector) {

        double[] normalizedVector = new double[vector.length];
        double sum = 0;

        for (double element : vector) {
            sum += element;
        }

        for (int i = 0; i < vector.length; ++i) {
            normalizedVector[i] = vector[i] / sum;
        }

        return normalizedVector;
    }

    /**
     * Calculates the consistency ratios for the AHP problem.
     *
     * @return the consistency ratio for the matrix.
     */
    double calculateConsistencyRatio() {

        double[] randomIndex = new double[] {0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};

        RealMatrix realMatrix = MatrixUtils.createRealMatrix(criteria);


        EigenDecomposition decomposition = new EigenDecomposition(realMatrix);
        double[] eigenValues = decomposition.getRealEigenvalues();

        int pos = 0;

        for (int i = 1; i < eigenValues.length; ++i) {
            if (eigenValues[i] > eigenValues[pos]) {
                pos = i;
            }
        }

        double consistencyIndex = (eigenValues[pos] - criteria.length) / (criteria.length - 1);

        if (criteria.length <= 3) {
            return consistencyIndex / randomIndex[0];
        }
        return consistencyIndex / randomIndex[criteria.length - 1];
    }
}
