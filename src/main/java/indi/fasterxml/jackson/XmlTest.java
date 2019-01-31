package indi.fasterxml.jackson;

<<<<<<< HEAD
import java.io.IOException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlTest {
    /** 
     * 生产环境的xml...
     */
    public static String TRUE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
            + "<a><e class=\"object\"><ADDRESS type=\"string\">广州市番禺区大石街道</ADDRESS><MAIN_TBL_CONTENT class=\"array\"><e class=\"object\"><ADDRESS type=\"string\">广州市番禺区大石街道</ADDRESS><SMS type=\"string\">1</SMS><build_unit_man type=\"string\"/><build_unit_name type=\"string\">中建四局</build_unit_name><build_unit_tel type=\"string\"/><cantom type=\"string\">番禺区</cantom><car_area type=\"string\"/><car_long type=\"string\">1000</car_long><car_wide type=\"string\">200</car_wide><constr_time type=\"string\">0</constr_time><construct_unit_man type=\"string\">马克</construct_unit_man><construct_unit_tel type=\"string\">15000631259</construct_unit_tel><dadui_popedom type=\"string\">番禺大队,黄埔大队</dadui_popedom><design_unit_man type=\"string\"/><design_unit_tel type=\"string\"/><end_date type=\"string\"/><formDataId type=\"string\">20180821144935979600</formDataId><limit_time type=\"string\">365</limit_time><name type=\"string\">占道施工交通组织方案审核测试</name><owner_unit_man type=\"string\">曼尼</owner_unit_man><owner_unit_name type=\"string\">市公安局</owner_unit_name><owner_unit_tel type=\"string\">15000631259</owner_unit_tel><project_type type=\"string\">道路改造</project_type><remark type=\"string\"/><start_date type=\"string\"/><type type=\"string\">临占</type><walk_area type=\"string\">100</walk_area><walk_long type=\"string\">1000</walk_long><walk_wide type=\"string\"/></e></MAIN_TBL_CONTENT><MAIN_TBL_PK type=\"string\">20180821144935979600</MAIN_TBL_PK><SMS type=\"string\">1</SMS><build_unit_man type=\"string\"/><build_unit_name type=\"string\">中建四局</build_unit_name><build_unit_tel type=\"string\"/><cantom type=\"string\">番禺区</cantom><car_area type=\"string\"/><car_long type=\"string\">1000</car_long><car_wide type=\"string\">200</car_wide><constr_time type=\"string\">0</constr_time><construct_unit_man type=\"string\">马克</construct_unit_man><construct_unit_tel type=\"string\">15000631259</construct_unit_tel><dadui_popedom type=\"string\">番禺大队,黄埔大队</dadui_popedom><design_unit_man type=\"string\"/><design_unit_tel type=\"string\"/><end_date type=\"string\"/><formDataId type=\"string\">20180821144935979600</formDataId><limit_time type=\"string\">365</limit_time><name type=\"string\">占道施工交通组织方案审核测试</name><owner_unit_man type=\"string\">曼尼</owner_unit_man><owner_unit_name type=\"string\">市公安局</owner_unit_name><owner_unit_tel type=\"string\">15000631259</owner_unit_tel><project_type type=\"string\">道路改造</project_type><remark type=\"string\"/><start_date type=\"string\"/><state type=\"string\">200</state><type type=\"string\">临占</type><walk_area type=\"string\">100</walk_area><walk_long type=\"string\">1000</walk_long><walk_wide type=\"string\"/></e></a>";


    @Test
    void go() throws JsonParseException, JsonMappingException, IOException {
        XmlMapper mapper = new XmlMapper();
        JsonNode node = mapper.readTree(TRUE_XML);
        Iterator<JsonNode> elements = node.get("e").elements();
        while (elements.hasNext()) {
            System.out.println(elements.next());
        }
        System.out.println();
=======
import org.junit.jupiter.api.Test;

public class XmlTest {

    @Test
    void go() {
        
>>>>>>> com
    }
}
