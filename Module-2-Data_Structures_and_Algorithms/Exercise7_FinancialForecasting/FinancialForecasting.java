package Exercise7_FinancialForecasting;

public class FinancialForecasting {
    
    public double predictFutureValueRecursive(double currentValue, double growthRate, int periods) {
        if (periods == 0) {
            return currentValue;
        }
        return predictFutureValueRecursive(currentValue * (1 + growthRate), growthRate, periods - 1);
    }
    
    public double predictFutureValueIterative(double currentValue, double growthRate, int periods) {
        double result = currentValue;
        for (int i = 0; i < periods; i++) {
            result *= (1 + growthRate);
        }
        return result;
    }
    
    public double predictFutureValueMemoized(double currentValue, double growthRate, int periods) {
        double[][] memo = new double[periods + 1][2];
        for (int i = 0; i <= periods; i++) {
            memo[i][0] = currentValue * Math.pow(1 + growthRate, i);
        }
        return memo[periods][0];
    }
    
    public double[] predictFutureValues(double currentValue, double growthRate, int periods) {
        double[] values = new double[periods + 1];
        values[0] = currentValue;
        for (int i = 1; i <= periods; i++) {
            values[i] = values[i - 1] * (1 + growthRate);
        }
        return values;
    }
    
    public static void main(String[] args) {
        FinancialForecasting forecast = new FinancialForecasting();
        
        double currentValue = 1000.0;
        double growthRate = 0.05;
        int periods = 5;
        
        System.out.println("Recursive Prediction:");
        double recursiveResult = forecast.predictFutureValueRecursive(currentValue, growthRate, periods);
        System.out.println("Value after " + periods + " periods: " + recursiveResult);
        
        System.out.println("\nIterative Prediction:");
        double iterativeResult = forecast.predictFutureValueIterative(currentValue, growthRate, periods);
        System.out.println("Value after " + periods + " periods: " + iterativeResult);
        
        System.out.println("\nMemoized Prediction:");
        double memoizedResult = forecast.predictFutureValueMemoized(currentValue, growthRate, periods);
        System.out.println("Value after " + periods + " periods: " + memoizedResult);
        
        System.out.println("\nFuture Values Array:");
        double[] futureValues = forecast.predictFutureValues(currentValue, growthRate, periods);
        for (int i = 0; i < futureValues.length; i++) {
            System.out.println("Period " + i + ": " + futureValues[i]);
        }
        
        System.out.println("\nPredicting multiple future values:");
        for (int i = 1; i <= 10; i++) {
            double result = forecast.predictFutureValueMemoized(currentValue, growthRate, i);
            System.out.println("Value after " + i + " periods: " + result);
        }
    }
}