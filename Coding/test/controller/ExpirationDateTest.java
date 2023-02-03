package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


class ExpirationDateTest {
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }
    @ParameterizedTest
    @CsvSource({
            "910, false",
            "sksksks, false",
            "alo alo, false",
            "01/31, true",
            "100/02, false",
            "24/133, false",
            "24/13, false",
            "11/25, true",
            "cntn_group2_2022, false",
            "trinh tung duong, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String expirationDate, boolean expected) {
        //when
        boolean isValid = paymentController.validateExpirationDate(expirationDate);
        //then
        assertEquals(expected, isValid);
    }
}