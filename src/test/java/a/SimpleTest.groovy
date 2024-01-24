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
        engine.parseRule("rule('age'){ if (age < 18){zs.message = '未成年'}}")

        def user = new User(age: 17)
        def zs = new User(age: 0)
        def ctx = new RuleContext(root: user)
        ctx.put('zs', zs)
        engine.applyRule('age', ctx)
        println(zs)

//        def user1 = new User(age: 19)
//        engine.applyRule('age', [user, user1])
//        println(user)
//        println(user1)
//        def zs = new User()
//        def ctx = new RuleContext(root: user)
//        ctx.put('zs', zs)
//
//
//        engine.applyRule('0-18', ctx)
//        println(zs)
//        user.age = 19
//        engine.applyRule('18-30', ctx)
//        println(zs)
    }
}
