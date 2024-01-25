package com.zwk.antlr

class FuncCallNode implements Node {
    def funcName
    def funcParams

    @Override
    def accept(Object v, Object c) {
        return v.visitFuncCallNode(this, c)
    }
}
