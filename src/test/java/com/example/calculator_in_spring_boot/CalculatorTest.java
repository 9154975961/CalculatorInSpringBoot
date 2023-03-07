package com.example.calculator_in_spring_boot;

import com.example.calculator_in_spring_boot.calculator.CalculatorUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testEvaluateExpressionAddition(){
        String expression = "2.2515 + 3.0101 + 4.000001 + 5.03 + 2.2";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("16.49", result);
    }

    @Test
    public void testEvaluateExpressionSubtractionTwo(){
        String expression = "- 3.4223";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("-3.42", result);
    }
    @Test
    public void testEvaluateExpressionSubtraction(){
        String expression = "2.15 - 3.4223 + 4.01 + 5.03 + 2.7";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("10.47", result);
    }

    @Test
    public void testEvaluateExpressionSubtractionMinus(){
        String expression = "2.22 - 3.4689 - 5.2576 - 5.1478 + 10.09";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("-1.56", result);
    }

    @Test
    public void testEvaluateExpressionMultiplication(){
        String expression = "7.4545 - 2.1588 * 2.5648";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("1.92", result);
    }

    @Test
    public void testEvaluateExpressionDivision(){
        String expression = "7.4545 - 2.1588 / 2.5648";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("6.61", result);
    }

    @Test
    public void testEvaluateExpressionDivisionByZero(){
        String expression = "4 / 0";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("делить на ноль нельзя", result);
    }

    @Test
    public void testEvaluateExpressionDivisionByZeroTwo(){
        String expression = "7 / (4-4)";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("делить на ноль нельзя", result);
    }

    @Test
    public void testEvaluateExpressionBrackets(){
        String expression = "(7.4545 - 2.1588) / (2.5648 - 4) + 5.27";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("1.58", result);
    }

    @Test
    public void testEvaluateExpressionBracketsTwo(){
        String expression = "4+(7.4545 - 2.1588 + ( 4 + 7))";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("20.3", result);
    }

    @Test
    public void testEvaluateExpressionBracketsThree(){
        String expression = "4*(7 - 2 + ( 4 + 7))";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("64.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrt(){
        String expression = "√225";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("15.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrtTwo(){
        String expression = "-√225";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("15.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrtThree(){
        String expression = "5-√225";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("-10.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrtFour(){
        String expression = "(5-√225) + 10 + √16";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("4.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrtFive(){
        String expression = "7+7√128";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("9.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrtSix(){
        String expression = "7+8√(128+128)";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("9.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrtSeven(){
        String expression = "8√(128+128)";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("2.0", result);
    }

    @Test
    public void testEvaluateExpressionSqrtEight(){
        String expression = "8√(128+128) + √25";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("7.0", result);
    }

    /*@Test
    public void testEvaluateExpressionSqrtNine(){
        String expression = "√(8√(128+128))";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("4.0", result);
    }*/

    @Test
    public void testEvaluateExpressionSqrtTen(){
        String expression = "√(5√(59000 + 049))";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("3.0", result);
    }

    @Test
    public void testEvaluateExpressionSquareOne(){
        String expression = "9^5";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("59049.0", result);
    }

    @Test
    public void testEvaluateExpressionSquareTwo(){
        String expression = "7+ 2^2";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("11.0", result);
    }

    @Test
    public void testEvaluateExpressionSquareThree(){
        String expression = "(7+2)^2";
        String result = null;
        result = CalculatorUtil.calc(expression);
        assertEquals("81.0", result);
    }

}
