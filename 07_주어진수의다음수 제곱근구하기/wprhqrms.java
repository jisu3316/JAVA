class wprhqrms {
    public long solution(){
		long n = 121;
        long answer = 0;
        long sqrt = (long) Math.sqrt(n);
		if(n == Math.pow(sqrt,2)) {
		answer= (long)(Math.pow(sqrt+1, 2)); 
		System.out.println(answer);
		}
			return answer-1; 
			
       
    }
	public static void main(String[] args){
		wprhqrms w = new wprhqrms();
		w.solution();
	}
}