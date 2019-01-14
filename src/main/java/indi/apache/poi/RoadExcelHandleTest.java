package indi.apache.poi;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;

class RoadExcelHandleTest {
    private static final Logger logger = LoggerFactory.getLogger(RoadExcelHandleTest.class);
    
    @Test
    void go() throws IOException, InstantiationException, IllegalAccessException {
        InputStream inputStream = Files.newInputStream(Paths.get("d:", "road.xls"));
        Builder builder = new Builder(inputStream);
//        builder.test();
//        System.exit(0);
        Function<Object, Object> handleIllegaNumberFunction = cellValue -> {
            if (!(cellValue instanceof Number)) {
                // TODO(wzh) 若输入的不是数字
            }
            return cellValue;
        };
        
        builder
            .loadCell(1, 1, "name")
            .loadCell(2, 1, "address")
            .loadCell(3, 1, "attribute", cellValue -> {//项目类型，1-临占  0-挖掘
                String str = (String) cellValue;
                switch (str) {
                case "1:临占" :
                    return 1;
                case "0:挖掘":
                    return 0;
                default :
                    onInputError();
                    break;
                }
                return null;
            })
            .loadCell(3, 3, "category", handleIllegaNumberFunction)//工程性质，1-审批工程（正常流程申请）  0-抢修工程（先作业后备案）-1:其他
            // 以下单元格里的数据必须是数字
            .loadCell(4, 1, "carLong", handleIllegaNumberFunction)
            .loadCell(4, 3, "carWide", handleIllegaNumberFunction)
            .loadCell(5, 1, "carArea", handleIllegaNumberFunction)
            .loadCell(5, 3, "walkLong", handleIllegaNumberFunction)
            .loadCell(6, 1, "walkWide", handleIllegaNumberFunction)
            .loadCell(6, 3, "walArea", handleIllegaNumberFunction)
            // 以下单元格必须是日期
            .loadCell(7, 1, "startDate")//申请开工日期
            .loadCell(7, 3, "endDate")//申请完工日期
            .loadCell(8, 1, "limitTime")//工期(天)
            
            .loadCell(8, 3, "projectTypeId", cellValue -> {
                // TODO
                return null;
            })//工程类型
            .loadCell(9, 1, "constrTime")//施工时间，1-白天  0-夜间
            
            .loadCell(10, 1, "ownerUnitName")
            .loadCell(11, 1, "ownerUnitMan")
            .loadCell(11, 3, "ownerUnitTel")
            .loadCell(12, 1, "designUnitMan")
            .loadCell(12, 3, "designUnitTel")
            .loadCell(13, 1, "buildUnitName")
            .loadCell(14, 1, "buildUnitMan")
            .loadCell(14, 3, "buildUnitTel")
            .loadCell(15, 1, "constructUnitMan")
            .loadCell(15, 3, "constructUnitTel")
            .build();
    }
    
    public void onInputError() {
        
    }
    
    public static class Builder {
        private Sheet sheet;
        
        private ImmutableTable.Builder<Integer, Integer, String> immutableTableBuilder = ImmutableTable.builder();
        private ImmutableTable<Integer, Integer, String> immutableTable;
        
        private ImmutableMap.Builder<String, Function<Object, Object>> converterMapBuilder = 
                ImmutableMap.<String, Function<Object, Object>>builder();
        private ImmutableMap<String, Function<Object, Object>> converterMap;
        
        public Builder(InputStream inStream) {
            HSSFWorkbook hssfWorkbook = null;
            try {
                hssfWorkbook = new HSSFWorkbook(inStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sheet = hssfWorkbook.getSheetAt(0);// TODO
        }
        
        public Builder loadCell(int row, int column, String propertyName) {
            immutableTableBuilder.put(row, column, propertyName);
            return this;
        }
        
        public Builder loadCell(int row, int column, String propertyName, Function<Object, Object> converter) {
            immutableTableBuilder.put(row, column, propertyName);
            converterMapBuilder.put(propertyName, converter);
            return this;
        }
        
        public Builder build() {
            immutableTable = immutableTableBuilder.build();
            converterMap = converterMapBuilder.build();
            return this;
        }
        
        // TODO readExcel(...)
        
        public <T> T getOne(Class<T> cl) throws InstantiationException, IllegalAccessException {
            T bean = cl.newInstance();
            immutableTable.columnKeySet().forEach(rownum -> {
                Row row = sheet.getRow(rownum);
                ImmutableMap<Integer,String> immutableMap = immutableTable.row(rownum);
                immutableMap.forEach((cellnum, propertyName) -> {
                    Cell cell = row.getCell(cellnum);
                    Object value = cell.getStringCellValue();
                    if (converterMap.containsKey(propertyName)) {
                        value = converterMap.get(propertyName).apply(value);
                    }
                    try {
                        BeanUtils.copyProperty(bean, propertyName, value);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            });
            return bean;
        }
        
        public void test() {
            int lastRowNum = sheet.getLastRowNum();
            for (int rownum = 0; rownum < lastRowNum; rownum++) {
                Row row = sheet.getRow(rownum);
                short lastCellNum = row.getLastCellNum();
                for (int cellnum = 0; cellnum < lastCellNum; cellnum++) {
                    Cell cell = row.getCell(cellnum);
                    Object value = null;
                    switch (cell.getCellTypeEnum()) {// TODO(wzh) fill it...
                    case BLANK:
                        value = null;
                        continue;
                    case BOOLEAN:
                        break;
                    case ERROR:
                        break;
                    case FORMULA:
                        value = cell.getNumericCellValue();
                        break;
                    case NUMERIC:
                        value = cell.getNumericCellValue();
                        break;
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case _NONE:
                        value = null;
                        break;
                    default:
                        break;
                    }
                    logger.debug("{}-{} |=> {}", rownum, cellnum, value);
                }
            }
        }
    }

}
