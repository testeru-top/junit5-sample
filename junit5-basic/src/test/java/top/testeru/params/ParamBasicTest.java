package top.testeru.params;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import top.testeru.MySUT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: 参数化基本示例
 * 多个数相加 list<int> 转为int[]
 * 多个字符串拼接 list<String> 转为str[]
 * @Version 1.0
 * @create: 2022/6/21 11:44 AM
 */
public class ParamBasicTest {
    static MySUT sut ;


    @ParameterizedTest
    @MethodSource
    void moreParamsTest(List<Integer> add, int re){

        // list转int[]
        /**
         * List<Integer>集合转为Stream<Integer>流
         * Stream<Integer>流转为 IntStream
         * IntStream转为int[]
         * 因为int 不能new对象，所以不能直接toArray
         */
        int result = sut.sum(add.stream().mapToInt(Integer::intValue).toArray());

        //断言失败没有提示消息
        assertEquals(re,result,"sum结果错误");

    }
    static Stream<Arguments> moreParamsTest(){
        return Stream.of(
                Arguments.arguments(Arrays.asList(1,2),3),
                Arguments.arguments(Arrays.asList(32,65,89),99)
        );
    }
    @ParameterizedTest
    @MethodSource
    void moreParamsWithStrTest(List<String> strs, String re){
        // list转String[]
        Stream<String> stream = strs.stream();
        String[] strings = stream.toArray(String[]::new);
        /**
         * List<String>集合转为Stream<String>流
         * Stream<String>流转为 String[]
         */
        String s = sut.concatenate(strs.stream().toArray(String[]::new));
        //        断言失败没有提示消息
        assertEquals(re,s,"拼接str");

    }
    static Stream<Arguments> moreParamsWithStrTest(){
        return Stream.of(
                Arguments.arguments(Arrays.asList("Hello","JUnit5"),"Hello JUnit5"),
                Arguments.arguments(Arrays.asList("这里","是","我最爱的家"),"这里 是 我最爱的家")
        );
    }
    @BeforeAll
    static void beforeAll(){
        sut = new MySUT("params");
    }
}
