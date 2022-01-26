import java.util.Arrays;
import java.util.ArrayList;
class Wkrdmstnwprj {
    public void Wkrdmstnwprj() {
		int[] arr ={4,3,2,1};
        if(arr.length == 1) {
            arr[0] = -1;
            
        }
        int[] copyArr = arr.clone();
        Arrays.sort(copyArr);
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < copyArr.length; i++){
            if(arr[i] != copyArr[0])
                list.add(arr[i]);
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
			System.out.println(answer[i]);
        }
        
    }
	public static void main (String args[]){
		Wkrdmstnwprj wk =new Wkrdmstnwprj();
		wk.Wkrdmstnwprj();
	}
}