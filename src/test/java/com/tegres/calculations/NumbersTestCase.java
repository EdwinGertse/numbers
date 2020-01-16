package com.tegres.calculations;

import com.tegres.calculations.exceptions.NegativeNumberNotSupportedException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NumbersTestCase {
    private Numbers numbers;

    @Before
    public void setup() {
        numbers = new Numbers();
    }

    @Test
    public void whenAddEmptyStringThenReturnZero() {
        int sum = numbers.Add("");
        assertThat(sum, is(0));
    }

    @Test
    public void whenAddSingleNumberReturnGivenNumber() {
        int sum = numbers.Add("1");
        assertThat(sum, is(1));
    }

    @Test
    public void whenAddMultipleNumberReturnSum() {
        int sum = numbers.Add("1,2");
        assertThat(sum, is(3));
    }

    @Test
    public void whenAddMultipleNumberWithMultipleDividersReturnSum() {
        int sum = numbers.Add("1\n2,3");
        assertThat(sum, is(6));
    }
    @Test
    public void whenAddMultipleNumbersWithMultipleDividersAtStartReturnSum() {
        int sum = numbers.Add("//;\\n1;2");
        assertThat(sum, is(3));
    }

    @Test
    public void whenAddNegativeNumberReturnException() {
        try {
            numbers.Add("-2,-3");
            fail();
        } catch (NegativeNumberNotSupportedException e) {
            assertEquals("negatives not allowed [-2, -3]", e.getMessage());
        }
    }

    @Test
    public void whenAddNumbersLargerThen1000Ignore(){
        int sum = numbers.Add("//[***]\\n1***2***1003");
        assertThat(sum, is(3));
    }
}