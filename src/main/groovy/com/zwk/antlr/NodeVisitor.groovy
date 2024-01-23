package com.zwk.antlr

class NodeVisitor<R, C> {

    R processNode(Node node, C c) {
        node.accept(this, c)
    }

    R visitRootNode(RootNode node, C c) {
        visitNode(node, c)
    }

    R visitSingleRuleNode(SingleRuleNode node, C c) {
        visitNode(node, c)
    }

    R visitCompExprNode(CompExprNode node, C c) {
        visitNode(node, c)
    }


    R visitAssignExprNode(AssignExprNode node, C c) {
        visitNode(node, c)
    }

    R visitMemberAccessNode(MemberAccessNode node, C c) {
        visitNode(node, c)
    }

    R visitMulExprNode(MulExprNode node, C c) {
        visitNode(node, c)
    }

    R visitDivExprNode(DivExprNode node, C c) {
        visitNode(node, c)
    }

    R visitPlusExprNode(PlusExprNode node, C c) {
        visitNode(node, c)
    }

    R visitSubExprNode(SubExprNode node, C c) {
        visitNode(node, c)
    }

    R visitIdExprNode(IdExprNode node, C c) {
        visitNode(node, c)
    }

    R visitStringExprNode(StringExprNode node, C c) {
        visitNode(node, c)
    }

    R visitNumberExprNode(NumberExprNode node, C c) {
        visitNode(node, c)
    }

    R visitNode(Node node, C c) {
        null
    }
}
