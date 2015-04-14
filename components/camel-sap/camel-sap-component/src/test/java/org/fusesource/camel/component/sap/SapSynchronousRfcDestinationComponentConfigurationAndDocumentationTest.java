/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.camel.component.sap;

import org.apache.camel.ComponentConfiguration;
import org.apache.camel.EndpointConfiguration;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class SapSynchronousRfcDestinationComponentConfigurationAndDocumentationTest extends CamelTestSupport {

    @Override
    public boolean isUseRouteBuilder() {
        return false;
    }

    @Test
    public void testComponentConfiguration() throws Exception {
        SapSynchronousRfcDestinationComponent comp = context.getComponent("sap-srfc-destination", SapSynchronousRfcDestinationComponent.class);
        EndpointConfiguration conf = comp.createConfiguration("sap-srfc-destination:destinationName:rfcName?stateful=true&transacted=false");

        assertEquals("true", conf.getParameter("stateful"));
        assertEquals("false", conf.getParameter("transacted"));

        ComponentConfiguration compConf = comp.createComponentConfiguration();
        String json = compConf.createParameterJsonSchema();
        assertNotNull(json);

        assertTrue(json.contains("\"destination\": { \"kind\": \"path\", \"required\": \"true\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the destination this endpoint sends an SAP request to\" }"));
        assertTrue(json.contains("\"rfc\": { \"kind\": \"path\", \"required\": \"true\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the Remote Function Module this endpoint sends an SAP request to\" }"));
        assertTrue(json.contains("\"transacted\": { \"kind\": \"parameter\", \"type\": \"boolean\", \"javaType\": \"boolean\", \"deprecated\": \"false\", \"defaultValue\": \"false\", \"description\": \"When true specifies that this endpoint will initiate an SAP transaction\" }"));
        assertTrue(json.contains("\"stateful\": { \"kind\": \"parameter\", \"type\": \"boolean\", \"javaType\": \"boolean\", \"deprecated\": \"false\", \"defaultValue\": \"false\", \"description\": \"When true specifies that this endpoint will initiate an SAP stateful session\" }"));
    }

}
