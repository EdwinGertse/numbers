package com.tegres.calculations;

import com.tegres.calculations.exceptions.NegativeNumberNotSupportedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Numbers {
    private static final int HIGHEST_NUMBER = 1000;

    public int Add2(String numbers){
        if(isEmpty(numbers))
            return 0;
        else if(numbers.length() == 1)
            return Integer.valueOf(numbers);
        else if(hasSpecialCharacter(numbers)) {
            return getSum(numbers);
        }
        else return -1;
    }

    public int Add(String numbers) {
        if(isEmpty(numbers)) return 0;
        List<Integer> integers = extractNumbers(numbers);
        hasNegativeNumbers(integers);
        integers = ignoreInvalidNumbers(integers);
        return calcSum(integers);
    }

    private boolean isEmpty(String str) {
        return (str == null || str.trim().isEmpty());
    }

    private boolean isEmpty(Collection collection) {
        return collection == null && collection.isEmpty();
    }

    private List<String> removeEmptyElements(String... elements) {
        return Arrays.stream(elements)
                .filter(item -> !item.isEmpty())
                .collect(Collectors.toList());
    }

    private boolean hasSpecialCharacter(String numbers) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(numbers);
        return m.find();
    }

    private int getSum(String numbers) {
        String v = numbers.replaceAll("[^0-9]",",");
        String[] split = v.split(",");

        List<String> list = removeEmptyElements(split);

        List<Integer> intList = list.stream()
                .map(Integer::valueOf)
                .filter(i -> i <= 1000)
                .collect(Collectors.toList());

        return intList.stream().reduce(0, Integer::sum);
    }

    public List<Integer> extractNumbers(String numbers) {
        List<Integer> result = new ArrayList<>();
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(numbers);
        while(m.find()){
            result.add(Integer.valueOf(m.group()));
        }
        return result;
    }

    public List<Integer> ignoreInvalidNumbers(List<Integer> numbers) {
        return numbers.stream().filter(i -> i <= HIGHEST_NUMBER).collect(Collectors.toList());
    }

    public void hasNegativeNumbers(List<Integer> numbers) {
        List<Integer> negatives = new ArrayList<>();
        for(Integer n : numbers) {
            if(n < 0) {
                negatives.add(n);
            }
        }
        if(!isEmpty(negatives))
            throw new NegativeNumberNotSupportedException(negatives);
    }

    public int calcSum(List<Integer> numbers) {
        int result = 0;
        for(Integer n : numbers) {
            result += n;
        }
        return result;
    }
}
