package indi.algorithm;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UniqueNumberTest {
	private int[] bucket = new int[100];
	private short order = 0;

	@BeforeEach
	void initBucket() {
		for (int i = 0; i < bucket.length; i++) {
			bucket[i] = 0;
		}
	}

	@AfterEach
	void check() {
		System.out.println("check case " + order + ":");
		boolean fail = false;
		int[] tmp = new int[bucket.length];
		for (int i = 0; i < bucket.length; i++) {
			if (tmp[bucket[i]] != 0) {
				fail = true;
				break;
			}
			tmp[bucket[i]] = bucket[i];
		}
		if (fail) {
			System.out.println("	current case fail");
		} else {
			System.out.println("	current case success");
		}
	}

	@Test
	void case1() {
		Random r = new Random();
		for (int i = 0; i < bucket.length; i++) {
			bucket[i] = r.nextInt(bucket.length);
		}
	}

	@Test
	void case2() {

	}
}
