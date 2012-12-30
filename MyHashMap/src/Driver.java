
public class Driver {
	public static void main(String args[]) {
		SimpleHashMap<String, String> myHashMap = new SimpleHashMap<String, String>();
		myHashMap.put("Test", "testvalue");
		myHashMap.put("Test", "testvalue1");
		myHashMap.put("Test2", "test2");
		
		System.out.println(myHashMap.get("Test"));
		System.out.println(myHashMap.get("Test2"));
		System.out.println(myHashMap.get("Test3"));
	}
}
