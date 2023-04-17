package fr.iut.tpjson;

import java.util.stream.Collectors;

public class MyJsonTextVisitor implements MyJsonVisitor {

    String result;

    protected  String toJson(MyJsonNode p) {
        p.accept(this);
        return result;
    }

    @Override
    public void caseNull(MyJsonNode.MyNullJsonNode p) {
        result = "null";
    }
    @Override
    public void caseText(MyJsonNode.MyTextJsonNode p) {
        result = "\"" + p.value + "\"";
    }
    @Override
    public void caseNumeric(MyJsonNode.MyNumericJsonNode p) {
        result = Double.toString(p.value);
    }
    @Override
    public void caseBoolean(MyJsonNode.MyBooleanJsonNode p) {
        result = (p.value)? "true" : "false";
    }

    @Override
    public void caseArray(MyJsonNode.MyArrayJsonNode p) {
        result = "[" + p.child.stream().map(e -> toJson(e)).collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public void caseObject(MyJsonNode.MyObjectJsonNode p) {
        result = "{" + p.fields.entrySet().stream().map(
                e -> "\"" + e.getKey() +"\":" + toJson(e.getValue())
            ).collect(Collectors.joining(", ")) + "}";
    }
}
