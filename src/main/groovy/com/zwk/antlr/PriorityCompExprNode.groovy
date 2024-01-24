package com.zwk.antlr

class PriorityCompExprNode extends CompExprNode {
    def compExpr

    @Override
    def accept(Object v, Object c) {
        return v.visitPriorityCompExprNode(this, c)
    }
}
