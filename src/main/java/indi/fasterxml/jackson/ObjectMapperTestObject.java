package indi.fasterxml.jackson;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id"})
public class ObjectMapperTestObject {

    private String id;
    private String name;
    @JsonIgnore
    private byte[] bytes;
    @JsonIgnoreProperties
    private ObjectMapperTestObject testClass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public ObjectMapperTestObject getTestClass() {
        return testClass;
    }

    public void setTestClass(ObjectMapperTestObject testClass) {
        this.testClass = testClass;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TestClass [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", bytes=");
        builder.append(Arrays.toString(bytes));
        builder.append(", testClass=");
        builder.append(testClass);
        builder.append("]");
        return builder.toString();
    }

}
