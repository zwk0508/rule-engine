package com.zwk.antlr;

/**
 * @author zwk
 * @version 1.0
 * @date 2024/1/24 17:33
 */

class BaseCompExprNode extends CompExprNode {

    def memberAccess
    def op
    def literalValue

    @Override
    Object accept(Object v, Object c) {
        return v.visitBaseCompExprNode(this, c)
    }
}
