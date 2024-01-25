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
        registerFunction(new RuleFunction(funcName: funcName, target: target, methodName: methodName))
    }

    def registerFunction(RuleFunction ruleFunction) {
        RuleFunction.registerFunction(functions, ruleFunction)
    }

    def registerFunction(List<RuleFunction> functions) {
        functions.each {
            registerFunction(it)
        }
    }

    def callFunction(String funcName, Object[] args) {
        def rf = functions[funcName]
        if (!rf) {
            rf = RuleFunction.globalFunctions[funcName]
            if (!rf)
                throw new IllegalArgumentException("function $funcName not exists!")
        }
        def methodName = rf.methodName
        if (!methodName) {
            methodName = funcName
        }
        rf.target."$methodName"(*args)
    }
}
