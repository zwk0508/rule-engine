package com.zwk.antlr

class MulExprNode extends RightExprNode {
    def left
    def right
    def op

    @Override
    void accept(Object v, Object c) {
        v.visitMulExprNode(this, c)
    }
}
