import java.util.Arrays;
import java.util.*;
class soflacktns{
	public long solution() {
        long answer = 0;
		long n =118372;
		String str=Long.toString(n);
		String str1[] = str.split("");
		Arrays.sort(str1, Collections.reverseOrder());
		String s="";
		for(int i=0;i<str.length();i++){
			s+= String.join("", str1[i]);
		
		}
		System.out.println(s);
		answer=Long.parseLong(s);
        return answer;
    }
	public static void main(String[] args) 
	{
		soflacktns so =new soflacktns();
		so.solution();
	}
}
