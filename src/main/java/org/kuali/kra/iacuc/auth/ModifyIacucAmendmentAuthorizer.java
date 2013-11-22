/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.auth;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;

public abstract class ModifyIacucAmendmentAuthorizer extends ModifyIacucProtocolAuthorizer {
    
    private String moduleTypeCode;
    private ProtocolAmendRenewService protocolAmendRenewService;
    
    protected ModifyIacucAmendmentAuthorizer(String moduleTypeCode) {
        this.moduleTypeCode = moduleTypeCode;
    }

    /**
     * @see org.kuali.kra.protocol.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.protocol.auth.ProtocolTask)
     */
    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        ProtocolBase protocol = task.getProtocol();
        boolean hasPermission = super.isAuthorized(userId, task);

        if (hasPermission && protocol.isRenewalWithoutAmendment()) {
            hasPermission = IacucProtocolModule.ADD_MODIFY_ATTACHMENTS.equals(moduleTypeCode) || canModifyModule(protocol, moduleTypeCode);
        } else if (hasPermission && isAmendmentOrRenewal(protocol)) {
            hasPermission = canModifyModule(protocol, moduleTypeCode);
        }
        
        if (hasPermission && protocol.isCorrectionMode()) {
            hasPermission = canCorrectModule(protocol, moduleTypeCode);
        }
        
        return hasPermission;
    }
    
    private boolean canCorrectModule(ProtocolBase protocol, String moduleTypeCode) {
        List<String> availableModules = new ArrayList<String>();
        
        try {
            availableModules = protocolAmendRenewService.getAvailableModules(protocol.getProtocolNumber());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        
        return availableModules.contains(moduleTypeCode);
    }
    
    /**
     * For amendment (or a renewal with an amendment), the user can only modify certain modules (parts) of the protocol. Determine
     * if a certain module can be modified or not.
     * 
     * @param protocol the amendment protocol
     * @param moduleTypeCode the module type code
     * @return true if the module can be modified; otherwise false
     */
    private boolean canModifyModule(ProtocolBase protocol, String moduleTypeCode) {
        return protocol.getProtocolAmendRenewal().hasModule(moduleTypeCode);
    }

    public void setProtocolAmendRenewService(ProtocolAmendRenewService protocolAmendRenewService) {
        this.protocolAmendRenewService = protocolAmendRenewService;
    }    

}
