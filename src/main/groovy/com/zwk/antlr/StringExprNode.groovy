package com.zwk.antlr

class StringExprNode extends RightExprNode{
    def string
    @Override
    void accept(Object v, Object c) {
        v.visitStringExprNode(this, c)
    }
}
