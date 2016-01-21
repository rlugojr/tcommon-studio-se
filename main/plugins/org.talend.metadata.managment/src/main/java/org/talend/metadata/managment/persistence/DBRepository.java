// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.metadata.managment.persistence;

import org.apache.axis.wsdl.symbolTable.SchemaUtils;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.schema.Schema;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.repository.persistence.AbstractRepository;

/**
 * created by ycbai on 2016年1月22日 Detailled comment
 *
 */
public class DBRepository extends AbstractRepository {

    @Override
    public String storeComponentProperties(ComponentProperties properties, String name, String repositoryLocation, Schema schema) {
        String serializedProperties = properties.toSerialized();
        if (repositoryLocation.contains(REPOSITORY_LOCATION_SEPARATOR)) {// nested properties to be
            GenericConnectionItem item = getGenericConnectionItem(repositoryLocation.substring(0,
                    repositoryLocation.indexOf(REPOSITORY_LOCATION_SEPARATOR)));
            if (item == null) {
                throw new RuntimeException("Failed to find the GenericConnectionItem for location:" + repositoryLocation); //$NON-NLS-1$
            }
            GenericConnection connection = (GenericConnection) item.getConnection();
            SubContainer subContainer = createContainer(name, serializedProperties);
            if (repositoryLocation.endsWith(REPOSITORY_LOCATION_SEPARATOR)) {// first nested property
                if (item != null) {
                    connection.getOwnedElement().add(subContainer);
                }
            } else {
                SubContainer parentContainer = getContainer(connection, repositoryLocation);
                parentContainer.getOwnedElement().add(subContainer);
            }
            // if there is a schema then creates a Schema element
            if (schema != null) {
                MetadataTable metadataTable = SchemaUtils.createSchema(name, serializedProperties);
                subContainer.getOwnedElement().add(metadataTable);
                SchemaUtils.convertComponentSchemaIntoTalendSchema(schema, metadataTable);
            }
            return repositoryLocation + REPOSITORY_LOCATION_SEPARATOR + name;
        } else {// simple properties to be set
            GenericConnectionItem item = getGenericConnectionItem(repositoryLocation);
            if (item != null) {
                GenericConnection connection = (GenericConnection) item.getConnection();
                connection.setCompProperties(serializedProperties);
                connection.getOwnedElement().clear();
                return repositoryLocation + REPOSITORY_LOCATION_SEPARATOR;
            } else {
                throw new RuntimeException("Failed to find the GenericConnectionItem for location:" + repositoryLocation); //$NON-NLS-1$
            }
        }
    }

}
