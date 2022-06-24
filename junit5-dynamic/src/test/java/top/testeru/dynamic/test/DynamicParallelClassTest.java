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
@Execution(ExecutionMode.CONCURRENT)
public class DynamicParallelClassTest {
    //动态测试方法指定多线程执行
//    @Execution(ExecutionMode.CONCURRENT)
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
    //动态测试方法指定主线程执行
    @Execution(ExecutionMode.SAME_THREAD)
    @TestFactory
    Collection<DynamicTest> mainTest() {
        return Arrays.asList(
                dynamicTest("1st", () -> {
                    System.out.println(Thread.currentThread().getName()+" => 1st");
                }),
                dynamicTest("2nd", () -> {
                    System.out.println(Thread.currentThread().getName()+" => 2nd");
                }),
                dynamicTest("3rd", () -> {
                    System.out.println(Thread.currentThread().getName()+" => 3rd");
                })
        );
    }

}
