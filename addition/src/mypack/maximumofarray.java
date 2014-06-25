package mypack;

public class maximumofarray {
	int number[]={12,34,12,43,21};
	int max=number[0];
	public int findmax(){
		for(int i=0;i<number.length;i++){
			if(number[i]>max){
				max=number[i];
			}
		}
		return max;
	}
	

}
