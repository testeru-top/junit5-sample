package top.testeru.basic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.provider.Arguments;
import top.testeru.entity.Data;
import top.testeru.entity.Sum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/24 11:11
 */
public class ParamArguments {
    public static Stream<Data> sumData(){
        return getSumData().getDatas().stream();
    }


    //参数化Arguments
    public static Collection<Arguments> sumArgData(){
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
