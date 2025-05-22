package com.qbhy.poster.utils;
import java.util.Optional;
import java.util.function.Function;

public class NumberUtils {
    // 定义转换策略
    public static final Function<Double, Integer> ROUND = d -> Math.toIntExact(Math.round(d));
    public static final Function<Double, Integer> CEIL = d -> (int) Math.ceil(d);
    public static final Function<Double, Integer> FLOOR = d -> (int) Math.floor(d);

    // 带默认值的转换
    public static Integer convert(Double value, Function<Double, Integer> converter, Integer defaultValue) {
        return Optional.ofNullable(value)
                .map(converter)
                .orElse(defaultValue);
    }
}

