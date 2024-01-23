package com.zwk.antlr

class PlusExprNode extends RightExprNode {
    def left
    def right
    def op

    @Override
    void accept(Object v, Object c) {
        v.visitPlusExprNode(this, c)
    }
}
