package com.example.test;

import com.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Calculator Test Cases")
public class CalculatorTest {

    static Calculator calculator;

    @BeforeAll
    static void setUpBeforeEach() {
        System.out.println("Starting Calculator Test..");
    }

    @AfterAll
    static void setUpAfterAll() {
        System.out.println("Calculator Test Completed..");
    }

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
        System.out.println("Instance Of The Calculator Created");
    }

    @AfterEach
    void afterEach() {
        calculator = null;
        System.out.println("Clean Up");
    }

    @Nested
    @DisplayName("Addition Tests")
    class AdditionTest {
        @Test
        @DisplayName("Addition: Should add two positive numbers correctly")
        void testAdditionWithPositiveNumbers() {

         int actual = calculator.add(5,5);
         assertEquals(10,actual,"5 + 5 = 10");

        }

        @Test
        @DisplayName("Addition: Should add positive and negative numbers correctly")
        void testAdditionWithPositiveAndNegativeNumbers() {

           int actual = calculator.add(10,-5);
           assertEquals(5,actual,"10 + (-5) = 5");

        }

        @Test
        @DisplayName("Addition: Should handle zero correctly")
        void testAdditionWithZero() {

            int result = calculator.add(5, 0);
            assertEquals(5, result, "5 + 0 should equal 5");
        }

        @ParameterizedTest(name = "Addition Test By Parameters")
        @CsvSource({
                "2, 3, 5",
                "10, 20, 30",
                "7, 8, 15",
                "-5, -3, -8",
                "100, -50, 50",
                "0, 0, 0",
                "1, -1, 0"
        })
        @DisplayName("Param Add Test")
        void testAddParam(int a , int b , int expected){
            int actual = calculator.add(a,b);
            assertEquals(expected,actual);
        }

    }

    @Nested
    @DisplayName("Subtraction Tests")
    class SubtractionTests {
        @Test
        @DisplayName("Subtraction: Should subtract two positive numbers correctly")
        void testSubtractionWithPositiveNumbers() {

            int actual = calculator.subtract(10 , 3);
            assertEquals(7, actual, "10 - 3 should equal 7");
        }

        @ParameterizedTest(name = "Param Sub Test")
        @CsvSource({
          "10,2,8",
          "2,10,-8",
          "-2,-2,0",
          "2,2,0"
        })
        @DisplayName("Subtract Params")
        void testParamSubtract(int a , int b , int expected){
            int actual = calculator.subtract(a,b);
            assertEquals(expected,actual);
        }
    }

    @Nested
    @DisplayName("Multiplication Tests")
    class MultiplyTests {
        @Test
        @DisplayName("Multiplication: Should multiply two positive numbers correctly")
        void testMultiplicationWithPositiveNumbers() {

            int actual = calculator.multiply(4, 3);
            assertEquals(12,actual, "4 * 3 should equal 12");
        }
    @ParameterizedTest(name="Param Mult Test")
    @CsvSource({
            "10,0,0",
            "0,10,0",
            "-1,10,-10",
            "10,-1,-10",
            "-1,-10,10",
            "0,0,0"
    })
    @DisplayName("Mul Param")
    void testParamMultiply(int a , int b , int expected){

            int actual = calculator.multiply(a,b);
            assertEquals(expected,actual);

    }
    }

    @Nested
    @DisplayName("Division Tests")
    class DivisionTests {
        @Test
        @DisplayName("Division: Should divide two positive numbers correctly")
        void testDivisionWithPositiveNumbers() {
            int result = calculator.divide(12, 3);
            assertEquals(4, result, "12 / 3 should equal 4");
        }

        @Test
        @DisplayName("Division: Should throw exception when dividing by zero")
        void testDivisionByZeroThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> {
                calculator.divide(10, 0);
            }, "Division by zero should throw ArithmeticException");
        }
    }
}