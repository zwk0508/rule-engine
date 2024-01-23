package com.zwk.parser

import com.zwk.antlr.*

class GenVisitor extends NodeVisitor<Void, StringBuilder> {
    @Override
    Void visitRootNode(RootNode node, StringBuilder builder) {
        for (n in node.singleRules) {
            n.accept(this, builder)
            builder.append('\n')
        }
        return null
    }

    @Override
    Void visitSingleRuleNode(SingleRuleNode node, StringBuilder builder) {
        builder.append('rule(')
                .append(node.ruleName)
                .append('){')
                .append('if(')
        def size = node.compExprs.size()
        for (i in 0..<size) {
            if (i > 0) {
                builder.append(' ')
                        .append(getOp(node.ops[i - 1]))
                        .append(' ')
            }
            node.compExprs[i].accept(this, builder)
        }
        builder.append('){')

        size = node.assignExprs.size()
        for (i in 0..<size) {
            if (i > 0) {
                builder.append('\n')
            }
            node.assignExprs[i].accept(this, builder)
        }
        builder.append('}}')
        return null
    }

    def getOp(op) {
        if ('AND'.equalsIgnoreCase(op)) {
            return '&&'
        }
        if ('OR'.equalsIgnoreCase(op)) {
            return '||'
        }
        return op
    }

    @Override
    Void visitCompExprNode(CompExprNode node, StringBuilder builder) {
        node.memberAccess.accept(this, builder)
        builder.append(node.op)
                .append(node.value)
        return null
    }

    @Override
    Void visitAssignExprNode(AssignExprNode node, StringBuilder builder) {
        node.memberAccess.accept(this, builder)
        builder.append(' = ')
        node.rightExpr.accept(this, builder)
        return null
    }

    @Override
    Void visitMemberAccessNode(MemberAccessNode node, StringBuilder builder) {
        def ids = node.ids
        def size = ids.size()
        if (size > 1) {
            builder.append('it.bindings[\'')
                    .append(ids[0])
                    .append('\'].')
                    .append(ids[1..<size].join('.'))
        } else {
            builder.append('it.root.')
                    .append(ids[0])
        }

        return null
    }

    @Override
    Void visitMulExprNode(MulExprNode node, StringBuilder builder) {
        node.left.accept(this, builder)
        builder.append(node.op)
        node.right.accept(this, builder)
        return null
    }

    @Override
    Void visitDivExprNode(DivExprNode node, StringBuilder builder) {
        node.left.accept(this, builder)
        builder.append(node.op)
        node.right.accept(this, builder)
        return null
    }

    @Override
    Void visitPlusExprNode(PlusExprNode node, StringBuilder builder) {
        node.left.accept(this, builder)
        builder.append(node.op)
        node.right.accept(this, builder)
        return null
    }

    @Override
    Void visitSubExprNode(SubExprNode node, StringBuilder builder) {
        node.left.accept(this, builder)
        builder.append(node.op)
        node.right.accept(this, builder)
        return null
    }

    @Override
    Void visitIdExprNode(IdExprNode node, StringBuilder builder) {
        node.memberAccess.accept(this, builder)
        return null
    }

    @Override
    Void visitStringExprNode(StringExprNode node, StringBuilder builder) {
        builder.append(node.string)
        return null
    }

    @Override
    Void visitNumberExprNode(NumberExprNode node, StringBuilder builder) {
        builder.append(node.number)
        return null
    }
}
