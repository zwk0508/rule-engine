package com.zwk.antlr

class SingleRuleNode implements Node {

    def ruleName
    def ifExprs = []

    @Override
    def accept(Object v, Object c) {
        v.visitSingleRuleNode(this,c)
    }
}
