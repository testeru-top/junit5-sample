package top.testeru.num;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import top.testeru.basic.BasicTest;
import top.testeru.entity.Data;
import top.testeru.entity.Sum;
import top.testeru.entity.SumData;
import top.testeru.self.ArgToSumData;
import top.testeru.self.ListAggregator;
import top.testeru.self.SumAggregator;
import top.testeru.self.YamlToSum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_WITH_NAMES_PLACEHOLDER;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("add With Yaml Test")
public class MySUTAddYamlTest extends BasicTest {

    @Test
    void yamlTest(){
        Sum sum = null;
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<Sum> typeReference =
                    new TypeReference<>() {
            };
            sum = mapper.readValue(new File("src/test/resources/yaml/sum.yaml"), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }
    @DisplayName("yaml数据驱动")
    @ParameterizedTest
    @MethodSource("top.testeru.basic.ParamArguments#sumData")
    void sumTest(Data data){
        int result = mySUT.sum(data.getA(), data.getB());
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





    //方法的参数为多个对应变量
    @DisplayName("yaml文件解析为对应变量")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("top.testeru.basic.ParamArguments#sumArgData")
    void sumArgTest(int a, int b, int re, String message){
        int result = mySUT.sum(a, b);
        //hamcrest断言数字计算
        assertThat(message, result, equalTo(re));
    }
    //方法的参数为多个对应变量
    @DisplayName("yaml文件解析为对应变量")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("top.testeru.basic.ParamArguments#sumArgData")
    void sumArgAllTest(int a, int b, int re, String message){
        if(message.startsWith("无效")){
            Exception exception = assertThrowsExactly(
                    IllegalArgumentException.class, () -> mySUT.sum(a, b));
            //hamcrest断言
            assertThat("The result of adding two numbers is wrong",
                    exception.getMessage() ,
                    Matchers.is(containsString("Please enter an integer in the range")));

        }else {
            result = mySUT.sum(a, b);
            //hamcrest断言数字计算
            assertThat(message, result, equalTo(re));
        }
    }

    //    方法参数为对象
    @DisplayName("方法参数为对象")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("top.testeru.basic.ParamArguments#sumArgData")
    void sumArgTest(@AggregateWith(SumAggregator.class)Data data){
        int result = mySUT.sum(data.getA(), data.getB());
        //hamcrest断言数字计算
        assertThat(data.getMessage(),result, equalTo(data.getResult()));
    }

    //自定义注解优化
    @DisplayName("方法参数为对象-优化")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("top.testeru.basic.ParamArguments#sumArgData")
    void sumArgOptimalTest(@YamlToSum Data data){
        int result = mySUT.sum(data.getA(), data.getB());
        //hamcrest断言数字计算
        assertThat(data.getMessage(),result, equalTo(data.getResult()));
    }




}

