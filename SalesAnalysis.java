import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SalesAnalysis {

  
    private static Map<String, Double> generateMonthlySales() {
        Random random = new Random();
        int unitsSold = random.nextInt(901) + 100; 
        double price = random.nextDouble() * 11 + 20; 
        double amount = unitsSold * price;

        Map<String, Double> result = new HashMap<>();
        result.put("unitsSold", (double) unitsSold);
        result.put("amount", amount);
        return result;
    }

   
    private static void analyzeSalesData() {
        Map<Integer, int[]> monthlyData = new HashMap<>();
        Map<Integer, int[]> quarterlyData = new HashMap<>();

        for (int day = 1; day <= 365; day++) {
            Map<String, Double> sales = generateMonthlySales();
            int unitsSold = sales.get("unitsSold").intValue();
            double amount = sales.get("amount");

            int month = (day - 1) / 30 + 1;

           
            if (!monthlyData.containsKey(month)) {
                monthlyData.put(month, new int[]{0, 0});
            }
            int[] monthlyValues = monthlyData.get(month);
            monthlyValues[0] += unitsSold;
            monthlyValues[1] += amount;

            
            int quarter = (month - 1) / 3 + 1;
            if (!quarterlyData.containsKey(quarter)) {
                quarterlyData.put(quarter, new int[]{0, 0});
            }
            int[] quarterlyValues = quarterlyData.get(quarter);
            quarterlyValues[0] += unitsSold;
            quarterlyValues[1] += amount;
        }

       
        System.out.println("Monthly Data:");
        for (Map.Entry<Integer, int[]> entry : monthlyData.entrySet()) {
            int month = entry.getKey();
            int[] data = entry.getValue();
            System.out.printf("Month %d: Units Sold - %d, Amount - %.2f%n", month, data[0], data[1]);
        }

        System.out.println("\nQuarterly Data:");
        for (Map.Entry<Integer, int[]> entry : quarterlyData.entrySet()) {
            int quarter = entry.getKey();
            int[] data = entry.getValue();
            System.out.printf("Quarter %d: Units Sold - %d, Amount - %.2f%n", quarter, data[0], data[1]);
        }

        int maxAmountQuarter = quarterlyData.entrySet().stream()
                .max(Map.Entry.comparingByValue((a, b) -> Integer.compare(a[1], b[1])))
                .orElseThrow()
                .getKey();
        int maxUnitsQuarter = quarterlyData.entrySet().stream()
                .max(Map.Entry.comparingByValue((a, b) -> Integer.compare(a[0], b[0])))
                .orElseThrow()
                .getKey();

        System.out.printf("\nQuarter with Maximum Sale (Amount): Quarter %d%n", maxAmountQuarter);
        System.out.printf("Quarter with Maximum Sale (Units): Quarter %d%n", maxUnitsQuarter);
    }

    // Main method to run the analysis
    public static void main(String[] args) {
        analyzeSalesData();
    }
}
