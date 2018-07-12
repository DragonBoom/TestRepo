package indi.reflect;

import org.junit.Test;

public class FinalizeTest {

	@Test
	public void test01() {
		FinalizeEscapeGC.SAVE_HOOK = new FinalizeEscapeGC();
		// part 1
		FinalizeEscapeGC.SAVE_HOOK = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (FinalizeEscapeGC.SAVE_HOOK == null) {
			System.out.println("he dead TVT");
		} else {
			System.out.println("wow he till alive!");
		}
		// part 2
		FinalizeEscapeGC.SAVE_HOOK = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (FinalizeEscapeGC.SAVE_HOOK == null) {
			System.out.println("he dead TVT");
		} else {
			System.out.println("wow he till alive!");
		}
		
	}
	
	private static class FinalizeEscapeGC {
		
		public static FinalizeEscapeGC SAVE_HOOK = null;
		
		@Override
		public final void finalize() throws Throwable {
			super.finalize();
			System.out.println("finalize method executed!");
			SAVE_HOOK = this;
		}
	}
}
