package top.testeru.yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Test;
import top.testeru.BaseTest;

import top.testeru.entity.Sum;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: 解析文件测试
 * @Version 1.0
 * @create: 2022/6/20 3:14 PM
 */
public class JacksonYamlTest extends BaseTest {

    //解析业务sum yaml文件
    @Test
    public void yamlTest(){

        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<Sum> typeReference = new TypeReference<Sum>() {
            };
            Sum sum = mapper.readValue(new File("src/test/resources/yaml/sum.yaml"), typeReference);
            log.info("数据解析结果为:{}",sum);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
