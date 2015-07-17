package org.talend.core.model.metadata.query.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.utils.TalendTextUtils;

public class PostgreQueryGeneratorTest {

    @Test
    public void testGenerateQuery() {
        IElement postGreSQLElement = mock(IElement.class);
        IElementParameter newParam = mock(IElementParameter.class);
        List<? extends IElementParameter> elementParameters = postGreSQLElement.getElementParameters();
        when(newParam.getName()).thenReturn("DBNAME");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.TEXT);
        when(newParam.getValue()).thenReturn("talend");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(postGreSQLElement.getElementParameter("DBNAME")).thenReturn(newParam);

        newParam = mock(IElementParameter.class);
        when(newParam.getName()).thenReturn("DBTABLE");
        when(newParam.getFieldType()).thenReturn(EParameterFieldType.DBTABLE);
        when(newParam.getValue()).thenReturn("mytable");
        when(newParam.isShow(elementParameters)).thenReturn(true);
        when(postGreSQLElement.getElementParameterFromField(EParameterFieldType.DBTABLE)).thenReturn(newParam);
        PostgreQueryGenerator pQG = new PostgreQueryGenerator(EDatabaseTypeName.PSQL);

        IMetadataTable newTable = new MetadataTable();

        newTable.setLabel("mytable");

        MetadataColumn newColumn = new MetadataColumn();

        newColumn.setLabel("newColumn");

        newColumn.setOriginalDbColumnName("newColumn");

        List<IMetadataColumn> columnsList = new ArrayList<IMetadataColumn>();

        columnsList.add(newColumn);

        newTable.setListColumns(columnsList);

        pQG.setParameters(postGreSQLElement, newTable, "myschema", "mytable");

        String resultString = pQG.generateQuery();

        StringBuilder expectSql = new StringBuilder();
        expectSql
                .append(TalendTextUtils.getQuoteChar())
                .append("SELECT \n  \\\"talend\\\".\\\"myschema\\\".\\\"mytable\\\".\\\"newColumn\\\"\nFROM \\\"talend\\\".\\\"myschema\\\".\\\"mytable\\\"")
                .append(TalendTextUtils.getQuoteChar());

        assertNotNull(resultString);

        assertTrue(!"".equals(resultString));

        assertTrue(expectSql.toString().equals(resultString));
    }

}