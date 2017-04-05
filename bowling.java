import java.util.Scanner;

public class bowling {
	public static final int PINS = 10;
	/**
	 * The total frame numbers of the game.
	 */
	private static int frameNum;
	/**
	 * The current frame number.
	 */
	private static int currentFrame;
	/**
	 * The total roll numbers of the game.
	 */
	private static int rollNum;
	/**
	 * The current roll number.
	 */
	private static int currentRoll;
	/**
	 * The score recorded in the game.
	 */
	private static int score;
	/**
	 * The number of pin numbers at the beginning of each roll.
	 */
	private static int pinNum;
	/**
	 * The record whether it is strike.
	 */
	private static int strike;
	/**
	 * The record whether it is spare.
	 */
	private static int spare;
	/**
	 * The record whether it has double bonds, which means the whether it is followed by two strikes.
	 */
	private static int doublebond;
	
	public static void roll(int pins)
	{
		if(pins<=pinNum)
		{
			if(currentRoll == 1)
			{
				if(currentFrame!=frameNum)
				{
					if(pins == PINS)
					{
						currentFrame++;
						score = score+pins;
						currentRoll = 1;
						pinNum = PINS;
						if(strike!=0)
						{
							if(doublebond!=0)
							{
								score = score +2*pins;
								strike--;
								doublebond = 0;
							}
							else
							{
								score = score + pins;
								strike--;
							}
						}
						if(spare!=0)
						{
							score = score +pins;
							spare--;
						}
						if(strike!=0)
							{
							strike++;
							doublebond = 1;
							}
						else
							{strike = strike +2;}
					}
					else
					{
						currentRoll++;
						pinNum = PINS -pins;
						score = score + pins;
						if(strike!=0)
						{
							if(doublebond!=0)
							{
								score = score +2*pins;
								strike--;
								doublebond = 0;
							}
							else
							{
								score = score +pins;
								strike--;
							}
							
						}
						if(spare!=0)
						{
							score = score +pins;
							spare--;
						}
						
					}
				}
				else
				{
					if(pins == PINS)
					{
						rollNum++;
						currentRoll++;
						pinNum = PINS;
						score = score +pins;
						if(strike!=0)
						{
							if(doublebond!=0)
							{
								score = score +2*pins;
								strike--;
								doublebond = 0;
							}
							else
							{
								score = score +pins;
								strike--;
							}
							
						}
						if(spare!=0)
						{
							score = score +pins;
							spare--;
						}
					}
					else
					{
						currentRoll++;
						pinNum = PINS -pins;
						score = score + pins;
						if(strike!=0)
						{
							score = score +pins;
							strike--;
						}
						if(spare!=0)
						{
							score = score +pins;
							spare--;
						}
					}
				}
			}
			else if(currentRoll == 2)
			{
				if(currentFrame!=frameNum)
				{
					currentFrame++;
					currentRoll =1;
					if(pins == pinNum)
					{
						spare++;
					}
					score = score +pins;
					if(strike!=0)
					{
						score = score +pins;
						strike--;
					}
					pinNum = PINS;
				}
				else if(rollNum!=3)
				{
					if(pins!=pinNum)
					{
						currentFrame++;
						pinNum = PINS;
						score = score + pins;
						if(strike!=0)
						{
							score = score +pins;
							strike--;
						}
					}
					else
					{
						rollNum++;
						currentRoll++;
						pinNum = PINS;
						score = score + pins;
						if(strike!=0)
						{
							score = score +pins;
							strike--;
						}
						spare = 1;
						
					}
				}
				else
				{
					if(pins == PINS)
					{
						currentRoll++;
						pinNum = PINS;
						score = score + pins;
						if(strike!=0)
						{
							score = score +pins;
							strike--;
						}
					}
					else
					{
						currentRoll++;
						pinNum = PINS - pins;
						score = score + pins;
						if(strike!=0)
						{
							score = score +pins;
							strike--;
						}
						
					}
				}
			}
			else
			{
				currentFrame++;
				score = score+pins;
				pinNum = PINS;
				
			}
			
		}
		
		
		
	}
	public static void reset()
	{
		currentFrame = 1;
		rollNum = 2;
		currentRoll = 1;
		score = 0;
		pinNum = PINS;
		strike = 0;
		spare = 0;
		doublebond = 0;
		
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int opponent;
		int caseNum = 1;
		while(true)
		{
			reset();
			opponent = scan.nextInt();
			if(opponent<0)
			{
				scan.close();
				break;
			}
			int countToE = 0;
			while(countToE<8)
			{
				int data = scan.nextInt();
				roll(data);
				if(data == 10||currentRoll==1)
					countToE++;
			}
				
//			System.out.println("score" +score);
//			System.out.println("strike"+strike);
//			System.out.println("spare" +spare);
//			System.out.println("doublebond" +doublebond);
//			System.out.println("currentRoll" +currentRoll);
			
			int rest = opponent - score + 1;
			if(rest<=10)
				System.out.println("Case "+caseNum+": 0 0 0 "+rest);				
			else if(rest<=20&&rest>10)
			{
				int temp = rest-10;
				System.out.println("Case "+caseNum+": 0 0 0 10 "+temp);
			}
			else if(rest<30)
			{
				int temp = rest-20;
				System.out.println("Case "+caseNum+": 0 0 10 10 "+temp);
			}
								
			else if(spare==1)
			{
				if(rest>70)
					System.out.println("Case "+caseNum+": impossible");
				else if(rest>=60&&rest<=70)
				{
					int temp = rest-60;	
					System.out.println("Case "+caseNum+": 10 10 10 "+temp);
				}
				else if(rest>=40&&rest<60)
				{
					int temp = rest-40;
					int half = temp/2;
					if(temp%2==0)
						System.out.println("Case "+caseNum+": 10 10 "+half+" 0");
					else
						System.out.println("Case "+caseNum+": 10 10 "+half+" 1");
				}
				else 
				{
					int temp = rest-30;
					System.out.println("Case "+caseNum+": 0 "+temp+" 10 10 10");
				}					
			}
			else if(doublebond==1)
			{
				if(rest>90)
					System.out.println("Case "+caseNum+": impossible");
				else if(rest>=80)
				{
					int temp = rest-80;
					System.out.println("Case "+caseNum+": 10 10 10 "+temp);
				}
				else if(rest>=70)
				{
					int temp = rest-60;
					int half = temp/2;
					if(temp%2==0)
						System.out.println("Case "+caseNum+": 10 10 "+half+" 0");
					else
						System.out.println("Case "+caseNum+": 10 10 "+half+" 1");
				}
				else if(rest>60)
				{
					int temp = rest%10;
					int resttemp = 10-temp;
					System.out.println("Case "+caseNum+": "+temp+" "+resttemp+" 10 10 10");
				}
				else if(rest==60)
				{
					System.out.println("Case "+caseNum+": 0 10 10 10 10");
				}
				else if(rest>=50)
				{
					int temp = rest-50;
					System.out.println("Case "+caseNum+": 10 0 10"+temp);
				}
				else 
				{
					int temp = rest-30;
					int half = (temp/2);
					System.out.println("Case "+caseNum+": 10 0 "+half);
				}
			}
			else
			{
				if(rest>60)
					System.out.println("Case "+caseNum+": impossible");
				else if(rest>=50)
				{
					int temp = rest-50;
					System.out.println("Case "+caseNum+": 10 10 10 "+temp);
				}
				else if(rest>=40)
				{
					int temp = rest - 40;
					System.out.println("Case "+caseNum+": 0 10 10 10 "+temp);
				}
				else if(rest>=30)
				{
					int temp = rest - 30;
					System.out.println("Case "+caseNum+": 0 "+temp+" 10 10 10");
				}
				else
				{
					int half = ((rest-30)/2);
					System.out.println("Case "+caseNum+": 10 10 "+half+" 0");
				}
					
			}
			caseNum++;
		}
		
		
	}

}
