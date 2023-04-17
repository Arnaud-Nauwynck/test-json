package fr.iut.tpjson;

public interface MyJsonVisitor {

    void caseNull(MyJsonNode.MyNullJsonNode p);

    void caseText(MyJsonNode.MyTextJsonNode p);

    void caseNumeric(MyJsonNode.MyNumericJsonNode p);

    void caseBoolean(MyJsonNode.MyBooleanJsonNode p);

    void caseArray(MyJsonNode.MyArrayJsonNode p);

    void caseObject(MyJsonNode.MyObjectJsonNode p);

}
