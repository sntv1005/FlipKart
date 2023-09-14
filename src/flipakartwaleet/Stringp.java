package flipakartwaleet;

public class Stringp {
	
	public static void main(String[] args) {
		String s= "abc";
		String t="ahbgdc";
		char[] ch1= s.toCharArray();
		char[] ch2=t.toCharArray();
		CharSequence ch3= t.subSequence(0, 5);
		
		for(int i=0;i<ch1.length;i++) {
			for(int j=0;j<ch2.length;j++) {
				if(ch1[i]==ch2[i]) {
					
					
				}
			}
			System.out.println(ch2[i]);
			System.out.println(ch3);
		}
		
		
	}

}
