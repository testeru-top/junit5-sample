package top.testeru.dynamic.test;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @Classname: DynamicNodeTest
 * @Description:
 * @Date: 2022/6/8 16:42
 * @Created by top.testeru
 */
public class DynamicNodeTest {
    //方法返回实例为DynamicTest
//    @TestFactory
//    @DisplayName("单个")
//    DynamicTest dynamicTest() {
//        return DynamicTest.dynamicTest("单个动态测试",
//                () -> assertEquals(5, 3+2));
//    }
//    //方法返回实例为DynamicContainer
//    @TestFactory
//    @DisplayName("容器")
//    DynamicContainer dynamicTestsForContainer() {
//        return DynamicContainer.dynamicContainer("多个动态测试",
//
//                Stream.of(
//                        DynamicTest.dynamicTest("1、动态容器",
//                                () -> assertEquals(5, 3+2)),
//                        DynamicTest.dynamicTest("2、动态容器",
//                                () -> assertEquals(5,25/5))
//                        ));
//    }
//
//
//    //动态测试用例
//    @TestFactory
//    @DisplayName("stream动态测试")
//    Stream<DynamicTest> dynamicTestFromStream() {
//
//        return Stream.of(6, 8, 10)
//                    .map(arg -> DynamicTest.dynamicTest("Dy test " + arg, () -> {
//                                    System.out.println(arg);//14 22 32
//                                    assertThat(arg, is(greaterThan(2)));
//                                }));
//    }
//    //动态测试用例
//    @TestFactory
//    @DisplayName("stream动态测试多个方法")
//    Stream<DynamicContainer> dynamicTestsFromStream() {
//        return Stream.of(6, 8, 10)
//                .map(arg -> DynamicContainer.dynamicContainer("Container "+arg,
//                        Stream.of(DynamicTest.dynamicTest("1.Dy test " + arg, () -> {
//                                        System.out.println(arg);//14 22 32
//                                        assertThat(arg, is(greaterThan(2)));
//                                    }),
//                                DynamicTest.dynamicTest("2.Dy test " + arg, () -> {
//                                    System.out.println(arg+1);//14 22 32
//                                    assertThat(arg+1, is(greaterThan(4)));
//                                })
//                        )));
//    }
//
//    //不能 static private 修饰
//    //返回值类型：Stream Collection Iterable Iterator DynamicNode
//    @TestFactory
//    Collection<DynamicTest> dynamicWithCollection(){
//        Collection<DynamicTest> dynamicTestCollection = new ArrayList<>();
//
//        DynamicTest dynamicTest1 = DynamicTest.dynamicTest("1.dynamic add test",
//                () -> System.out.println("只是打印加法"));
//        DynamicTest dynamicTest2 = DynamicTest.dynamicTest("2.dynamic sub test",
//                ()-> System.out.println("只是打印减法"));
//        dynamicTestCollection.add(dynamicTest1);
//        dynamicTestCollection.add(dynamicTest2);
//        return dynamicTestCollection;
//    }


    @TestFactory
    Iterator<DynamicTest> dynamicWithIterator(){

        Collection<DynamicTest> dynamicTestIterator = new ArrayList<>();
        PrimitiveIterator.OfInt iterator = IntStream.iterate(2, n -> n + 1).limit(3).iterator();

        while (iterator.hasNext()){
            Integer next = iterator.next();
            dynamicTestIterator.add(
                    DynamicTest.dynamicTest("加法测试",  () -> {
                        System.out.println("n:" + next);
                        assertThat(next,is(greaterThan( 1)));
                    }));
        }
        return dynamicTestIterator.iterator();
    }

    @TestFactory
    Iterable<DynamicTest> dynamicWithIterable(){
        Collection<DynamicTest> dynamicTestIterator = new ArrayList<>();
        PrimitiveIterator.OfInt iterator = IntStream.iterate(2, n -> n + 1).limit(3).iterator();

        while (iterator.hasNext()){
            Integer next = iterator.next();
            dynamicTestIterator.add(
                    DynamicTest.dynamicTest("加法Iterable测试",  () -> {
                        System.out.println("Iterable-n:" + next);
                        assertThat(next,is(greaterThan( 1)));
                    }));
        }
        return dynamicTestIterator;
    }
}
