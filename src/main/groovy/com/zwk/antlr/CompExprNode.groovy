package com.zwk.antlr

class CompExprNode implements Node {

    def memberAccess
    def op
    def value

    @Override
    void accept(Object v, Object c) {
        v.visitCompExprNode(this, c)
    }
}
