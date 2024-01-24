package com.zwk

import com.zwk.parser.RuleContainer
import com.zwk.parser.RuleParser

/**
 * @author zwk
 * @version 1.0
 * @date 2024/1/19 15:26
 */

class RuleEngine {
    final rc = new RuleContainer()
    final parser = new RuleParser(rc: rc)

    def parseRule(String rule) {
        parser.parse(rule)
    }

    def parseRule(InputStream inputStream) {
        parseRule(inputStream.text)
    }

    def parseRule(File file) {
        parseRule(file.text)
    }

    def applyRule(String ruleName, obj) {
        Objects.requireNonNull(ruleName, 'rule name cannot be null')
        Objects.requireNonNull(obj, 'rule obj cannot be null')
        def rule = rc.getRule(ruleName)
        if (isIterable(obj)) {
            for (o in obj) {
                rule(getRuleContext(o))
            }
        } else {
            rule(getRuleContext(obj))
        }
    }

    def getRuleContext(obj) {
        if (obj instanceof RuleContext) {
            return obj
        }

        def ctx = new RuleContext()
        ctx.setRoot(obj)
        ctx
    }

    def isIterable(obj) {
        def clazz = obj.getClass()
        clazz.isArray() || Iterable.isAssignableFrom(clazz)
    }

    def hasRule(String ruleName) {
        rc.hasRule(ruleName)
    }

    def deleteRule(String ruleName) {
        rc.deleteRule(ruleName)
    }

}
