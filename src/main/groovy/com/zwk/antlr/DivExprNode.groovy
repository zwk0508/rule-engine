package com.zwk.antlr

class DivExprNode extends RightExprNode {
    def left
    def right
    def op

    @Override
    def accept(Object v, Object c) {
        v.visitDivExprNode(this, c)
    }
}
