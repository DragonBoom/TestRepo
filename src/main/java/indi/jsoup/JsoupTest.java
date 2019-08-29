package indi.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class JsoupTest {
    
    /**
     * 测试从富文本中提取纯文字
     */
    @Test
    void innerTestTest() {
//        String richTxt = "<p style=\"text-align:center;\"><strong>测试图文</strong></p><h3><span class=\"ue_t\" style=\"font-family:幼圆\">[标题 1]</span></h3><p><span class=\"ue_t\" style=\"font-family:幼圆\"><img src=\"http://127.0.0.1:9091/pooc/console/files//1117\" width=\"300\" alt=\"user.png\"/></span></p><p class=\"ue_t\" style=\"text-indent:2em;\">对于“插入”选项卡上的库，在设计时都充分考虑了其中的项与文档整体外观的协调性。 您可以使用这些库来插入表格、页眉、页脚、列表、封面以及其他文档构建基块。 您创建的图片、图表或关系图也将与当前的文档外观协调一致。</p><h3><span class=\"ue_t\" style=\"font-family:幼圆\">[标题 2]</span></h3><p class=\"ue_t\" style=\"text-indent:2em;\">在“开始”选项卡上，通过从快速样式库中为所选文本选择一种外观，您可以方便地更改文档中所选文本的格式。 您还可以使用“开始”选项卡上的其他控件来直接设置文本格式。大多数控件都允许您选择是使用当前主题外观，还是使用某种直接指定的格式。</p><h3><span class=\"ue_t\" style=\"font-family:幼圆\">[标题 3]</span></h3><p class=\"ue_t\">对于“插入”选项卡上的库，在设计时都充分考虑了其中的项与文档整体外观的协调性。 您可以使用这些库来插入表格、页眉、页脚、列表、封面以及其他文档构建基块。 您创建的图片、图表或关系图也将与当前的文档外观协调一致。</p><p class=\"ue_t\"><br/></p><p><br/></p>\r\n";
        String richTxt = "<p style=\"text-align:center;\"><strong>测试图文</strong></p><h3><span class=\"ue_t\" style=\"font-family:幼圆\">[标题 1]</span></h3><p><span class=\"ue_t\" style=\"font-family:幼圆\"><img src=\"http://127.0.0.1:9091/pooc/console/files//1117\" width=\"300\" alt=\"user.png\"/></span></p><p class=\"ue_t\" style=\"text-indent:2em;\">对于“插入”选项卡上的库，在设计时都充分考虑了其中的项与文档整体外观的协调性。 您可以使用这些库来插入表格、页眉、页脚、列表、封面以及其他文档构建基块。 您创建的图片、图表或关系图也将与当前的文档外观协调一致。</p><h3><span class=\"ue_t\" style=\"font-family:幼圆\">[标题 2]</span></h3><p class=\"ue_t\" style=\"text-indent:2em;\">在“开始”选项卡上，通过从快速样式库中为所选文本选择一种外观，您可以方便地更改文档中所选文本的格式。 您还可以使用“开始”选项卡上的其他控件来直接设置文本格式。大多数控件都允许您选择是使用当前主题外观，还是使用某种直接指定的格式。</p><h3><span class=\"ue_t\" style=\"font-family:幼圆\">[标题 3]</span></h3><p class=\"ue_t\">对于“插入”选项卡上的库，在设计时都充分考虑了其中的项与文档整体外观的协调性。 您可以使用这些库来插入表格、页眉、页脚、列表、封面以及其他文档构建基块。 您创建的图片、图表或关系图也将与当前的文档外观协调一致。</p><p class=\"ue_t\"><br/></p><p><br/></p>";
        
        Document doc = Jsoup.parse(richTxt);
        Elements pTags = doc.getElementsByTag("p");
        pTags.forEach(ele -> {
            System.out.println(ele.text());
        });
        
        System.out.println(pTags.text());
        
        System.out.println(doc.text());
    }
    
    @Test
    void parseNotHtmlTest() {
        System.out.println(Jsoup.parse("<html>"));
    }

}
