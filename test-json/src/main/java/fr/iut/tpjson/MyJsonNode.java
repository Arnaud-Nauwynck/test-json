package fr.iut.tpjson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class MyJsonNode {

    public abstract String toJson();

    // nested static classes: all known sub-classes  of JsonNode

    public static class MyNullJsonNode extends MyJsonNode {

        @Override
        public String toJson() {
            return "null";
        }

    }

    public static class MyTextJsonNode extends MyJsonNode {
        public String value;

        public MyTextJsonNode() {
        }
        public MyTextJsonNode(String value) {
            this.value = value;
        }

        @Override
        public String toJson() {
            return "\"" + value.replace("\"", "\\\"") + "\"";
        }

    }

    public static class MyNumericJsonNode extends MyJsonNode {
        public double value;

        @Override
        public String toJson() {
            return Double.toString(value);
        }
    }

    public static class MyBooleanJsonNode extends MyJsonNode {  public boolean value;

        @Override
        public String toJson() {
            return value? "true" : "false";
        }

    }

    public static class MyArrayJsonNode extends MyJsonNode {
        public List<MyJsonNode> child = new ArrayList<>();

        @Override
        public String toJson() {
            return "[" + child.stream().map(e -> e.toJson()).collect(Collectors.joining(", ")) + "]";
        }

        public void add(MyObjectJsonNode node) {
            child.add(node);
        }
    }
    public static class MyObjectJsonNode extends MyJsonNode {
        public Map<String,MyJsonNode> fields = new LinkedHashMap<>();

        public void put(String key, MyJsonNode node) {
            fields.put(key, node);
        }

        public void putText(String key, String text) {
            MyTextJsonNode node = new MyTextJsonNode(text);
            put(key, node);
        }

        @Override
        public String toJson() {
            return "{" + fields.entrySet().stream().map(
                    e -> "\"" + e.getKey() +"\":" + e.getValue().toJson()
                ).collect(Collectors.joining(", ")) + "}";
        }

    }

}
