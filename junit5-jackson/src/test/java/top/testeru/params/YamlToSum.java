package top.testeru.params;

import org.junit.jupiter.params.aggregator.AggregateWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: 自定义注解类
 * @Version 1.0
 * @create: 2022/6/20 4:52 PM
 */
//指定注释要保留多长时间
@Retention(RetentionPolicy.RUNTIME)
//注解位置：方法的形式参数声明
@Target(ElementType.PARAMETER)
//作用
@AggregateWith(SumAggregator.class)
public @interface YamlToSum {
}
