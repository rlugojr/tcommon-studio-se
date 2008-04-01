// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataquality.helpers;

import org.talend.dataquality.expressions.BooleanExpressionNode;
import org.talend.dataquality.expressions.ExpressionsFactory;
import orgomg.cwm.objectmodel.core.CoreFactory;
import orgomg.cwm.objectmodel.core.Expression;

/**
 * @author scorreia
 * 
 * Helper class for handling BooleanExpressionNode.
 */
public class BooleanExpressionHelper {

    /**
     * Method "createBooleanExpressionNode".
     * 
     * @param body the body of the Expression
     * @return a BooleanExpressionNode with the given Expression.
     */
    public BooleanExpressionNode createBooleanExpressionNode(String body) {
        BooleanExpressionNode expr = ExpressionsFactory.eINSTANCE.createBooleanExpressionNode();
        Expression expression = CoreFactory.eINSTANCE.createExpression();
        expression.setBody(body);
        expression.setLanguage("SQL");
        expr.setExpression(expression);
        return expr;
    }
}
