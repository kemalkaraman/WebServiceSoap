/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kemal.mywebservicesoap;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author Kemal KARAMAN
 */
@WebService(serviceName = "TestService")
public class TestService {

    @Resource
    WebServiceContext webServiceContext;

    String json = "{\n" +
"  \"successful\": true,\n" +
"  \"resultmessage\": {\n" +
"    \"Result \": true,\n" +
"    \"DataJson \":{\"playlist\":[{\"order\":1,\"scenario\":1,\"type\":2,\"showTime\":15,\"repeat\":2,\"fileName\":\"mepsan.mp4\"},{\"order\":2,\"scenario\":2,\"type\":1,\"showTime\":15,\"repeat\":1,\"fileName\":\"mepsan.png\"},{\"order\":3,\"scenario\":3,\"type\":2,\"showTime\":15,\"repeat\":1,\"fileName\":\"opet.mp4\"}],\"scenarios\":[{\"id\":1,\"year\":[2021],\"mount\":[1,2,3,4,5,6,7,8,9,10,11,12],\"day\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],\"hour\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]},{\"id\":2,\"year\":[2021],\"mount\":[1],\"day\":[1,8,9,10,11,12,13,14,15,30],\"hour\":[6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]},{\"id\":3,\"year\":[2021],\"mount\":[1],\"day\":[1,8,9,10,11,12,13,14,15,30],\"hour\":[6,7,8,9,10,11,12,13,14,15,16,17,18]}]},\n" +
"    \"PointOfSaleId \":123,\n" +
"    \"ErrorMessage \": \"Kayit Güncellendi. \",\n" +
"    \"ErrorCode \": 123\n" +
"  }\n" +
"}";

    @WebMethod(operationName = "SetData")
    public String SetData(@WebParam(name = "json") String json) {
        if (isValidatorUsernameAndPassword()) {
            if (json != null) {
                if (json != "") {
                    this.json = json;
                }
            }

            return this.json;
        }
        return "Unknown User!";
    }

    @WebMethod(operationName = "GetData")
    public String GetData() {
        if (isValidatorUsernameAndPassword()) {
            return this.json;
        }
        return "Unknown User!";
    }

    public boolean isValidatorUsernameAndPassword() {
        MessageContext messageContext = webServiceContext.getMessageContext();

        Map<?, ?> requestHeaders = (Map<?, ?>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List<?> usernameList = (List<?>) requestHeaders.get("username");
        List<?> passwordList = (List<?>) requestHeaders.get("password");

        String username = "";
        String password = "";

        if (usernameList != null) {
            username = usernameList.get(0).toString();
        }

        if (passwordList != null) {
            password = passwordList.get(0).toString();
        }

        if (username.equals("kemal") && password.equals("123")) {

            return true;

        } else {

            return false;
        }
    }
}
/*
{
  "successful": true,
  "resultmessage": {
    "Result ": true,
    "DataJson ":{"playlist":[{"order":1,"scenario":1,"type":2,"showTime":15,"repeat":2,"fileName":"mepsan.mp4"},{"order":2,"scenario":2,"type":1,"showTime":15,"repeat":1,"fileName":"mepsan.png"},{"order":3,"scenario":3,"type":2,"showTime":15,"repeat":1,"fileName":"opet.mp4"}],"scenarios":[{"id":1,"year":[2021],"mount":[1,2,3,4,5,6,7,8,9,10,11,12],"day":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],"hour":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]},{"id":2,"year":[2021],"mount":[1],"day":[1,8,9,10,11,12,13,14,15,30],"hour":[6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]},{"id":3,"year":[2021],"mount":[1],"day":[1,8,9,10,11,12,13,14,15,30],"hour":[6,7,8,9,10,11,12,13,14,15,16,17,18]}]},
    "PointOfSaleId ":123,
    "ErrorMessage ": "Kayit Güncellendi. ",
    "ErrorCode ": 123
  }
}
*/