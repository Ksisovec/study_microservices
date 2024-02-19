package com.example.employeeservice.circuitbreaker;

import java.util.function.Predicate;

public class AlwaysTrueRecordResultPredicate implements Predicate<Object> {
    @Override
    public boolean test(Object o) {
        return true;
    }
}
