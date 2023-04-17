package fr.iut.tpjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class JsonMain {

    public static void main(String[] args) throws JsonProcessingException {
        // json Text
        String json = "[ true, 1.23, \"text\", [[]], { \"key\": null } ]";

        // json Tree pointer
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = om.readTree(json);
        } catch(Exception ex) {
            jsonNode = null;
        }
        System.out.println("json parsed:" + jsonNode);


        String jsonUser = "{ \"firstName\":\"John\", \"lastName\":\"Smith\"}";
        UserDTO user;
        try {
            user = om.readValue(jsonUser, UserDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("user:" + user);

        String jsonUser2 = om.writeValueAsString(user);
        System.out.println("userJson:" + jsonUser2);

        // AST -> text: format

        ObjectMapper om2 = new ObjectMapper(new YAMLFactory());
        String yaml = om2.writeValueAsString(user);
        System.out.println("user Yaml:\n" + yaml);


        MyJsonNode.MyObjectJsonNode myUserNode1 = new MyJsonNode.MyObjectJsonNode();
        myUserNode1.putText("firstName", "John");
        myUserNode1.putText("lastName", "Smith");

        MyJsonNode.MyObjectJsonNode myUserNode2 = new MyJsonNode.MyObjectJsonNode();
        myUserNode2.putText("firstName", "Jack");
        myUserNode2.putText("lastName", "Smith");

        MyJsonNode.MyArrayJsonNode myArray = new MyJsonNode.MyArrayJsonNode();
        myArray.add(myUserNode1);
        myArray.add(myUserNode2);

        String json3 = myArray.toJson();
        System.out.println("Json again.. : " + json3);

        String json4 = new JsonWriter().toJson(myArray);
        System.out.println("Json4 again.. : " + json4);

        MyJsonTextVisitor myJsonTextVisitor = new MyJsonTextVisitor();
        myArray.accept(myJsonTextVisitor);
        System.out.println("Json (visitor) again.. : " + myJsonTextVisitor.result);
    }
}
