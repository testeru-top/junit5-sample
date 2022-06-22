package top.testeru.yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.*;
import top.testeru.BaseTest;
import top.testeru.entity.OrderData;
import top.testeru.entity.OrderListData;
import top.testeru.entity.Sum;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: 解析文件测试-order.yaml解析
 * @Version 1.0
 * @create: 2022/6/20 3:14 PM
 */
@DisplayName(value = "order.yaml解析")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderYamlTest extends BaseTest {

    //直接声明类型解析
    @DisplayName("直接声明类型")
    @Test
    @Order(1)
    public void listAndMapTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String, Object>>> typeReference =
                new TypeReference<List<HashMap<String, Object>>>() {};
        List<HashMap<String, Object>> hashMaps = mapper.readValue(
                new File("src/test/resources/yaml/order.yaml"), typeReference);
        System.out.println(hashMaps);
    }

    //声明实体类解析

    /**
     * 实体类的成员变量与yaml文件的key一致
     * dataTime报错：添加jackson-datatype-jsr310
     * @throws IOException
     */

    @DisplayName("实体类解析-成员变量与key一致")
    @Test
    @Order(2)
    public void orderSameTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
        mapper.findAndRegisterModules();
        TypeReference<List<OrderData>> typeReference = new TypeReference<List<OrderData>>() {
        };
        List<OrderData> orderLines = mapper.readValue(new File("src/test/resources/yaml/order.yaml"), typeReference);
        System.out.println(orderLines);
    }

    /**
     * 实体类的成员变量与yaml文件key不一致
     * 需要在实体类的成员变量上声明@JsonProperty("")
     * @throws IOException
     */
    @DisplayName("实体类解析-成员变量与key不一致")
    @Test
    @Order(3)
    public void orderListTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
        mapper.findAndRegisterModules();
        TypeReference<List<OrderListData>> typeReference = new TypeReference<List<OrderListData>>() {
        };
        List<OrderListData> orderLists = mapper.readValue(new File("src/test/resources/yaml/order.yaml"), typeReference);
        System.out.println(orderLists);
    }


}
