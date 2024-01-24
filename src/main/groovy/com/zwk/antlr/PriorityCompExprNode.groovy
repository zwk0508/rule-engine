package com.zwk.antlr

class PriorityCompExprNode extends CompExprNode {
    def conditionExpr

    @Override
    def accept(Object v, Object c) {
        return v.visitPriorityCompExprNode(this, c)
    }
}
