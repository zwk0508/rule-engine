package com.zwk.antlr

class MemberAccessNode implements Node {

    def ids = []

    @Override
    def accept(Object v, Object c) {
        v.visitMemberAccessNode(this, c)
    }
}
