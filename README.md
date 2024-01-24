# rule-engine

规则引擎实现

### 规则语法

```groovy

/*
 * rule 规则可以写多个 rule('a'){}  rule('b'){} ...
 * ruleName 单引号包裹的字符串 例如：'this is a rule name'
 * if 判断可以写多个 if(age > 18){} if(age > 20){} ...
 * condition 判断条件
 *      比较操作符：  >,>=,<,<=,==,!= 分别对应 大于，大于等于，小于，小于等于，等于，不等于
 *      逻辑操作符： &&,||,and,or 分别对应 逻辑与，逻辑或，逻辑与，逻辑或
 *      括号内优先级最高
 *      例如：age > 18
 *           age >= 18
 *           age < 18
 *           age <= 18
 *           age == 18
 *           age != 18
 *           age >= 18 && age <20
 *           age >= 18 || message == '已成年'
 *           (age < 18 && message == '未成年') || (age >= 65 && message == '已退休')
 * 赋值操作 field = value
 * field 是对象的属性字段
 * value 值的形式以下几种
 *      字符串         'this is value string'
 *      整数和浮点数    1, 1.1
 *      布尔值         true, false
 *      计算值         3 * 4, field + value, field - value, field * value, field / value
 *      例如：
 *           age = 2 * 9
 *           age = age + 18
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
ctx.put('zs', zs)
engine.applyRule('age', ctx)
println(zs)  //-----User(0, 未成年)
```
