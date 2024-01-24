package com.zwk.antlr

class RuleVisitor extends RuleBaseBaseVisitor<Node> {
    @Override
    Node visitRoot(RuleBaseParser.RootContext ctx) {
        RootNode rootNode = new RootNode()

        def singleRules = ctx.singleRule()
        if (singleRules) {
            for (rule in singleRules) {
                rootNode.singleRules << visit(rule)
            }
        }
        return rootNode
    }

    @Override
    Node visitSingleRule(RuleBaseParser.SingleRuleContext ctx) {
        SingleRuleNode singleRuleNode = new SingleRuleNode()
        singleRuleNode.ruleName = ctx.CHAR_STRING().text
        def ifExprs = ctx.ifExpr()
        if (ifExprs) {
            ifExprs.eachWithIndex { def r, def i ->
                singleRuleNode.ifExprs << visit(r)
            }

        }
        return singleRuleNode
    }

    @Override
    Node visitIfExpr(RuleBaseParser.IfExprContext ctx) {
        def node = new IfExprNode()
        node.conditionExpr = visit(ctx.conditionExpr())
        def assignExprs = ctx.assignExpr()
        if (assignExprs) {
            for (expr in assignExprs) {
                node.assignExprs << visit(expr)
            }
        }
        return node
    }

    @Override
    Node visitConditionExpr(RuleBaseParser.ConditionExprContext ctx) {
        def node = new ConditionExprNode()

        def compExprs = ctx.compExpr()
        if (compExprs) {
            for (expr in compExprs) {
                node.compExprs << visit(expr)
            }
        }

        def ops = ctx.ops
        if (ops) {
            node.ops.addAll(ops.collect { it.text })
        }
        return node
    }

    @Override
    Node visitBaseCompExpr(RuleBaseParser.BaseCompExprContext ctx) {
        def node = new BaseCompExprNode()

        node.memberAccess = visit(ctx.memberAccess())
        node.op = ctx.op.text
        node.literalValue = visit(ctx.literalValue())

        return node
    }

    @Override
    Node visitPriorityCompExpr(RuleBaseParser.PriorityCompExprContext ctx) {
        def node = new PriorityCompExprNode()
        node.conditionExpr = visit(ctx.conditionExpr())
        return node
    }

    @Override
    Node visitStringLiteralValue(RuleBaseParser.StringLiteralValueContext ctx) {
        def node = new StringLiteralValueNode()
        node.value = ctx.CHAR_STRING().text
        return node
    }

    @Override
    Node visitNumberLiteralValue(RuleBaseParser.NumberLiteralValueContext ctx) {
        def node = new NumberLiteralValueNode()
        node.value = ctx.NUMBER().text
        return node
    }

    @Override
    Node visitBoolLiteralValue(RuleBaseParser.BoolLiteralValueContext ctx) {
        def node = new BoolLiteralValueNode()
        node.value = ctx.BOOL().text
        return node
    }

    @Override
    Node visitMemberAccess(RuleBaseParser.MemberAccessContext ctx) {

        def accessNode = new MemberAccessNode()

        accessNode.ids.addAll(ctx.IDENTIFIER().collect { it.text })

        return accessNode
    }

    @Override
    Node visitAssignExpr(RuleBaseParser.AssignExprContext ctx) {
        def exprNode = new AssignExprNode()

        exprNode.memberAccess = visit(ctx.memberAccess())
        exprNode.rightExpr = visit(ctx.rightExpr())

        return exprNode
    }

    @Override
    Node visitMulExpr(RuleBaseParser.MulExprContext ctx) {
        def exprNode = new MulExprNode()
        exprNode.left = visit(ctx.rightExpr(0))
        exprNode.op = ctx.op.text
        exprNode.right = visit(ctx.rightExpr(1))
        return exprNode
    }

    @Override
    Node visitDivExpr(RuleBaseParser.DivExprContext ctx) {
        def exprNode = new DivExprNode()
        exprNode.left = visit(ctx.rightExpr(0))
        exprNode.op = ctx.op.text
        exprNode.right = visit(ctx.rightExpr(1))
        return exprNode
    }

    @Override
    Node visitPlusExpr(RuleBaseParser.PlusExprContext ctx) {
        def exprNode = new PlusExprNode()
        exprNode.left = visit(ctx.rightExpr(0))
        exprNode.op = ctx.op.text
        exprNode.right = visit(ctx.rightExpr(1))
        return exprNode
    }

    @Override
    Node visitSubExpr(RuleBaseParser.SubExprContext ctx) {
        def exprNode = new SubExprNode()
        exprNode.left = visit(ctx.rightExpr(0))
        exprNode.op = ctx.op.text
        exprNode.right = visit(ctx.rightExpr(1))
        return exprNode
    }

    @Override
    Node visitIdExpr(RuleBaseParser.IdExprContext ctx) {
        def exprNode = new IdExprNode()
        exprNode.memberAccess = visit(ctx.memberAccess())

        return exprNode
    }

    @Override
    Node visitLiteralValueExpr(RuleBaseParser.LiteralValueExprContext ctx) {
        def node = new LiteralValueExprNode()
        node.literalValue = visit(ctx.literalValue())
        return node
    }
}

