package com.zwk.antlr

class IdExprNode extends RightExprNode {
    def memberAccess

    @Override
    void accept(Object v, Object c) {
        v.visitIdExprNode(this, c)
    }
}
