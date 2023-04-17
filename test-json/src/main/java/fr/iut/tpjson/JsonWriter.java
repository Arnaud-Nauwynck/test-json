package fr.iut.tpjson;

import com.fasterxml.jackson.databind.node.NullNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonWriter {

    public String toJson(MyJsonNode p) {
        if (p instanceof MyJsonNode.MyNullJsonNode) {
            return "null";
        } else if (p instanceof MyJsonNode.MyTextJsonNode) {
            MyJsonNode.MyTextJsonNode p2 = ( MyJsonNode.MyTextJsonNode) p;
            return "\"" + p2.value + "\"";
        } else if (p instanceof MyJsonNode.MyNumericJsonNode) {
            MyJsonNode.MyNumericJsonNode p2 = (MyJsonNode.MyNumericJsonNode) p;
            return "" + p2.value;
        } else if (p instanceof MyJsonNode.MyBooleanJsonNode) {
            boolean value = ((MyJsonNode.MyBooleanJsonNode) p).value;
            return value? "true" : "false";
        } else if (p instanceof MyJsonNode.MyArrayJsonNode) {
            List<MyJsonNode> child = ((MyJsonNode.MyArrayJsonNode) p).child;
            return "[" + child.stream().map(e -> e.toJson()).collect(Collectors.joining(", ")) + "]";
        } else if (p instanceof MyJsonNode.MyObjectJsonNode) {
            Map<String,MyJsonNode> fields = ((MyJsonNode.MyObjectJsonNode) p).fields;
            return "{" + fields.entrySet().stream().map(
                        e -> "\"" + e.getKey() +"\":" + e.getValue().toJson()
                ).collect(Collectors.joining(", ")) + "}";
        } else {
            return "??";
        }
    }
}
