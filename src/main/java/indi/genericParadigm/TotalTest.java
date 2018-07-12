package indi.genericParadigm;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TotalTest {

	@Test
	void go() {
		List<SpecificPool<? extends Task, ? extends Crawler>> mans = new LinkedList<>();
		HTTPSpecificPool pool = new HTTPSpecificPool();
		mans.add(pool);
	}

	class HTTPCrawler extends Crawler {

	}

	class Crawler {

	}

	class HTTPTask extends Task {

	}

	class Task {

	}

	class HTTPSpecificPool extends SpecificPool<HTTPTask, HTTPCrawler> {

	}

	public class SpecificPool<Task, Crawler> {

	}
}
