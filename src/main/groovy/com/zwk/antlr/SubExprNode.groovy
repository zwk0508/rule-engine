package com.zwk.antlr

class SubExprNode extends RightExprNode {

    def left
    def right
    def op

    @Override
    def accept(Object v, Object c) {
        v.visitSubExprNode(this, c)
    }
}
