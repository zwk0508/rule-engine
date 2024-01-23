package a

import com.zwk.RuleContext
import com.zwk.RuleEngine
import groovy.transform.Canonical

class SimpleTest {
    @Canonical
    static class User {
        def age
        def message
    }

    static void main(String[] args) {

        def engine = new RuleEngine()
        engine.parseRule("rule('0-18'){ if (age < 18){zs.message = '未成年'  zs.age = 13}}\nrule('18-30'){if (age >= 18 and age < 30){zs.message = '青年'}}")

        def user = new User(age: 17)
        def zs = new User()
        def ctx = new RuleContext(root: user)
        ctx.put('zs', zs)


        engine.applyRule('0-18', ctx)
        println(zs)
        user.age = 19
        engine.applyRule('18-30', ctx)
        println(zs)
    }
}
