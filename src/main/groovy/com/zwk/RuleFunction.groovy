package com.zwk

class RuleFunction {
    static final def globalFunctions = [:]

    def funcName
    def target
    def methodName


    static def registerGlobalFunction(String funcName, Object target, String methodName = funcName) {
        registerGlobalFunction(new RuleFunction(funcName: funcName, target: target, methodName: methodName))
    }

    static def registerGlobalFunction(RuleFunction ruleFunction) {
        registerFunction(globalFunctions, ruleFunction)
    }

    static def registerGlobalFunction(List<RuleFunction> functions) {
        functions.each {
            registerGlobalFunction(it)
        }
    }

    static def registerFunction(Map functions, RuleFunction ruleFunction) {
        Objects.requireNonNull(ruleFunction, "rule function cannot be null")
        Objects.requireNonNull(ruleFunction.funcName, "rule function funcName cannot be null")
        Objects.requireNonNull(ruleFunction.target, "rule function target cannot be null")
        if (functions.containsKey(ruleFunction.funcName)) {
            throw new IllegalArgumentException("function ${ruleFunction.funcName} already exists!")
        }
        functions[ruleFunction.funcName] = ruleFunction
    }
}
