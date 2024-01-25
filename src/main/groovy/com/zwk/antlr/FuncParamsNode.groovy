package com.zwk.antlr

class FuncParamsNode implements Node{

    def funcParam = []

    @Override
    def accept(Object v, Object c) {
        return v.visitFuncParamsNode(this, c)
    }
}
