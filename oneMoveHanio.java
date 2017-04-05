import java.util.ArrayList;
import java.util.Scanner;

public class oneMoveHanio {
	private static int n;
	private static long k;
	private static int numCase = 1;

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLong())
		{
			
			k = scan.nextLong();
		    n = scan.nextInt();
			if(k==0&&n==0)
			{
				scan.close();
				break;
			}
			char[][][] next = new char [2][3][2];
			if(n%2==0)
			{
				next[0][0][0] = 'B';
				next[0][0][1] = 'A';
				next[0][1][0] = 'A';
				next[0][1][1] = 'C';
				next[0][2][0] = 'C';
				next[0][2][1] = 'B';
				next[1][0][0] = 'C';
				next[1][0][1] = 'A';
				next[1][1][0] = 'A';
				next[1][1][1] = 'B';
				next[1][2][0] = 'B';
				next[1][2][1] = 'C';
			}
			else
			{
				next[1][0][0] = 'B';
				next[1][0][1] = 'A';
				next[1][1][0] = 'A';
				next[1][1][1] = 'C';
				next[1][2][0] = 'C';
				next[1][2][1] = 'B';
				next[0][0][0] = 'C';
				next[0][0][1] = 'A';
				next[0][1][0] = 'A';
				next[0][1][1] = 'B';
				next[0][2][0] = 'B';
				next[0][2][1] = 'C';
			}
			String s = Long.toBinaryString(k);
			ArrayList<Integer> value = new ArrayList<Integer>();
			for(int i=1;i<=s.length();i++)
			{
				if(s.substring(i-1, i).equals("1"))
					value.add(s.length()-i);
			}
			int item = value.get(value.size()-1)+1;
			int temp = value.get(0);
			value.remove(0);
			int times = 0;
			while(temp-item>=0)
			{
				times = times + (int)Math.pow(2, temp-item);
				if(value.isEmpty())
					break;
				else
				{
					temp = value.get(0);
					value.remove(0);
				}
					
			}
			times = times + 1;
			value.clear();
			
			System.out.println("Case "+numCase+": "+item+" "+next[item%2][times%3][0]
					+" "+next[item%2][times%3][1]);
			numCase++;
		}
		scan.close();
	}
}
