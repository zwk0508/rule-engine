package com.zwk.antlr

class RootNode implements Node {
    def singleRules = []

    @Override
    void accept(Object v, Object c) {
        v.visitRootNode(this,c)
    }
}
