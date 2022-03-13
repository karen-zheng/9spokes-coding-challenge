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
            String revenue = calculateRevenue(entries).toString();
            String expense = calculateExpense(entries).toString();
            String gpm = calculateGrossProfitMargin(entries).toString();
            String npm = calculateNetProfitMargin(entries).toString();
            String wcr = calculateWorkingCapitalRatio(entries).toString();

            //Return results
            System.out.print("Revenue: " + revenue + " \n" +
                    "Expenses: " + expense + " \n" +
                    "Gross Profit Margin: " + gpm + "\n" +
                    "Net Profit Margin: " + npm + "\n" +
                    "Working Capital Ratio: " + wcr);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    static Double calculateRevenue(EntryModel[] entries) {
        Double result = Arrays.stream(entries)
                .filter(entry -> "revenue".equals(entry.getAccount_category()))
                .mapToDouble(EntryModel::getTotal_value).sum();
        return result;
    }

    static Double calculateExpense(EntryModel[] entries) {
        Double result = Arrays.stream(entries)
                .filter(entry -> "expense".equals(entry.getAccount_category()))
                .mapToDouble(EntryModel::getTotal_value).sum();
        return result;
    }

    static Double calculateGrossProfitMargin(EntryModel[] entries) {
        Double result = Arrays.stream(entries)
                .filter(entry -> "sales".equals(entry.getAccount_type()) | "debit".equals(entry.getValue_type()))
                .mapToDouble(EntryModel::getTotal_value).sum();

        Double revenue = calculateRevenue(entries);

        return result/revenue;
    }

    static Double calculateNetProfitMargin(EntryModel[] entries) {
        Double expense = calculateExpense(entries);

        Double revenue = calculateRevenue(entries);

        return (revenue - expense)/revenue;
    }

    static Double calculateWorkingCapitalRatio(EntryModel[] entries) {
        Double assets = calculateAssets(entries);

        Double liabilities = calculateLiabilities(entries);

        return assets/liabilities;
    }

    private static Double calculateLiabilities(EntryModel[] entries) {
        Double liabilitiesCredit = Arrays.stream(entries)
                .filter(entry -> "liability".equals(entry.getAccount_category())
                                & "credit".equals(entry.getValue_type())
                                & ("current".equals(entry.getAccount_type())
                                | "current_accounts_payable".equals(entry.getAccount_type())
                        )
                )
                .mapToDouble(EntryModel::getTotal_value).sum();

        Double liabilitiesDebit = Arrays.stream(entries)
                .filter(entry -> "liability".equals(entry.getAccount_category())
                                & "debit".equals(entry.getValue_type())
                                & ("current".equals(entry.getAccount_type())
                                | "current_accounts_payable".equals(entry.getAccount_type())
                        )
                )
                .mapToDouble(EntryModel::getTotal_value).sum();
        return liabilitiesCredit - liabilitiesDebit;
    }

    private static Double calculateAssets(EntryModel[] entries) {
        Double assetsDebit = Arrays.stream(entries)
            .filter(entry -> "assets".equals(entry.getAccount_category())
                            & "debit".equals(entry.getValue_type())
                            & ("current".equals(entry.getAccount_type())
                            | "bank".equals(entry.getAccount_type())
                            | "current_accounts_payable".equals(entry.getAccount_type())
                    )
            )
            .mapToDouble(EntryModel::getTotal_value).sum();

        Double assetsCredit = Arrays.stream(entries)
                .filter(entry -> "assets".equals(entry.getAccount_category())
                                & "credit".equals(entry.getValue_type())
                                & ("current".equals(entry.getAccount_type())
                                | "bank".equals(entry.getAccount_type())
                                | "current_accounts_payable".equals(entry.getAccount_type())
                        )
                )
                .mapToDouble(EntryModel::getTotal_value).sum();

        return assetsDebit - assetsCredit;
    }


    //Formatting



}
