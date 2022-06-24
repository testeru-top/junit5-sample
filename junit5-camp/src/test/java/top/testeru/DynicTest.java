/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */
package top.testeru;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import top.testeru.basic.BasicTest;
import top.testeru.entity.ResultList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;
import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/24 17:01
 */
@Execution(ExecutionMode.CONCURRENT)
public class DynicTest {
    public static final Logger logger = getLogger(lookup().lookupClass());
    public static long l;
    @BeforeAll
    static void beforeAll(){
        l = System.currentTimeMillis();
    }
    @AfterAll
    static void afterAll(){
        logger.info("时间："+(System.currentTimeMillis() - l));
    }
    @TestFactory
    @DisplayName("动态测试")
//    @Execution(ExecutionMode.CONCURRENT)
    Collection<DynamicTest> test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ResultList resultList = objectMapper.readValue(new File("src/test/resources/yaml/shell_test_result.yaml"), ResultList.class);
        Collection<DynamicTest> list = new ArrayList<>();
        resultList.getResultList().forEach(
                shellResult -> {
                     list.add(DynamicTest.dynamicTest(
                                        shellResult.getCaseName(),
                                        () -> {
                                            logger.info(shellResult.getCaseName());
                                            sleep(2000);
                                            assertTrue(shellResult.getResult());
                                        })
                    );
                }
        );
        return list;
    }
}
