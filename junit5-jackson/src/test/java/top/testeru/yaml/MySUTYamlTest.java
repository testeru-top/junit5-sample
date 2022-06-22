package top.testeru.yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import top.testeru.MySUT;
import top.testeru.entity.Data;
import top.testeru.entity.Sum;
import top.testeru.params.SumAggregator;
import top.testeru.params.YamlToSum;
import top.testeru.params.SumAggregator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: yaml文件解析参数化
 * @Version 1.0
 * @create: 2022/6/20 3:10 PM
 */
public class MySUTYamlTest {
    static MySUT mySUT;
    @BeforeAll
    static void beforeAll(){
        mySUT = new MySUT("yaml Calculator");
    }
    @DisplayName("yaml文件解析为对象")
    @ParameterizedTest
    @MethodSource("sumData")
    void sumTest(Data data){
        int result = mySUT.sum(data.getA(), data.getB());
        //hamcrest断言数字计算
        assertThat(data.getMessage(),result, equalTo(data.getResult()));
    }
    //参数化对象
    static Stream<Data> sumData(){
        return getSumData().getDatas().stream();
    }


    //方法的参数为多个对应变量
    @DisplayName("yaml文件解析为对应变量")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("sumArgData")
    void sumArgTest(int a, int b, int re, String message){
        int result = mySUT.sum(a, b);
        //hamcrest断言数字计算
        assertThat(message, result, equalTo(re));
    }


    //    方法参数为对象
    @DisplayName("方法参数为对象")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("sumArgData")
    void sumArgTest(@AggregateWith(SumAggregator.class)Data data){
        int result = mySUT.sum(data.getA(), data.getB());
        //hamcrest断言数字计算
        assertThat(data.getMessage(),result, equalTo(data.getResult()));
    }

    //自定义注解优化
    @DisplayName("方法参数为对象-优化")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("sumArgData")
    void sumArgOptimalTest(@YamlToSum Data data){
        int result = mySUT.sum(data.getA(), data.getB());
        //hamcrest断言数字计算
        assertThat(data.getMessage(),result, equalTo(data.getResult()));
    }

    //参数化Arguments
    static Collection<Arguments> sumArgData(){
        List<Data> dataList = getSumData().getDatas();
        Collection<Arguments> argumentsCollection = new ArrayList<>();
        dataList.forEach(data -> {
            Arguments arguments = Arguments.arguments(
                                            data.getA(), data.getB(), data.getResult(), data.getMessage()
                                    );
            argumentsCollection.add(arguments);
        });
        return argumentsCollection;

    }



    private static Sum getSumData() {
        Sum sum = null;
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<Sum> typeReference = new TypeReference<>() {
            };
            sum = mapper.readValue(new File("src/test/resources/yaml/sum.yaml"), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }


}