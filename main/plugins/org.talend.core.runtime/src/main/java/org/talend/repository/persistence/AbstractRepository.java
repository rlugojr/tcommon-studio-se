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
package org.talend.repository.persistence;

import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.properties.Repository;

/**
 * created by ycbai on 2016年1月22日 Detailled comment
 *
 */
public abstract class AbstractRepository implements Repository {

    public static final String REPOSITORY_LOCATION_SEPARATOR = "#"; //$NON-NLS-1$

    @Override
    public ComponentProperties getPropertiesForComponent(String arg0) {
        return null;
    }

}
