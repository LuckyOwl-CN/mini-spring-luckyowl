package com.luckyowl.test.aop.aspectj;

import com.luckyowl.test.aop.ClassFilter;
import com.luckyowl.test.aop.MethodMatcher;
import com.luckyowl.test.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/24
 **/
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    /**
     * 支持的原语：存储AspectJ表达式中支持的元素类型
     */
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    /**
     * 初始化原语类型，支持execution：方法执行的切点类型
     */
    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    /**
     * 切点表达式
     */
    private final PointcutExpression pointcutExpression;

    /**
     * 接受一个表达式字符串作为参数，通过PointcutParser类解析表达式
     * @param expression
     */
    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> targerClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
