package com.zwk.antlr

class DivExprNode extends RightExprNode {
    def left
    def right
    def op

    @Override
    void accept(Object v, Object c) {
        v.visitDivExprNode(this, c)
    }
}
