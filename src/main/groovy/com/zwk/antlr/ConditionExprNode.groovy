package com.zwk.antlr

class ConditionExprNode implements Node {
    def compExprs = []
    def ops = []

    @Override
    def accept(Object v, Object c) {
        return v.visitConditionExprNode(this, c)
    }
}
