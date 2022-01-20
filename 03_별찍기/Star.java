class Star 
{
	void m1(){
	for(int i =1;i<5;i++){
			for (int j =0;j<i ;j++ ){
				System.out.print("*");
				}
				System.out.println();
		}
				
	}
	void m2(){
		for(int i = 1;i<5;i++){
			for (int j = 4; j>0 ;j-- )
			{
				if(i<j){
					System.out.print(" ");
				}else{
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}


	void m3(){
	for(int i =1;i<=4;i++){
			for (int j =i;j<5 ;j++ ){
				System.out.print("*");
				}
				System.out.println();
		}
				
	}
void m4(){
			for(int i = 5;i>0;i--){
			for (int j = 4; j>0 ;j-- )
			{
				if(i>j){
					System.out.print("*");
				}else{
					
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}	
	public static void main(String[] args) 
	{
		Star s = new Star();
		
		s.m1();
		System.out.println();
		s.m2();
		System.out.println();
		s.m3();
		System.out.println();
		s.m4();
	}
}
