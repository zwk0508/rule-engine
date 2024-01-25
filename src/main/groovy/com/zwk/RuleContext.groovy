package com.zwk

class RuleContext {
    def root

    def bindings = [:]

    def functions = [:]


    void put(String key, obj) {
        bindings[key] = obj
    }

    void putAll(Map map) {
        bindings.putAll(map)
    }

    void setRoot(root) {
        this.root = root
    }

    def registerFunction(String funcName, Object target, String methodName = funcName) {
        Objects.requireNonNull(funcName, "funcName cannot be null")
        Objects.requireNonNull(target, "target cannot be null")
        if (functions.containsKey(funcName)) {
            throw new IllegalArgumentException("function ${funcName} already exists!")
        }
        functions[funcName] = new RuleFunction(funcName: funcName, target: target, methodName: methodName)
    }

    def registerFunction(RuleFunction ruleFunction) {
        Objects.requireNonNull(ruleFunction, "rule function cannot be null")
        Objects.requireNonNull(ruleFunction.funcName, "rule function funcName cannot be null")
        Objects.requireNonNull(ruleFunction.target, "rule function target cannot be null")
        if (functions.containsKey(ruleFunction.funcName)) {
            throw new IllegalArgumentException("function ${ruleFunction.funcName} already exists!")
        }
        functions[ruleFunction.funcName] = ruleFunction
    }

    def registerFunction(Map<String, RuleFunction> functions) {
        def thiz = this
        functions.each { k, v ->
            if (thiz.functions.containsKey(k)) {
                throw new IllegalArgumentException("function ${k} already exists!")
            }
            thiz[k] = v
        }
    }

    def callFunction(String funcName, Object[] args) {
        def rf = functions[funcName]
        if (rf == null) {
            throw new IllegalArgumentException("function $funcName not exists!")
        }
        def methodName = rf.methodName
        if (!methodName) {
            methodName = funcName
        }
        rf.target."$methodName"(*args)
    }
}
