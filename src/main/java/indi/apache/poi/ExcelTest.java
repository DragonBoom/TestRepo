package indi.apache.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * 需要注意poi的处理都是线程不安全的！
 * 
 * <p>HSSFWorkbook = xls (-2003), XSSFWorkbook = xlsx (2007+)
 * 
 * <p>HSSF is our port of the Microsoft Excel 97 (-2003) file format (BIFF8) to pure Java. 
 * XSSF is our port of the Microsoft Excel XML (2007+) file format (OOXML) to pure Java. 
 * 
 * @author wzh
 * @since 2020.12.17
 */
@ExtendWith(TestSeparateExtension.class)
class ExcelTest {

    /**
     * 测试合并单元格
     * 
     * 
     * @throws IOException
     */
    @Test
    @Disabled
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

    /**
     * 可用SXSSFWorkbook来处理大文件，将把数据缓存在文件中、仅在内存中保留指定行、以流的方式处理数据，从而避免爆内存
     * 
     * 
     * <p>参考自 https://juejin.cn/post/6844904002161737735
     * 
     * @since 2020.12.17
     */
    @Test
    @SneakyThrows
    void exportCacheTest() {
        SXSSFWorkbook workbook = null;
        OutputStream outputStream = null;
        try {
            // 创建工作簿
            workbook = new SXSSFWorkbook();
            // 打开压缩临时文件的功能(gzip) 防止占用过多磁盘
            workbook.setCompressTempFiles(true);

            // 创建一个工作表
            Sheet sheet = workbook.createSheet("表名");
            // 创建一行
            Row titleRow = sheet.createRow(0);
            // 创建一个单元格
            Cell cell = titleRow.createCell(0);
            // 给单元格赋值
            cell.setCellValue("内容");

            // 将工作簿写入输出流
            FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/test.xlsx"));
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                // 使用完毕后将产生的临时文件删除 防止将磁盘搞满
                workbook.dispose();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
