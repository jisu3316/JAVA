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
		System.out.println("파일명입력 기본 우리반:");
		try{
			fName= br.readLine();
			if(fName != null) fName=fName.trim();
			if(fName.length()==0) fName="우리반";
			fr= new FileReader(fName+".txt");
			brFile= new BufferedReader(fr);
		}catch(FileNotFoundException fi){
			System.out.println("파일명을 찾을 수 없습니다.");
			input();
		}catch(IOException ie){}
	}

	void L(){
		i=r.nextInt(30);
		try{
			for(int j =0;j<=i;j++){
				String line = brFile.readLine();
				if(j==i){
					System.out.println("당첨자:"+line);
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
