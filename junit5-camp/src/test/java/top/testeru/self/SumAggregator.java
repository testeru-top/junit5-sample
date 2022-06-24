/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */
package top.testeru.self;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import top.testeru.entity.Data;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/23 11:47
 */
public class SumAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        int index = context.getIndex();
        Data data = new Data();
        data.setA(accessor.getInteger(index));
        data.setB(accessor.getInteger(index + 1));
        data.setResult(accessor.getInteger(index + 2));
        data.setMessage(accessor.getString(index + 3));
        return data;
    }
}
