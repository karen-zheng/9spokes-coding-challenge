import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class AccountingCalculator {
    public static void main(String[] args) throws IOException {
        //Read file
        try {
            ObjectMapper mapper = new ObjectMapper();
            AccountingDataModel accountingData = mapper.readValue(
                    new FileReader("src/main/resources/data.json"), AccountingDataModel.class);

            EntryModel[] entries = accountingData.getData();
            String revenue = calculateRevenue(entries);

            //Return results
            System.out.print("Revenue: " + revenue + " \n" +
                    "Expenses: \n" +
                    "Gross Profit Margin: \n" +
                    "Net Profit Margin: \n" +
                    "Working Capital Ratio: ");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    static String calculateRevenue(EntryModel[] entries) {
        Double result = Arrays.stream(entries)
                .filter(entry -> "revenue".equals(entry.getAccount_category()))
                .mapToDouble(EntryModel::getTotal_value).sum();
        return result.toString();
    }

    //Calculate revenue

    //Calculate expense

    //Calculate Profit margin

    //Calculate Gross profit

    //Calculate Net Profit

    //Calculate Working Capital Ratio

    //Formatting



}
