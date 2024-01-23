package com.zwk.parser

class RuleContainer {

    final map = [:]

    def rule(s, clo) {
        if (map.containsKey(s)) {
            throw new IllegalArgumentException("rule name repeat: $s")
        }
        map.put(s, clo)
    }

    def getRule(s) {
        def rule = map[s]
        if (!rule) {
            throw new IllegalArgumentException("rule not exists: $s")
        }
        rule.clone()
    }

    def hasRule(ruleName) {
        map.containsKey(ruleName)
    }

    def deleteRule(s) {
        map.remove(s)
    }
}
