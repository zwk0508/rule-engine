package com.zwk

class RuleContext {
    def root

    def bindings = [:]

    void put(String key, obj) {
        bindings[key] = obj
    }

    void putAll(Map map) {
        bindings.putAll(map)
    }

    void setRoot(root) {
        this.root = root
    }
}
