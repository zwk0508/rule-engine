package com.zwk.antlr

class FuncParamNode implements Node{

    def rightExpr

    @Override
    def accept(Object v, Object c) {
        return v.visitFuncParamNode(this, c)
    }
}
