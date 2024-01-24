package com.zwk.antlr

class LiteralValueExprNode extends RightExprNode {
    def literalValue

    @Override
    def accept(Object v, Object c) {
        return v.visitLiteralValueExprNode(this, c)
    }
}
