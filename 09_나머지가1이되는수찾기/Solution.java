class Solution {
    public int solution(int n) {
        int answer = 0;
         int i=0;
        
        for(int j=0;j<n;j++){
            i++;
            if(n%i==1){
               return i;
           }
        }
        return answer;
    }
}
//�ڿ��� n�� �Ű������� �־����ϴ�. 
//n�� x�� ���� �������� 1�� �ǵ��� �ϴ� ���� ���� �ڿ��� x�� return �ϵ��� solution �Լ��� �ϼ����ּ���. 
//���� �׻� �������� ����� �� �ֽ��ϴ�.