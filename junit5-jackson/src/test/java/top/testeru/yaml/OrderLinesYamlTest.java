package top.testeru.yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import top.testeru.MySUT;
import top.testeru.entity.OrderLinesData;
import top.testeru.entity.OrderLinesListData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: orderlines.yaml解析
 * @Version 1.0
 * @create: 2022/6/20 5:56 PM
 */
public class OrderLinesYamlTest {
    //直接声明类型解析 HashMap<String,List<HashMap<String, Object>>>
    @DisplayName("直接声明类型")
    @Test
    @Order(1)
    void mapAndListAndMapTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, List<HashMap<String, Object>>>> typeReference =
                new TypeReference<HashMap<String,List<HashMap<String, Object>>>>() {};
        HashMap<String,List<HashMap<String, Object>>> hashMaps = mapper.readValue(
                new File("src/test/resources/yaml/orderlines.yaml"), typeReference);
        System.out.println(hashMaps);
    }


    /**
     * 实体类的成员变量与yaml文件的key一致
     * @throws IOException
     */
    @DisplayName("实体类解析-成员变量与key一致")
    @Test
    @Order(2)
    public void orderLinesDataTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
        mapper.findAndRegisterModules();
        TypeReference<OrderLinesData> typeReference = new TypeReference<OrderLinesData>() {
        };
        OrderLinesData orderLists = mapper.readValue(new File("src/test/resources/yaml/orderlines.yaml"), typeReference);
        System.out.println(orderLists);
    }


    @Test
    public void orderLinesListDataTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
        mapper.findAndRegisterModules();
        TypeReference<OrderLinesListData> typeReference = new TypeReference<OrderLinesListData>() {
        };
        OrderLinesListData orderLists = mapper.readValue(new File("src/test/resources/yaml/orderlines.yaml"), typeReference);
        System.out.println(orderLists);
    }

}
