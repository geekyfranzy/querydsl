package com.querydsl;

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanPath;

public class BooleanExtensions2 {
    
    @QueryDelegate(boolean.class)
    public static Predicate isFalse(BooleanPath path) {
        return path.isNotNull().or(path.eq(false));
    }

    @QueryDelegate(boolean.class)
    public static Predicate isTrue(BooleanPath path) {
        return path.eq(true);
    }

}
