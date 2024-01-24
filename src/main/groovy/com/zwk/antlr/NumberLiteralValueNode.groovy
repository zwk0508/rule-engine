package com.zwk.antlr

class NumberLiteralValueNode extends LiteralValueNode{
    @Override
    def accept(Object v, Object c) {
        return v.visitNumberLiteralValueNode(this,c)
    }
}
