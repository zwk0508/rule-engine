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
        engine.parseRule("rule('age'){ if (age >= pow(10,2)){message = '长寿' age = getAge() + 1}}")

        def user = new User(age: 100)
        def ctx = new RuleContext(root: user)
        //注册静态
        ctx.registerFunction('pow', Math)
        //注册实例
        ctx.registerFunction('getAge', user)

        engine.applyRule('age', ctx)
        println(user)

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
