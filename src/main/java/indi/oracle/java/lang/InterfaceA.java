package indi.oracle.java.lang;

public interface InterfaceA {

    void go();

	void go2();
	
	default void go3() {
	    
	}
	
	default void go4() {
	}
	
	public class Test implements InterfaceA {

        @Override
        public void go() {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void go2() {
            // TODO Auto-generated method stub
        }
        
	}
}
