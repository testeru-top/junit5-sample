package top.testeru.self;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import top.testeru.entity.SumData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/22 16:54
 */

public class ListAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
//        int index = context.getIndex();
//        System.out.println("index:"+index);
        int size = accessor.size();//list集合的长度
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < size-1; i++) {
            integers.add(accessor.getInteger(/*index +*/ i));
        }
        System.out.println(integers);
        return new SumData(integers,accessor.getInteger(size - 1));
    }
}
