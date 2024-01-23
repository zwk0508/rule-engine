package com.zwk.antlr

class AssignExprNode implements Node {

    def memberAccess
    def rightExpr

    @Override
    void accept(Object v, Object c) {
        v.visitAssignExprNode(this, c)
    }
}
