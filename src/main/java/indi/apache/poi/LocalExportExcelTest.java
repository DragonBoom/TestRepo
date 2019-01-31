package indi.apache.poi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;

class LocalExportExcelTest {

    /**
     * 测试合并单元格
     * 
     * @throws IOException
     */
    @Test
    void test() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("test");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 18));
        Row row1 = sheet.createRow(0);
        Cell cell0 = row1.createCell(0);
        
        cell0.setCellValue("卫生防疫准许证核发一览表");
        
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 20);
        
        CellStyle cellStyle0 = cell0.getCellStyle();
        cellStyle0.setFont(font);
        
        workbook.write(Files.newOutputStream(Paths.get("d:", "tets.xls"), StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING));
        workbook.close();
    }

}
