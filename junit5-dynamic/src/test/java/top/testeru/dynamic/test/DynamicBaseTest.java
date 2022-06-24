package top.testeru.dynamic.test;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @Classname: DynamicBaseTest
 * @Description:
 * @Date: 2022/6/8 16:13
 * @Created by top.testeru
 */
public class DynamicBaseTest {

    // Static test 1
    @Test
    void test_Add() {
        assertEquals(5, 3+2);
    }

    // This method produces Dynamic test cases
    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {

        return Arrays.asList(
                dynamicTest("1stDynamic", () -> assertEquals(5, 3+2)),
                dynamicTest("2ndDynamic", () -> assertEquals(5, 25/5))
        );
    }
    // Static test 2
    @Test
    void test_Devide() {
        assertEquals(5,25/5);
    }
}
