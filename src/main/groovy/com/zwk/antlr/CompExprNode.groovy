package com.zwk.antlr

class CompExprNode implements Node {

    def memberAccess
    def op
    def value

    @Override
    def accept(Object v, Object c) {
        v.visitCompExprNode(this, c)
    }
}
