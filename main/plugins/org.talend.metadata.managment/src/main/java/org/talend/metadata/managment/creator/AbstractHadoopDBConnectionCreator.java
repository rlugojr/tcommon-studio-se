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
package org.talend.metadata.managment.creator;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.database.conn.ConnParameterKeys;
import org.talend.core.hadoop.IHadoopClusterService;
import org.talend.core.hadoop.creator.AbstractHadoopConnectionCreator;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;

/**
 * created by ycbai on 2015年6月29日 Detailled comment
 *
 */
public abstract class AbstractHadoopDBConnectionCreator extends AbstractHadoopConnectionCreator {

    protected void retrieveCommonParameters(String relativeHadoopClusterId, Map<String, String> paramsMap) {
        if (relativeHadoopClusterId == null || paramsMap == null) {
            return;
        }
        IHadoopClusterService hadoopClusterService = null;
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IHadoopClusterService.class)) {
            hadoopClusterService = (IHadoopClusterService) GlobalServiceRegister.getDefault().getService(
                    IHadoopClusterService.class);
        }
        if (hadoopClusterService != null) {
            Map<String, String> dbParameters = hadoopClusterService.getHadoopDbParameters(relativeHadoopClusterId);
            Iterator<Entry<String, String>> iter = dbParameters.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                paramsMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    protected void setParameters(DatabaseConnection connection, Map<String, String> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return;
        }
        EMap<String, String> connParameters = connection.getParameters();
        Iterator<Entry<String, String>> iter = parameters.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            connParameters.put(entry.getKey(), entry.getValue());
        }
    }

    protected void initializeConnectionParameters(DatabaseConnection connection) {
        EMap<String, String> parameters = connection.getParameters();
        if (connection.getDatabaseType() == null) {
            connection.setDatabaseType(parameters.get(ConnParameterKeys.CONN_PARA_KEY_DB_TYPE));
        }
        String productId = connection.getProductId();
        if (productId == null) {
            connection.setProductId(productId = parameters.get(ConnParameterKeys.CONN_PARA_KEY_DB_PRODUCT));
            String mapping = null;
            if (MetadataTalendType.getDefaultDbmsFromProduct(productId) != null) {
                mapping = MetadataTalendType.getDefaultDbmsFromProduct(productId).getId();
            }
            if (mapping == null) {
                mapping = "mysql_id"; // default value //$NON-NLS-1$
            }
            connection.setDbmsId(mapping);
        }
        if (connection.getServerName() == null) {
            connection.setServerName(parameters.get(ConnParameterKeys.CONN_PARA_KEY_DB_SERVER));
        }
        if (connection.getPort() == null) {
            connection.setPort(parameters.get(ConnParameterKeys.CONN_PARA_KEY_DB_PORT));
        }
        if (connection.getUsername() == null) {
            connection.setUsername(parameters.get(ConnParameterKeys.CONN_PARA_KEY_USERNAME));
        }
    }

}
