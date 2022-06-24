package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.ParameterizedTest.*;

/**
 * 1.创建计算器对象
 * 2.生成测试用例ID
 * 3.测试方法之前打印：开始进行加法测试操作
 * 4.编写测试业务逻辑
 * 5.测试后打印：计算结果result
 * 6.对测试结果进行断言
 * 7.销毁ID
 * 改造，添加beforeall afterall beforeEach afterEach
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
class MySUTBase04Test extends BasicTest{

    //自定义参数化用例显示名 {}代表引用
    //[{index}] index从1开始，执行参数化测试用例的索引 [1] [2] [3]
    //{0} {1} {2} 就是arguments的数组list的下标
//    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @ParameterizedTest(name = ARGUMENTS_WITH_NAMES_PLACEHOLDER) //a=11,b==11,re=22
//    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)  //11,11,22
    @MethodSource("add2Stream")
//    @CsvSource({"1, 1, 2", "99, 99, 198", "3,8,11" })
    @DisplayName("2个数加法19")
    @Order(4)
    void add1Test(int a, int b, int re){
        //4.业务逻辑 test case 编写
        result = mySUT.sum(a, b);
        //6.对测试结果进行断言
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(re,result, "The result of adding two numbers is wrong");
        //hamcrest断言
        assertThat( "The result of adding two numbers is wrong",result , is(equalTo(re)));
    }
    static Stream<Arguments> add1Stream(){
        return Stream.of(
                Arguments.arguments(1, 1, 2),
                Arguments.arguments(99, 99, 198),
                Arguments.arguments(5,8,13)
        );
    }
    static Stream<Arguments> add2Stream(){
        return Stream.of(
                Arguments.arguments(11, 11, 22),
                Arguments.arguments(90, 90, 180),
                Arguments.arguments(3,8,11)
        );
    }

    @Test
    @DisplayName("2个数加法55")
    void add2Test(){
        //4.业务逻辑 test case 编写
        result = mySUT.sum(1, 1,9);
        //6.对测试结果进行断言
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(11,result,"The result of adding two numbers is wrong");
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",result , is(equalTo(11)));
    }
    @Test
    @DisplayName("2个数加法11")
    void add3Test(){
        //抛出异常断言
        //Class<T> expectedType：抛出的异常类型，必须是.class结尾 T是泛型
        //Executable executable：业务逻辑流
        //String message：异常失败的显示原因
        //如果没有抛出异常，或者抛出了不同类型的异常，则此方法将失败
        //抛出指定类型异常(抛出的异常是指定异常的子类型即可)
        //Exception exception = assertThrows(RuntimeException.class, () -> mySUT.sum(9, 100));
        //抛出指定类型异常(抛出的异常和指定异常的class类型必须完全相同)
        Exception exception = assertThrowsExactly(NumberFormatException.class, () -> mySUT.sum(9, 100));

        assertTrue(exception.getMessage().contains("integer is 100"));
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",exception.getMessage() , is(containsString("integer is 100")));
    }

    @BeforeAll
    static void ba(){
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("MySUTBase03Test beforeAll");
    }

    @BeforeEach
    void be(){

        logger.info("MySUTBase03Test beforeEach");
    }

    @AfterEach
    void ae(){
        logger.info("MySUTBase03Test afterEach");
    }

    @AfterAll
    static void aa(){
        logger.info("MySUTBase03Test afterAll");
    }

}

