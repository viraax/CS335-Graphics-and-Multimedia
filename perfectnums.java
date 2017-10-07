//Petia Nikolova
public class perfectnums{

    public static int perfect(int number){
    	int start_num = 1; 
    	int end_num = 1000;

        int sum = 0;
        for (int i = 1; i < number; i++) 
	{
           	if ((number % i) == 0) 
		{
               	sum += i;
           	}	 
        }  
        if (sum == number)
	{           
        	return sum;
        }          
	else
		return(0);

    }	






public static void main(String[] args) {
    	int start_num;
	int end_num=1000;
	for (start_num = 1; start_num <  end_num; start_num++) 
	{ 
		perfect(start_num);
		if (perfect(start_num) != 0)
		{
			System.out.println(start_num);
    		}     	
	} 
	
    }

}

