import java.util.*;
import java.io.*;

class Lotto1{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	String fName;
	FileReader fr;
	Random r = new Random();
	int i;

	void input(){
		System.out.println("���ϸ��Է� �⺻ �츮��:");
		try{
			fName= br.readLine();
			if(fName != null) fName=fName.trim();
			if(fName.length()==0) fName="�츮��";
			fr= new FileReader(fName+".txt");
			brFile= new BufferedReader(fr);
		}catch(FileNotFoundException fi){
			System.out.println("���ϸ��� ã�� �� �����ϴ�.");
			input();
		}catch(IOException ie){}
	}

	void L(){
		i=r.nextInt(30);
		try{
			for(int j =0;j<=i;j++){
				String line = brFile.readLine();
				if(j==i){
					System.out.println("��÷��:"+line);
				}
			}
		}catch(IOException ie){}

	}
	public static void main(String[] args) 
	{
		Lotto1 lo = new Lotto1();
		lo.input();
		lo.L();
	}
}
