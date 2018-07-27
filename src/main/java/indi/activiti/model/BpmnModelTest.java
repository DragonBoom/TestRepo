package indi.activiti.model;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class BpmnModelTest {

    @Autowired
    private BpmnModel bpmnModel;

    @Test
    void convertTest() {
        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode on = converter.convertToJson(bpmnModel);
        System.out.println(on.toString());
    }
}
