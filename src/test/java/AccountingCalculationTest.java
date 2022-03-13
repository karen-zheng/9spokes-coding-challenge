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
        String result = AccountingCalculator.calculateRevenue(entries);
        assertEquals("32431.0", result );
    }

}
