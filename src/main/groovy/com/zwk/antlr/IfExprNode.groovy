package com.zwk.antlr

class IfExprNode implements Node {

    def conditionExpr

    def assignExprs = []

    @Override
    def accept(Object v, Object c) {
        return v.visitIfExprNode(this, c)
    }
}
