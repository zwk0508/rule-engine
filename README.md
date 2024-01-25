# rule-engine

规则引擎实现

### 规则语法

```groovy

/*
 * rule 规则可以写多个 rule('a'){}  rule('b'){} ...
 * ruleName 单引号包裹的字符串 例如：'this is a rule name'
 * if 判断可以写多个 if(age > 18){} if(age > 20){} ...
 * condition 判断条件 格式：field op value logic_op field op value ...
 *      op 比较操作符：  >,>=,<,<=,==,!= 分别对应 大于，大于等于，小于，小于等于，等于，不等于
 *      logic_op 逻辑操作符： &&,||,and,or 分别对应 逻辑与，逻辑或，逻辑与，逻辑或
 *      使用括号内来调整优先级
 * 赋值操作 field = value
 * 
 * field 是对象的属性字段
 * value 值的形式以下几种 可以使用括号内来调整优先级
 *      字符串         'this is value string'
 *      整数和浮点数    1, 1.1
 *      布尔值         true, false
 *      空值           null
 *      计算值         3 * 4, field + value, field - value, field * value, field / value
 *      函数           pow(3,2),toUpperCase('zs'),isTeenager(zs.age)..
 *      例如：
 *           age = 2 * (3 + 3)
 *           age = age + pow(3,2)
 *           age = zs.age + 3
 *           female = true
 *           zs.message = '未成年'
 * 
 */
rule(ruleName) {
    if (condition) {
        field = value
    }
}
```

### 使用方式

```groovy
import groovy.transform.Canonical

@Canonical
class User {
    def age
    def message
}
```

###### pojo对象

```groovy
import com.zwk.RuleEngine

def engine = new RuleEngine()
engine.parseRule("rule('age'){ if (age < 18){message = '未成年'}}")

def user = new User(age: 17)
engine.applyRule('age', user)
println(user) //----->User(17, 未成年)
```

###### 数组或集合对象

```groovy
import com.zwk.RuleEngine

def engine = new RuleEngine()
engine.parseRule("rule('age'){ if (age < 18){message = '未成年'} if (age >=18){message = '已成年'}}")

def user = new User(age: 17)
def user1 = new User(age: 19)
engine.applyRule('age', [user, user1])
println(user)  //----->User(17, 未成年)
println(user1)  //----->User(19, 已成年)
```

###### 添加额外变量

```groovy
import com.zwk.RuleEngine

def engine = new RuleEngine()
engine.parseRule("rule('age'){ if (age < 18){zs.message = '未成年'}}")

def user = new User(age: 17)
def zs = new User(age: 0)
def ctx = new RuleContext(root: user)
//添加额外变量
ctx.addVariable('zs', zs)
engine.applyRule('age', ctx)
println(zs)  //-----User(0, 未成年)
```

###### 注册函数

```groovy
import com.zwk.RuleEngine

def engine = new RuleEngine()
engine.parseRule("rule('age'){ if (age >= pow(10,2)){message = '长寿' age = getAge() + 1}}")

def user = new User(age: 100)
def ctx = new RuleContext(root: user)
//注册静态
ctx.registerFunction('pow', Math)
//注册实例
ctx.registerFunction('getAge', user)

engine.applyRule('age', ctx)
println(user)  //-----User(101, 长寿)
```
