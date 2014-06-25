package mypack;

public class Test3 {
	       
	        public static void main(String[] args) {
	                Foo foo = new Foo();
	                foo.setBar("Set by Main");
	 
	                update_V1(foo);
	                System.out.println(foo.getBar());
	               
	                foo.setBar("Set by Main");
	                update_V2(foo);
	                System.out.println(foo.getBar());
	               
	                updateString(foo.getBar());
	                System.out.println(foo.getBar());
	 
	        }
	 
	        private static void update_V1(Foo foo) {
	                foo.setBar("Update by V1");
	        }
	       
	        private static void update_V2(Foo foo) {
	                foo = new Foo();
	                foo.setBar("Update by V2");
	        }      
	       
	       
	        private static void updateString(String str) {
	                str = "Update by Update String";
	        }
	 
	}
	 
	 
	


