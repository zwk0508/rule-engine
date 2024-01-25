package com.zwk.antlr

class PriorityRightExprNode extends RightExprNode {

    def rightExpr

    @Override
    def accept(Object v, Object c) {
        return v.visitPriorityRightExprNode(this, c)
    }
}
