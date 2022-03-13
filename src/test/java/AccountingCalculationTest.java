import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountingCalculationTest {
    ObjectMapper mapper = new ObjectMapper();
    AccountingDataModel accountingData = mapper.readValue(
            new FileReader("src/main/resources/data.json"), AccountingDataModel.class);

    EntryModel[] entries = accountingData.getData();

    public AccountingCalculationTest() throws IOException {
    }

    @Test
    void returnsTotalRevenue() throws IOException {
        Double result = AccountingCalculator.calculateRevenue(entries);
        assertEquals(32431.0, result );
    }

    @Test
    void returnsTotalExpense() {
        Double result = AccountingCalculator.calculateExpense(entries);
        assertEquals(36529.68, result );
    }

    @Test
    void returnsGrossProfitMargin() {
        Double result = AccountingCalculator.calculateGrossProfitMargin(entries);
        assertEquals(2.9788554161142113, result );
    }

    @Test
    void returnsNetProfitMargin() {
        Double result = AccountingCalculator.calculateNetProfitMargin(entries);
        assertEquals(-0.1263815485183929, result );
    }

    @Test
    void returnsWorkingCapitalRatio() {
        Double result = AccountingCalculator.calculateWorkingCapitalRatio(entries);
        assertEquals(0.7130127831205757, result );
    }

}
