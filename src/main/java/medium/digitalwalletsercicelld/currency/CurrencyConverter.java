package medium.digitalwalletsercicelld.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private static final Map<CurrencyType, BigDecimal> exchangeRates = new HashMap<>();

    static {
        // Initialize exchange rates
        exchangeRates.put(CurrencyType.USD, BigDecimal.ONE);
        exchangeRates.put(CurrencyType.EUR, new BigDecimal("0.85"));
        exchangeRates.put(CurrencyType.GBP, new BigDecimal("0.72"));
        exchangeRates.put(CurrencyType.JPY, new BigDecimal("110.00"));
        // Add more exchange rates as needed
    }

    public static BigDecimal convert(BigDecimal amount, CurrencyType sourceCurrency, CurrencyType targetCurrency) {
        BigDecimal sourceRate = exchangeRates.get(sourceCurrency);
        BigDecimal targetRate = exchangeRates.get(targetCurrency);
        return amount.multiply(sourceRate).divide(targetRate, RoundingMode.HALF_UP);
    }
}
