package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CardNumberTest {
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }
    @ParameterizedTest
    @CsvSource({
            "nhduong, false",
            "laduc, false",
            "ttduong, false",
            "nhd20190044, false",
            "kstn_group10_2021, true",
            "fhgfghfhgf, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String cardNumber, boolean expected) {
        //when
        boolean isValid = paymentController.validateCardNumber(cardNumber);
        //then
        assertEquals(expected, isValid);
    }
}