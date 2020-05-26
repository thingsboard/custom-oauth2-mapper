/**
 * Copyright © 2016-2018 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.mapper.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.oauth2.OAuth2User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping(path = "/oauth2")
public class CustomOAuth2MapperController {

    private static final ObjectMapper json = new ObjectMapper();

    @RequestMapping(value = "/mapper", method = RequestMethod.POST)
    @ResponseBody
    public OAuth2User map(@RequestBody String request) {
        JsonNode externalUserInJson;
        try {
            externalUserInJson = json.readTree(request);
        } catch (IOException e) {
            throw new RuntimeException("Can't parse external user object", e);
        }
        OAuth2User result = new OAuth2User();

        // In case of PE please use PEOAuth2User
        // PEOAuth2User result = new PEOAuth2User();

        String email = externalUserInJson.get("email").asText();
        result.setEmail(email);

        String domain = email .substring(email .indexOf("@") + 1);

        // Any attribute from the external user info object can be used as tenant name
        result.setTenantName("Tenant " + domain);

        // You can set directly Tenant ID instead of the Tenant Name
        // result.setTenantId(new TenantId(UUID.fromString("bc89e8f0-8ba9-11ea-97a9-0bc52285619a")));

        String firstName = externalUserInJson.get("givenName").asText();
        result.setFirstName(firstName);

        String lastName = externalUserInJson.get("familyName").asText();
        result.setLastName(lastName);


//        // If required, you can set Customer Name, and user is going to be created on the Customer level
//        String customerName = externalUserInJson.get("CUSTOMER_NAME_ATTRIBUTE").asText();
//        result.setCustomerName(email);


//        // You can set directly Customer ID instead of the Customer Name
//        result.setCustomerId(new CustomerId(UUID.fromString("e78c0ab0-8ba9-11ea-97a9-0bc52285619a")));

        // ------
        // Fields applicable for Professional Edition
        // ------

//        // If required, you can set Parent Customer Name, and Customer of the user is going to be created in the hierarchy under the Parent Customer
//        String parentCustomerName = externalUserInJson.get("PARENT_CUSTOMER_NAME_ATTRIBUTE").asText();
//        result.setParentCustomerName(parentCustomerName);
//        // You can set directly Parent Customer ID instead of the Parent Customer Name
//        result.setParentCustomerId(new CustomerId(UUID.fromString("e78c0ab0-8ba9-11ea-97a9-0bc52285619a")));

//        // If required, you can set user groups, where user is going to be assigned
//        ArrayList<String> userGroups = new ArrayList<>();
//        userGroups.add("Managers");
//        userGroups.add("Tenant Administrators");
//        userGroups.add(externalUserInJson.get("CUSTOMER_USER_GROUP").asText());
//        result.setUserGroups(userGroups);

        return result;
    }
}
