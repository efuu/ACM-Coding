import java.math.BigDecimal;
import java.util.Scanner;
import java.text.DecimalFormat; 
public class CentroidPoint {
	private static int numCase = 1;
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			
			int n = scan.nextInt();
			if(n<0)
			{
				scan.close();
				break;
			}
			double sumX = 0, sumY = 0, sumM = 0;
			for(int i=0;i<n;i++)
			{
				double x = scan.nextInt();
				double y = scan.nextInt();
				double mass = scan.nextInt();
				sumX = sumX + x*mass;
				sumY = sumY + y*mass;
				sumM = sumM + mass;
			}
			double Mx = sumX/sumM;
			double My = sumY/sumM;
			BigDecimal b = new BigDecimal(Mx);  
			DecimalFormat    df   = new DecimalFormat("######0.00"); 
			double _Mx = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			b = new BigDecimal(My);  
			double _My = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			System.out.println("Case "+numCase+ ": "+df.format(_Mx)+" "+df.format(_My));
			numCase++;
		}
	}

}
