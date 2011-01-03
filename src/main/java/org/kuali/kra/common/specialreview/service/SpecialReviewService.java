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
package org.kuali.kra.common.specialreview.service;

/**
 * Provides services for linking an IRB Protocol with other modules.
 */
public interface SpecialReviewService {

    /**
     * Gets the route header id of the document represented by the given protocolNumber.
     * 
     * @param protocolNumber The number of the protocol
     * @return the route header id of the protocol document
     * @throws Exception
     */
    Long getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber) throws Exception;

}