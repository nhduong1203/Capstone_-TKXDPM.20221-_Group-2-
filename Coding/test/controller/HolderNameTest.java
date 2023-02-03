package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class HolderNameTest {
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }
    @ParameterizedTest
    @CsvSource({
            "Siuuu, false",
            "Ronaldo 07, true",
            "nguyen, false",
            "nguyen hai /=, false",
            "trinh tung duong, true",
            "cntn_group10_2021, false",
            "messi<>, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String cardNumber, boolean expected) {
        //when
        boolean isValid = paymentController.validateHolderName(cardNumber);
        //then
        assertEquals(expected, isValid);
    }
}