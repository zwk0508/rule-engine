package a;

import com.zwk.RuleContext;
import com.zwk.RuleEngine;

/**
 * @author zwk
 * @version 1.0
 * @date 2024/1/23 17:36
 */

public class AnotherTest {
    public static void main(String[] args) throws Exception {
        RuleEngine engine = new RuleEngine();
        engine.parseRule("rule('age'){ if (age < 18){zs.message = '未成年' zs.age = age+13} if ((age >= 18 and age < 30) || (age >= 18 and age < 30)){zs.message = '青年' zs.age = zs.age+20}}");

        SimpleTest.User user = new SimpleTest.User();
        user.setAge(17);
        SimpleTest.User zs = new SimpleTest.User();
        RuleContext ctx = new RuleContext();
        ctx.setRoot(user);
        ctx.put("zs", zs);

        engine.applyRule("age", ctx);

        System.out.println(zs);
        user.setAge(19);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            engine.applyRule("age", ctx);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(zs);
    }
}
