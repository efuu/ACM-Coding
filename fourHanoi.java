import java.util.Scanner;

public class fourHanoi {
	private static int numCase = 1;
	

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextInt())
		{
			
			int n = scan.nextInt();
			long[] data = new long[n+2];
			data[0] = -1;
			data[1] = 1;
			data[2] = 10;
			for(int i=2;i<=n;i++)
			{
				for(int j=1;j<i;j++)
					data[i]=findMin(data[i],2*data[i-j]+(long)Math.pow(2, j)-1);
				data[i+1] = 2*data[i]+(long)Math.pow(2, 1)-1;
			}
				System.out.println("Case "+numCase+": "+data[n]);
			numCase++;
			
		}
		scan.close();
	}
	
//	public static int fourH(int n)
//	{
//		if(n == 1)
//			return 1;
//		else
//		{
//			for(int i=1;i<=n;i++)
//				result = findMin(result,2*fourH(n-i)+(int)Math.pow(2,i)-1);			
//		}
//		return result;
//		
//	}
	public static long findMin(long data, long l)
	{
		if(l<0)
			return data;
		else if(data<l)
			return data;
		else
			return l;
	}

}
