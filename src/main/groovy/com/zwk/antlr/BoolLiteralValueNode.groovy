package com.zwk.antlr

class BoolLiteralValueNode extends LiteralValueNode {
    @Override
    def accept(Object v, Object c) {
        return v.visitBoolLiteralValueNode(this, c)
    }
}
