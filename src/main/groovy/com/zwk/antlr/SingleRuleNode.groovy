package com.zwk.antlr

class SingleRuleNode implements Node {

    def ruleName
    def compExprs = []
    def ops = []
    def assignExprs = []

    @Override
    void accept(Object v, Object c) {
        v.visitSingleRuleNode(this,c)
    }
}
