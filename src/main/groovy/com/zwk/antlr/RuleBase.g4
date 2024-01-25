grammar RuleBase;

root            : singleRule+ EOF ;

singleRule      : RULE '(' CHAR_STRING ')' '{' ifExpr* '}' ;

ifExpr          : IF '(' conditionExpr ')' '{' assignExpr* '}' ;

conditionExpr   : compExpr (ops+=LOGIC_OP compExpr)* ;

compExpr        : memberAccess op=COMP_OP rightExpr     #baseCompExpr
                | '(' conditionExpr ')'                 #priorityCompExpr
                ;

assignExpr      : memberAccess '=' rightExpr ;


rightExpr       : rightExpr op='*' rightExpr            #mulExpr
                | rightExpr op='/' rightExpr            #divExpr
                | rightExpr op='+' rightExpr            #plusExpr
                | rightExpr op='-' rightExpr            #subExpr
                | '(' rightExpr ')'                     #priorityRightExpr
                | literalValue                          #literalValueExpr
                | memberAccess                          #idExpr
                | funcCall                              #funcCallExpr
                ;

funcCall        : IDENTIFIER '(' funcParams? ')' ;

funcParams      : funcParam (',' funcParam)* ;

funcParam       : rightExpr
                ;

memberAccess    : IDENTIFIER ('.' IDENTIFIER)* ;

literalValue    : CHAR_STRING                           #stringLiteralValue
                | NUMBER                                #numberLiteralValue
                | BOOL                                  #boolLiteralValue
                | NULL                                  #nullLiteralValue
                ;

IF              : 'IF';
RULE            : 'RULE';
BOOL            : 'TRUE' | 'FALSE' ;
NULL            : 'NULL' ;

COMP_OP         : GT | GE | LT | LE | EQUAL | NOTEQUAL ;
LOGIC_OP        : AND | OR | AND_WORD | OR_WORD ;

AND_WORD        : 'AND' ;
OR_WORD         : 'OR' ;

CHAR_STRING     : '\'' ( EscapeSequence | ~('\'' | '\\'))* '\'' ;

IDENTIFIER      : ID_START (DIGIT | LETTER | DOLLAR | UNDERLINE)* ;

ID_START        : LETTER | DOLLAR | UNDERLINE ;

ASSIGN          : '=' ;
GT              : '>' ;
GE              : '>=' ;
LT              : '<' ;
LE              : '<=' ;
EQUAL           : '==' ;
NOTEQUAL        : '!=' ;
AND             : '&&' ;
OR              : '||' ;
ADD             : '+' ;
SUB             : '-' ;
MUL             : '*' ;
DIV             : '/' ;
DOLLAR          : '$' ;
UNDERLINE       : '_' ;

LPAREN          : '(' ;
RPAREN          : ')' ;
LBRACE          : '{' ;
RBRACE          : '}' ;
DOT             : '.' ;

NUMBER          : DIGIT+ ('.' DIGIT+)? ;

fragment
DIGIT           : [0-9] ;

fragment
LETTER          : [A-Z] ;

fragment
EscapeSequence  : '\\' [abfnrtvz"'|$#\\]
                | '\\' '\r'? '\n'
                | DecimalEscape
                | HexEscape
                | UtfEscape
                ;
fragment
DecimalEscape   : '\\' DIGIT | '\\' DIGIT DIGIT | '\\' [0-2] DIGIT DIGIT ;

fragment
HexEscape       : '\\' 'x' HexDigit HexDigit ;

fragment
UtfEscape       : '\\' 'u{' HexDigit+ '}' ;

fragment
HexDigit        : [0-9a-fA-F] ;

WS              : [ \t\r\n\u000C]+ -> skip ;

COMMENT         : '/*' .*? '*/' -> skip ;

LINE_COMMENT    : '//' ~[\r\n]* -> skip ;