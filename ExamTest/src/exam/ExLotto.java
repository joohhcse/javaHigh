package exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//go to NH bank of Center
public class ExLotto {

		public static void main(String[] args) {
			
			ExLotto lotto = new ExLotto();
			Scanner sc = new Scanner(System.in);
			
			System.out.println("로또번호 추출 개수 입력 : ");
			int gameCnt = sc.nextInt();
			
			for(int i = 1 ; i <= gameCnt ; i++)
			{
				System.out.println( i + "번째 로또번호 : " + lotto.lottoNumbers());
			}
			

		}
		
		String lottoNumbers()
		{
			List<Integer> lottoNum = new ArrayList<Integer>();

			// add lotto num in List
			for(int i = 1 ; i <= 45 ; i++)
			{
				lottoNum.add(i);	//1,2,3,4,5,,,
			}
			
			//shuffle
			Collections.shuffle(lottoNum);
			
			int[] lottoNums = new int [6];
			for (int i = 0 ; i < 6 ; i++)
			{
				lottoNums[i] = lottoNum.get(i);
			}
			
			//정렬	//sort
			Arrays.sort(lottoNums);
			
			return Arrays.toString(lottoNums);
		}
		
		
}
