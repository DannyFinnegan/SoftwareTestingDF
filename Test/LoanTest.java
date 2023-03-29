import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {
    private double answer;

    private double amount;
    private int period;

    Loan NoArgLoan;


    @Test
   public void TestConstructor()
    {
        NoArgLoan = new Loan();
        assertEquals(0, NoArgLoan.getAmount());
        assertEquals(0, NoArgLoan.getPeriod());
    }
    @ParameterizedTest
    @CsvFileSource(resources = "ParamTestFileMonthlyPositive.csv", numLinesToSkip = 1)
    void testcheckMontlyRateAll(double amount, int period, double answer) {
        Loan loan = new Loan(amount, period);
        assertEquals(answer, loan.getMonthlyPayment());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "ParamTestFileTotalPositive.csv", numLinesToSkip = 1)
    void testCheckTotalPaymentAll(double amount, int period, double answer) {
        Loan loan = new Loan(amount, period);
        assertEquals(answer, loan.getTotalPayment());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "ParamTestFileNegative.csv", numLinesToSkip = 1)
    public void TestMonthlyFails(String Period, String Amount) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(Double.parseDouble(Amount), Integer.parseInt(Period));
        });
    }


    @ParameterizedTest
    @CsvFileSource(resources = "ParamTestGetRate.csv", numLinesToSkip = 1)
    public void TestGetRate(double amount, int period, double answer) {
        Loan loan = new Loan(amount, period);
        assertEquals(answer, loan.getRate());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "ParamTestGetPeriod.csv", numLinesToSkip = 1)
    public void TestGetPeriod(double amount, int period, double answer) {
        Loan loan = new Loan(amount, period);
        assertEquals(answer, loan.getPeriod());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "ParamTestGetAmount.csv", numLinesToSkip = 1)
    public void TestGetAmount(double amount, int period, double answer) {
        Loan loan = new Loan(amount, period);
        assertEquals(answer, loan.getAmount());

    }

    @Test
    public void TestSetRate() throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Loan loan = new Loan(500, 2);

        Class[] params = new Class[1];
        params[0] = Integer.class;
        Method methodCall = loan.getClass().getDeclaredMethod("setRate", params);


        methodCall.setAccessible(true);

        Object[] methodArgument = new Object[1];
        methodArgument[0] = 2;

        double actualRate = (double)
        methodCall.invoke(loan, methodArgument);
        double expectedRate = 10/100.0/12;

        assertEquals(expectedRate, actualRate);

    }

}
