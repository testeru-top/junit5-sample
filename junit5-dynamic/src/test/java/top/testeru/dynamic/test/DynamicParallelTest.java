package top.testeru.dynamic.test;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @Classname: DynamicParallelTest
 * @Description: 动态测试的并发测试
 * @Date: 2022/6/8 21:44
 * @Created by top.testeru
 */
public class DynamicParallelTest {
    //动态测试方法指定多线程执行
    @Execution(ExecutionMode.CONCURRENT)
    @TestFactory
    Collection<DynamicTest> parallelTest() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> {
                    System.out.println(Thread.currentThread().getName()+" => 1st dynamic test");
                }),
                dynamicTest("2nd dynamic test", () -> {
                    System.out.println(Thread.currentThread().getName()+" => 2nd dynamic test");
                }),
                dynamicTest("3rd dynamic test", () -> {
                    System.out.println(Thread.currentThread().getName()+" => 3rd dynamic test");
                })
        );
    }
}
