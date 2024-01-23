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
        singleRuleNode.ruleName = ctx.SINGLE_QUOTE_STRING().text
        def compExprs = ctx.compExpr()
        if (compExprs) {
            compExprs.eachWithIndex { def r, def i ->
                singleRuleNode.compExprs << visit(r)
            }
            singleRuleNode.ops.addAll(ctx.op.collect { it.text })
        }
        def assignExprs = ctx.assignExpr()
        if (assignExprs) {
            for (r in assignExprs) {
                singleRuleNode.assignExprs << visit(r)
            }
        }
        return singleRuleNode
    }

    @Override
    Node visitCompExpr(RuleBaseParser.CompExprContext ctx) {
        def exprNode = new CompExprNode()
        exprNode.memberAccess = visit(ctx.memberAccess())
        exprNode.op = ctx.op.text
        exprNode.value = ctx.SINGLE_QUOTE_STRING()
                ? ctx.SINGLE_QUOTE_STRING().text
                : ctx.NUMBER().text

        return exprNode
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
    Node visitStringExpr(RuleBaseParser.StringExprContext ctx) {

        def exprNode = new StringExprNode()
        exprNode.string = ctx.SINGLE_QUOTE_STRING().text

        return exprNode
    }

    @Override
    Node visitNumberExpr(RuleBaseParser.NumberExprContext ctx) {
        def exprNode = new NumberExprNode()
        exprNode.number = ctx.NUMBER().text
        return exprNode
    }
}

