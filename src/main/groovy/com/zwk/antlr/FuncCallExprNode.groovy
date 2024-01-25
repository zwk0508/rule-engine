package com.zwk.antlr

class FuncCallExprNode extends RightExprNode{

    def funCall

    @Override
    def accept(Object v, Object c) {
        return v.visitFuncCallExprNode(this,c)
    }
}
