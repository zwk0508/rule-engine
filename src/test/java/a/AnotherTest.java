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
        engine.parseRule("rule('0-18'){ if (age < 18){zs.message = '未成年' zs.age = 13}}\nrule('18-30'){if (age >= 18 and age < 30){zs.message = '青年'}}");

        SimpleTest.User user = new SimpleTest.User();
        user.setAge(17);
        SimpleTest.User zs = new SimpleTest.User();
        RuleContext ctx = new RuleContext();
        ctx.setRoot(user);
        ctx.put("zs", zs);

        engine.applyRule("0-18", ctx);

        System.out.println(zs);
        user.setAge(19);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            engine.applyRule("18-30", ctx);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(zs);
    }
}
