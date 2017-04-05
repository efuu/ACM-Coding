import java.util.ArrayList;
import java.util.Scanner;

public class lightSwitches {

	private static int numCase = 1;
	
//	public static int euler(int n)
//	{
//		int res = n, a = n;
//		for(int i=2;i*i<=a;i++)
//		{
//			if(a%i==0)
//			{
//				res = res/i*(i-1);
//				while(a%i==0)
//					a/=i;
//			}
//		}
//		if(a>1)
//			res = res/a*(a-1);
//		return res;
//		
//	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLong())
		{
			
			long n = scan.nextLong();
			long t = scan.nextLong();
			long b = scan.nextLong();
//			if(n>100000000||t>100000000||b>100000000)
//			{
//				System.out.println("Case "+numCase+": Off");
//				numCase++;
//				continue;
//			}
			long count = 0;
//			System.out.println("Case "+numCase+" n is "+n+" t is "+t+" b is "+b);
		while(t>0)
		{
			long tempCount = 0;
			double sqrt = Math.sqrt(b);
			ArrayList<Long> data = new ArrayList<Long>();
			if(t<sqrt)
			{
				for(int i=1;i<t;i++)
				{
					if(b%i==0)
						count++;
				}
			}
			else
			{
				for(long i=1;i<sqrt;i++)
				{
					if(b%i==0)
						data.add(i);
				}
				tempCount = data.size();
				for(int i=data.size()-1;i>=0;i--)
				{
					if(b/data.get(i)<=t)
						tempCount++;
					else
						break;
				}
				if(b%sqrt==0)
					count = tempCount + 1;
				else
					count = tempCount;
			}	
			t = t -n;
		}
			long result = count%2;
			if(result==0)
				System.out.println("Case "+numCase+": Off");
			else
				System.out.println("Case "+numCase+": On");
			
		numCase++;
		}
		scan.close();
	}
}
