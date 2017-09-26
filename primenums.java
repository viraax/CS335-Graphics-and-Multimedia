//Petia Nikolova
import java.util.Arrays;
public class primenums{

	public static void main(String[] args){

	boolean[] myArray = new boolean[1000];
	Arrays.fill(myArray, true);
	int arr_len = myArray.length;

		for(int i=2; i < arr_len ; i++){
			for(int j=2; j < i; j++){
				if(i%j == 0){
					myArray[i] = false;
				}
			} 			
		}
		
		for(int k=2; k< arr_len; k++){
			if(myArray[k] == true){
				System.out.println(k);
			}
		}
	}
}
