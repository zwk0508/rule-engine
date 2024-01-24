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

        def ifExprs = node.ifExprs
        if (ifExprs) {
            for (expr in ifExprs) {
                expr.accept(this, builder)
            }
        }
        builder.append('}')
        return null
    }

    @Override
    Void visitIfExprNode(IfExprNode node, StringBuilder builder) {

        builder.append('if(')
        node.conditionExpr.accept(this, builder)
        builder.append('){')
        def assignExprs = node.assignExprs
        if (assignExprs) {
            def size = assignExprs.size()
            for (i in 0..<size) {
                if (i > 0) {
                    builder.append('\n')
                }
                assignExprs[i].accept(this, builder)
            }
        }

        builder.append('}\n')

        return null
    }

    @Override
    Void visitConditionExprNode(ConditionExprNode node, StringBuilder builder) {
        def compExprs = node.compExprs
        if (compExprs) {
            def size = compExprs.size()
            for (i in 0..<size) {
                if (i > 0) {
                    builder.append(getOp(node.ops[i - 1]))
                }
                compExprs[i].accept(this, builder)
            }
        }
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
    Void visitBaseCompExprNode(BaseCompExprNode node, StringBuilder builder) {
        node.memberAccess.accept(this, builder)
        builder.append(' ')
                .append(node.op)
                .append(' ')
        node.literalValue.accept(this, builder)

        return null
    }

    @Override
    Void visitPriorityCompExprNode(PriorityCompExprNode node, StringBuilder builder) {
        builder.append('(')
        node.compExpr.accept(this, builder)
        builder.append(')')
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
    Void visitLiteralValueExprNode(LiteralValueExprNode node, StringBuilder builder) {
        node.literalValue.accept(this, builder)
        return null
    }

    @Override
    Void visitStringLiteralValueNode(StringLiteralValueNode node, StringBuilder builder) {
        builder.append(node.value)
        return null
    }

    @Override
    Void visitNumberLiteralValueNode(NumberLiteralValueNode node, StringBuilder builder) {
        builder.append(node.value)
        return null
    }

    @Override
    Void visitBoolLiteralValueNode(BoolLiteralValueNode node, StringBuilder builder) {
        builder.append(node.value)
        return null
    }
}
