package com.zwk.antlr

class StringLiteralValueNode extends LiteralValueNode {

    @Override
    def accept(Object v, Object c) {
        return v.visitStringLiteralValueNode(this, c)
    }
}
