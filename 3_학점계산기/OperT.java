class OperT 
{
	void m1(){
		int ���� = 100; 
		int ���� = 90; 
		int ���� = 50;
		int ���� = 70;
		int t = ���� + ���� + ���� + ����;
        int a = t/4; 
	    String grade = m2(a); 
		System.out.println("����: " + t + ", ���: "+a + ", ����: " + grade);
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

