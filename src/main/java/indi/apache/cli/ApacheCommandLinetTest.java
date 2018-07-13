package indi.apache.cli;

import java.io.PrintStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

public class ApacheCommandLinetTest {

	@Test
	void singleTest() {
		CommandLineParser p = new DefaultParser();
		String[] cmd = {"-a hello -d wow", "hello", "-a ",  "fuck"};
		Options os = new Options();
		os.addOption("a", true, "fff");
		os.addOption("d", true, "fuck ?");

		CommandLine cl = null;
		try {
			cl = p.parse(os, cmd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(cl.getArgList());
	}
}
