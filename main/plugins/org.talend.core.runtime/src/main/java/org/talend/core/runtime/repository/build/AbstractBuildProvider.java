// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.runtime.repository.build;

import org.talend.core.runtime.process.IBuildJobHandler;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractBuildProvider {

    BuildType buildType;

    public BuildType getBuildType() {
        return buildType;
    }

    public void valid() {
        // TODO
    }

    public abstract IBuildJobHandler createHandler();
}
