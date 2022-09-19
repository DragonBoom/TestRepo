package indi.apache.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ApacheCommandLine包用于接收命令行参数
 * 
 * @author wzh
 * @since 2021.01.22
 */
public class ApacheCommandLineTest {

	@Test
	void singleTest() throws ParseException {
		CommandLineParser p = new DefaultParser();
		// 模拟命令：
		String[] cmd = {"-a hello -d wow", "hello", "-a ",  "fuck", "-b=123"};
		
		// 定义解析命令的模式
		Options os = new Options();
		os.addOption("a", true, "fff");
		os.addOption("d", true, "fuck ?");
		os.addOption("b", true, "test");

		// 解析命令，若包含未定义的参数将抛异常
		CommandLine cl = null;
		try {
			cl = p.parse(os, cmd);
		} catch (UnrecognizedOptionException e) {
		    System.out.println("参数错误");
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		// 获取前缀不为ie“-”的参数
		System.out.println(cl.getArgList());
		// 获取参数的值
		Assertions.assertEquals(" hello -d wow", cl.getParsedOptionValue("a"));
        Assertions.assertEquals("123", cl.getParsedOptionValue("b"));// 格式默认为字符串
		
		// 输出帮助文件
		HelpFormatter helper = new HelpFormatter();
		helper.printHelp("help page", os);// 第一个参数不能为空字符串
	}
}
