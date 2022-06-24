package top.testeru.num;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import top.testeru.basic.BasicTest;
import top.testeru.entity.SumData;
import top.testeru.self.ArgToSumData;
import top.testeru.self.ListAggregator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_WITH_NAMES_PLACEHOLDER;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("加法测试用例")
public class MySUTAddProTest extends BasicTest {

    @ParameterizedTest(name = ARGUMENTS_WITH_NAMES_PLACEHOLDER) //a=11,b==11,re=22
    @MethodSource("add1Stream")
    @DisplayName("add test example")
    @Order(4)
    void addPro1Test(List<Integer> add, int re){
        // list转int[]
        /**
         * List<Integer>集合转为Stream<Integer>流
         * Stream<Integer>流转为 IntStream
         * IntStream转为int[]
         * 因为int 不能new对象，所以不能直接toArray
         */
        result = mySUT.sum(add.stream().mapToInt(Integer::intValue).toArray());
        //断言失败带提示消息
        assertEquals(re,result,"The result of adding numbers is wrong");
    }
    static Stream<Arguments> add1Stream(){
        return Stream.of(
                Arguments.arguments(Arrays.asList(11,2),13),
                Arguments.arguments(Arrays.asList(32,65,89),99)
        );
    }



    @ParameterizedTest
    @MethodSource("add2Stream")
    @DisplayName("add throw test example")
    @Order(6)
    void addPro2Test(List<Integer> add){
        //抛出异常断言
        Exception exception = assertThrowsExactly(
                                NumberFormatException.class,
                                () -> mySUT.sum(add.stream().mapToInt(Integer::intValue).toArray())
                                );

        assertTrue(exception.getMessage().contains("integer is 100"));
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",exception.getMessage() ,
                is(containsString("integer is 100")));
    }
    static Stream<Arguments> add2Stream(){
        return Stream.of(
                Arguments.arguments(Arrays.asList(9,100)),
                Arguments.arguments(Arrays.asList(100,2,89))
        );
    }


    /**
     * @AggregateWith： 允许指定 ArgumentsAggregator；
     * 用于@ParameterizedTest 的参数，调用测试方法时为带注释的参数解析聚合值
     * ArgumentsAggregator 通过 @AggregateWith 注释应用于 @ParameterizedTest 方法的方法参数
     * 聚合的结果将作为参数传递给带注释参数的 @ParameterizedTest 方法。
     * @param sumData
     */
    @ParameterizedTest
    @MethodSource("add3Stream")
    @DisplayName("Para-add test example")
    @Order(8)
    void addPro3Test(@AggregateWith(ListAggregator.class)SumData sumData){
        result = mySUT.sum(sumData.getAdd().stream().mapToInt(Integer::intValue).toArray());
        //断言失败带提示消息
        assertEquals(sumData.getRe(),result,"The result of adding numbers is wrong");
    }
    static Stream<Arguments> add3Stream(){
        return Stream.of(
                Arguments.arguments(11,2,13),
                Arguments.arguments(32,65,89,99)
        );
    }
    @ParameterizedTest
    @MethodSource("add3Stream")
    @DisplayName("Para-add test example")
    @Order(10)
    void addPro4Test(@ArgToSumData SumData sumData){
        result = mySUT.sum(sumData.getAdd().stream().mapToInt(Integer::intValue).toArray());
        //断言失败带提示消息
        assertAll(
                //junit5 断言
                () -> assertEquals(2,result,"1+1结果错误"),
                //hamcrest断言
                () -> assertThat("1+1结果错误",result,is(equalTo(2))),
                //assertj断言
                () -> assertThat(result).isGreaterThan(1).isLessThan(3)
        );
    }

}

