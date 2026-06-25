public class FinancialForecasting {

    public static double futureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }

        return futureValue(currentValue, growthRate, years - 1) * (1 + growthRate);
    }

    public static void main(String[] args) {

        double currentValue = 10000;
        double growthRate = 0.10;
        int years = 5;

        double predictedValue = futureValue(currentValue, growthRate, years);

        System.out.println("===== FINANCIAL FORECASTING =====");
        System.out.println("Current Value : " + currentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "%");
        System.out.println("Years         : " + years);
        System.out.printf("Future Value  : %.2f%n", predictedValue);


    }
}