package top.testeru.dynamic.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @Classname: DynamicOrderTest
 * @Description: 动态测试执行顺序
 * @Date: 2022/6/8 19:04
 * @Created by top.testeru
 */

public class DynamicOrderTest {
    @TestFactory
    @DisplayName("自定义动态测试排序")
    Collection<DynamicTest> testDynamicTestsOrder() {

        List<DynamicTest> dynamicTests = Arrays.asList(
                dynamicTest("1st dynamic test", () -> {
                    System.out.println("=> 1st dynamic test");
                }),
                dynamicTest("2nd dynamic test", () -> {
                    System.out.println("=> 2nd dynamic test");
                }),
                dynamicTest("3rd dynamic test", () -> {
                    System.out.println("=> 3rd dynamic test");
                })
        );

        sortDynamicTests(dynamicTests);
        return dynamicTests;
    }

    static void sortDynamicTests(List<DynamicTest> dynamicTests) {
        dynamicTests.sort((DynamicTest d1, DynamicTest d2) ->
                d2.getDisplayName().compareTo(d1.getDisplayName()));
    }
}
