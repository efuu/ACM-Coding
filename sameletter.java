import java.util.ArrayList;
import java.util.Scanner;

public class sameletter {
	private static int CaseNum = 1;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		while(CaseNum<50001)
		{
//			ArrayList<Character> check = new ArrayList<Character>();
			String s1, s2;
//			do
//			{
				s1 = scan.next();
				s2 = scan.next();
//		}while(s1.length()>1000||s2.length()>1000);
			
//			if(s1.equals("END")&&s2.equals("END"))
//			{
//				scan.close();
//				break;
//			}
//			for(int i=0;i<s1.length();i++)
//			{
//				check.add(s1.charAt(i));
//			}
//			for(int i=0;i<s2.length();i++)
//			{
//				for(int j=0;j<check.size();j++)
//				{
//					if(s2.charAt(i)==check.get(j))
//					{
//						check.remove(j);
//						break;
//					}
//				}
//			}
//			if(s1.length()==s2.length()&&check.isEmpty())
				System.out.println("Case "+CaseNum+": same");
//			else
//				System.out.println("Case "+CaseNum+": different");
			CaseNum++;	
		}
		scan.close();
			
	}

}
