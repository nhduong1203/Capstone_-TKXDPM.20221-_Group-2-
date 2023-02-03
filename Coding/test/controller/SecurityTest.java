package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }
    @ParameterizedTest
    @CsvSource({
            "910, true",
            "101, true",
            "nguyen hai duong, false",
            "cntn_group2_2022, false",
            "siuuuu, false",
            "11_00, false",
            "998, true",
            "<<aa=bb_cc>>, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String securityCode, boolean expected) {
        //when
        boolean isValid = paymentController.validateSecurityCode(securityCode);
        //then
        assertEquals(expected, isValid);
    }
}