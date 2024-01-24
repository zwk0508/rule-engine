package com.zwk.antlr

class NodeVisitor<R, C> {

    R processNode(Node node, C c) {
        (R) node.accept(this, c)
    }

    R visitRootNode(RootNode node, C c) {
        visitNode(node, c)
    }

    R visitSingleRuleNode(SingleRuleNode node, C c) {
        visitNode(node, c)
    }

    R visitIfExprNode(IfExprNode node, C c) {
        visitNode(node, c)
    }

    R visitConditionExprNode(ConditionExprNode node, C c) {
        visitNode(node, c)
    }

    R visitBaseCompExprNode(BaseCompExprNode node, C c) {
        visitNode(node, c)
    }

    R visitPriorityCompExprNode(PriorityCompExprNode node, C c) {
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

    R visitLiteralValueExprNode(LiteralValueExprNode node, C c) {
        visitNode(node, c)
    }

    R visitStringLiteralValueNode(StringLiteralValueNode node, C c) {
        visitNode(node, c)
    }

    R visitNumberLiteralValueNode(NumberLiteralValueNode node, C c) {
        visitNode(node, c)
    }

    R visitBoolLiteralValueNode(BoolLiteralValueNode node, C c) {
        visitNode(node, c)
    }

    R visitNode(Node node, C c) {
        null
    }
}
