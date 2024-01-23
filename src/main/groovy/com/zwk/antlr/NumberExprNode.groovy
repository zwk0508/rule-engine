package com.zwk.antlr

class NumberExprNode extends RightExprNode {
    def number

    @Override
    def accept(Object v, Object c) {
        v.visitNumberExprNode(this, c)
    }
}
