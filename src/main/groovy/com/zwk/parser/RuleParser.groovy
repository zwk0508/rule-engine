package com.zwk.parser

import com.zwk.antlr.CaseInsensitiveStream
import com.zwk.antlr.RuleBaseLexer
import com.zwk.antlr.RuleBaseParser
import com.zwk.antlr.RuleVisitor
import org.antlr.v4.runtime.*

class RuleParser {
    private RuleContainer rc
    final shell = new GroovyShell()

    private static com.zwk.antlr.Node getNode(String s) {
        CharStream stream = CharStreams.fromString(s)
        TokenStream input = new CommonTokenStream(new RuleBaseLexer(new CaseInsensitiveStream(stream)))
        RuleBaseParser parser = new RuleBaseParser(input)
        parser.removeErrorListeners()
        parser.addErrorListener(new MyErrorListener())
        def root = parser.root()
        def visitor = new RuleVisitor()
        visitor.visit(root);
    }

    static class MyErrorListener extends BaseErrorListener {
        @Override
        void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            String errorMsg = "行: " + line + ",列: " + charPositionInLine + ",错误: " + msg
            throw new RuntimeException(errorMsg)
        }
    }


    def parse(String rule) {
        def node = getNode(rule)
        def sb = new StringBuilder()
        new GenVisitor().processNode(node, sb)
        shell.setProperty('rc', rc)
        def s = """\
def clo  = {
    $sb
}
clo.setDelegate(rc)
clo.setResolveStrategy(Closure.DELEGATE_FIRST)
clo()
"""
        shell.evaluate(s)
    }
}
