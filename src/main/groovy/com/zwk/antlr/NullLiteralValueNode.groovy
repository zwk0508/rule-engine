package com.zwk.antlr

class NullLiteralValueNode extends LiteralValueNode{
    @Override
    def accept(Object v, Object c) {
        return v.visitNullLiteralValueNode(this, c)
    }
}
