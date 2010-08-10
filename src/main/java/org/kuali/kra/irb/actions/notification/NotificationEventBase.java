/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.notification;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kew.util.XmlHelper;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.w3c.dom.Element;

/**
 * 
 * This is the super class for protocol actions notification event.
 */
public abstract class NotificationEventBase {

    private static final Log LOG = LogFactory.getLog(NotificationEventBase.class);
    private Protocol protocol;

    /**
     * 
     * Constructs a NotificationEventBase.java.
     */
    public NotificationEventBase() {
    }

    /**
     * 
     * Constructs a NotificationEventBase.java.
     * @param protocol
     */
    public NotificationEventBase(Protocol protocol) {
        this.protocol = protocol;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * 
     * This method is to a list of recipients and append to "recipients" element in KEN notification template.
     * @param recipients
     */
    public void getRecipients(Element recipients) {
        // TODO : based on kcirb-252 : all protocolperson but "SP" are default recipients
        try {
            for (ProtocolPerson protocolPerson : protocol.getProtocolPersons()) {
                if (!"SP".equals(protocolPerson.getProtocolPersonRoleId()) && StringUtils.isNotBlank(protocolPerson.getPersonId())) {
                    // rolodex does not have username
                    XmlHelper.appendXml(recipients, "<user>" + protocolPerson.getPerson().getUserName() + "</user>");
                    // recipientUser.setTextContent(protocol.getPrincipalInvestigator().getPerson().getUserName());
                }
            }
        } catch (Exception e) {
            LOG.info("Protocol withdraw - get recipeint - exception " + e.getStackTrace());

        }
        getProtocolActionsNotificationService().addIrbAdminToRecipients(recipients, getProtocol());
        getProtocolActionsNotificationService().addInitiatorToRecipients(recipients, getProtocol());

    }

    private ProtocolActionsNotificationService getProtocolActionsNotificationService() {
        return KraServiceLocator.getService(ProtocolActionsNotificationService.class);
    }

    /**
     * 
     * This method is to get the ken notification document title.
     * @return
     */
    public abstract String getTitle();

    /**
     * 
     * This method is to get the message body for ken notification.
     * @return
     */
    public abstract String getMessage();

    /**
     * 
     * This method is to get the action type code of this protocol action.
     * @return
     */
    public abstract String getActionTypeCode();

    /**
     * 
     * This method to get the stylesheet template path.
     * TODO : may not need this because template is saved in table now.
     * @return
     */
    public abstract String getTemplatePath();

    /**
     * 
     * This method is to get the template for this event.
     * @return
     */
    public StreamSource getTemplate() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("actionTypeCode", getActionTypeCode());
        List<ProtocolNotificationTemplate> templates = (List<ProtocolNotificationTemplate>) getBusinessObjectService().findMatching(
                ProtocolNotificationTemplate.class, fieldValues);
        if (templates != null && !templates.isEmpty()) {
            return new StreamSource(new ByteArrayInputStream(templates.get(0).getNotificationTemplate()));
        } else {
            return new StreamSource(this.getClass().getResourceAsStream(getTemplatePath()));

        }
        
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

}
