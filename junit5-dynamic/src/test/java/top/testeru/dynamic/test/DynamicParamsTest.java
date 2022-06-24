package top.testeru.dynamic.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @Classname: DynamicBaseTest
 * @Description: 动态测试参数化
 * 动态生命周期
 * 一个动态测试用例，只运行一次BeforeEach，
 * 相当于Factory动态生成测试用例，为一个测试工厂方法内生成
 * @Date: 2022/6/8 14:57
 * @Created by top.testeru
 */
public class DynamicParamsTest {
    static int m = 0;
    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll");
    }
    @BeforeEach
    void beforeEach(){
        System.out.println("BeforeEach:"+m);//0 9 9
        m=8;
    }
    @AfterEach
    void afterEach(){
        m=9;
    }
    @AfterAll
    static void afterAll(){System.out.println("AfterAll");}




    @ParameterizedTest
    @MethodSource("argstream")
    void staticTest(int arg) {
        System.out.println("m:"+m);//8  8  8
        m +=arg;
        System.out.println(m);//14 16 18
        assertThat(arg, is(greaterThan(2)));
    }

    static Stream argstream() {
        return Stream.of(6, 8, 10);
    }


    /**
     * 只打印一次beforeeach m为8
     * 动态生成测试用例
     * @return
     */
    @TestFactory
    Stream<DynamicTest> dyTestFactory(){

        return Stream.of(6, 8, 10)
                .map(arg -> dynamicTest("test" + arg, () -> {
                    System.out.println("m:"+m);//8  14  22
                    m +=arg;
                    System.out.println(m);//14 22 32
                    assertThat(arg, is(greaterThan(2)));
                }));

    }
    @TestFactory
    Collection<DynamicTest> dynamicTestsFromStreamInJava8() {
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        Arrays.asList("154.174.10.56", "211.152.104.132", "178.144.120.156")
                .forEach(s -> {
                    //每一个参数进行动态测试用例
                    DynamicTest dynamicTest = DynamicTest.dynamicTest("1、集合参数化", () -> {
                        System.out.println("1、集合参数化：" + s);
                    });

                    DynamicTest dynamicTest1 = DynamicTest.dynamicTest("2、集合参数化", () -> {
                        System.out.println("2、ip参数化：--" + s);
                    });
                    dynamicTests.add(dynamicTest);
                    dynamicTests.add(dynamicTest1);
                });

        return dynamicTests;
    }
    @TestFactory
    Collection<DynamicTest> dyTestCollection(){
        Collection<DynamicTest> dynamicTests = new ArrayList<DynamicTest>();
        Arrays.asList(6, 8, 10)
                .forEach(arg ->{

                            dynamicTests.add(
                                    dynamicTest("test" + arg,
                                            () -> {
                                                System.out.println("m:"+m);//8  14  22
                                                m +=arg;
                                                System.out.println("m+arg:"+m);//8  14  22
                                                assertThat(arg, is(greaterThan(2)));
                                            }
                                    ));
                            dynamicTests.add(
                                    dynamicTest("test-" + arg,
                                            () ->{
                                                System.out.println("m-:"+m);//8  14  22
                                                m -=arg;
                                                System.out.println("m-arg:"+m);//8  14  22
                                                assertThat(arg, is(greaterThan(2)));
                                            }
                                    ));
                            });

        return dynamicTests;
    }

}
