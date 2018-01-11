package com.good.rubbish;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String expression = "a-(b-c)";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 102.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Double result = (Double) compiledExp.execute(env);
        System.out.println(result);  // false
    }

}
