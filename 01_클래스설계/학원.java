class  �п� {
	String ����;
	�п�(){
		����="java";
	}
	�п�(String ����){
		this.����=����;
	}
	void �����ϰ��Ѵ�(){
		System.out.println("�����ϰ��Ѵ�");
	}
}

class �л�{
	int ����;
	�п� �п�1,�п�2;
	�л�(int ����){
		this.���� = ����;
}
void ����(){
	�п�1 = new �п�();
	�п�2 = new �п�("������");
	 System.out.println("�п��� ����");
	}
	void ����(){
		System.out.println("���� "+�п�1.����+"�� ����");
		System.out.println("���� "+�п�2.����+" �Ѵ�");
	}
}

class ������{
	int �Ƿ�;
	int �ܸ�;
	������(int �Ƿ�,int �ܸ�){
		this.�Ƿ�= �Ƿ�;
		this.�ܸ�= �ܸ�;
	}
	void �������ֽŴ�(){
		System.out.println("������ �ֽŴ�");
	}
}
class ������{
	public static void main(String args[]){
		�л� ���� = new �л�(26);
		System.out.print("���̰� " + ����.����+"���� ���� ");
		����.����();
		System.out.print("�п��� ���� ");
		����.�п�1.�����ϰ��Ѵ�();
		����.����();
		
		������ �� = new ������(1,1);
		System.out.print("�Ƿµ� " +��.�Ƿ� + "���̽ð� �ܸ� "+��.�ܸ�+"���̽� ������ �������� ");
		��.�������ֽŴ�();

		}
}