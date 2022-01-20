class  학원 {
	String 공부;
	학원(){
		공부="java";
	}
	학원(String 공부){
		this.공부=공부;
	}
	void 생각하게한다(){
		System.out.println("생각하게한다");
	}
}

class 학생{
	int 나이;
	학원 학원1,학원2;
	학생(int 나이){
		this.나이 = 나이;
}
void 간다(){
	학원1 = new 학원();
	학원2 = new 학원("열심히");
	 System.out.println("학원을 간다");
	}
	void 배운다(){
		System.out.println("나는 "+학원1.공부+"를 배운다");
		System.out.println("나는 "+학원2.공부+" 한다");
	}
}

class 선생님{
	int 실력;
	int 외모;
	선생님(int 실력,int 외모){
		this.실력= 실력;
		this.외모= 외모;
	}
	void 가르쳐주신다(){
		System.out.println("가르쳐 주신다");
	}
}
class 김지수{
	public static void main(String args[]){
		학생 나는 = new 학생(26);
		System.out.print("나이가 " + 나는.나이+"세인 나는 ");
		나는.간다();
		System.out.print("학원은 나를 ");
		나는.학원1.생각하게한다();
		나는.배운다();
		
		선생님 선 = new 선생님(1,1);
		System.out.print("실력도 " +선.실력 + "등이시고 외모도 "+선.외모+"등이신 김형수 선생님이 ");
		선.가르쳐주신다();

		}
}