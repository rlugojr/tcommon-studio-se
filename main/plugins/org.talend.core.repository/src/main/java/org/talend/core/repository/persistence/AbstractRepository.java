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
package org.talend.core.repository.persistence;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.properties.Repository;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.model.ProxyRepositoryFactory;

/**
 * created by ycbai on 2016年1月22日 Detailled comment
 *
 */
public abstract class AbstractRepository implements Repository {

    public static final String REPOSITORY_LOCATION_SEPARATOR = "#"; //$NON-NLS-1$

    @Override
    public ComponentProperties getPropertiesForComponent(String componentId) {
        return null;
    }

    /**
     * DOC sgandon Comment method "getConnectionItem".
     * 
     * @param repositoryLocation
     * @return
     */
    protected ConnectionItem getConnectionItem(String repositoryLocation) {
        ConnectionItem connItem = null;
        try {
            IRepositoryViewObject repViewObj = ProxyRepositoryFactory.getInstance().getLastVersion(repositoryLocation);
            if (repViewObj != null) {
                Property property = repViewObj.getProperty();
                if (property != null) {
                    Item item = property.getItem();
                    if (item instanceof ConnectionItem) {
                        connItem = (ConnectionItem) item;
                    }
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return connItem;
    }

}
