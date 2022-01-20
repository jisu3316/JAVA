class OperT 
{
	void m1(){
		int 국어 = 100; 
		int 수학 = 90; 
		int 과학 = 50;
		int 영어 = 70;
		int t = 국어 + 수학 + 과학 + 영어;
        int a = t/4; 
	    String grade = m2(a); 
		System.out.println("총점: " + t + ", 평균: "+a + ", 학점: " + grade);
	}
	String m2(int a){
		if(a>=90) return "A";
		else if(a>=80) return "B";
		else if(a>=70) return "C";
		else if(a>=60) return "D";
		else return "F";
	}
	public static void main(String[] args) {
		OperT op = new OperT();
		op.m1();
	}
}

