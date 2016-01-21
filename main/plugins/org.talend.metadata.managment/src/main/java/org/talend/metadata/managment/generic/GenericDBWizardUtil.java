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
package org.talend.metadata.managment.generic;

import org.talend.components.api.wizard.ComponentWizard;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.database.conn.ConnParameterKeys;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.runtime.services.IGenericWizardService;

/**
 * created by ycbai on 2016年1月22日 Detailled comment
 *
 */
public class GenericDBWizardUtil {

    public static IGenericWizardService getGenericWizardService() {
        IGenericWizardService wizardService = null;
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IGenericWizardService.class)) {
            wizardService = (IGenericWizardService) GlobalServiceRegister.getDefault().getService(IGenericWizardService.class);
        }
        return wizardService;
    }

    public static ComponentWizard getDBWizard(DatabaseConnection connection, String typeName) {
        ComponentWizard dbWizard = null;
        IGenericWizardService wizardService = getGenericWizardService();
        if (wizardService != null) {
            String compProperties = connection.getParameters().get(ConnParameterKeys.CONN_PARA_KEY_COMPONENT_PROPERTIES);
            try {
                dbWizard = wizardService.getComponentWizard(typeName, compProperties, connection.getId());
            } catch (Exception e) {
                // ignore it
            }
        }
        return dbWizard;
    }

}
