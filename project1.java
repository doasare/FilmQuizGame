/* AUTHOR DAVID OSSEO-ASARE 

Mini Project

*/
import java.io.*;
import javax.swing.*; 

//Film Quiz a game created for multiple players
//Results are displayed
//If qualified the winner will be added TopPlayerList
class FilmQuiz
{ 
	
	public static void main (String[] param)
	{
		try{	
		    FilmQuiz();
			System.exit(0);
		} catch(Exception e ){ //something went wrong?
			JOptionPane.showMessageDialog(null, "Game Ended Abruptly, Try Again");
			System.exit(0);
		}
		
	}// End of Main
	
	//Intialise Game
	public static void  FilmQuiz()

	{		
		int playerAmount = Integer.parseInt(JOptionPane.showInputDialog("How many players are player?"));
		String[]name = new String[playerAmount];//How many players?
	
		for(int k=0; k<playerAmount; k++)// Ask for all players names.
		{
			name[k]=JOptionPane.showInputDialog ("Player " + (k+1) + ": What is your name? ");
		}


		String [] question = new String [5];// Stored Questions 
	
		question [0] ="How many SAW films are out?";
		question [1] ="Who plays the character Harry Potter in every Harry Potter films?";
		question [2] ="Who is the main character in '8 Mile' the film?";
		question [3] ="What are the names of the little men in Charlie And The Chocolate factory?";
		question [4] ="What type of movie is final destination?";

		String [] answer = new String [5];// stored Answer
		answer [0] ="7";
		answer [1] ="daniel radcliffe";
		answer [2] ="eminem";
		answer [3] ="umpa lumpa";
		answer [4] ="horror";

		int [] point = new int [5];
		point [0] =20;
		point [1] =50;
		point [2] =60;
		point [3] =80;
		point [4] =35;

		int [] result = new int [playerAmount];//Stores Results for players
	
		String ans;

			// start the game display each questions to specific player and collect results
			for (int i=0; i<name.length; i++) //each player
			{
				JOptionPane.showMessageDialog(null, name[i]+ ": Please answer the following questions");		
				for(int j=0; j<5; j++)// ask all questions available
				{		
					ans = JOptionPane.showInputDialog (name[i]+" Question"+j+": \n"+question[j]);
					if(ans.equalsIgnoreCase(answer[j]))//checks answer
					{	
						result[i]= result [i] + point [j]; // add points
					}
				}
			}


		String report = "";

		System.out.println("\nGame Results");
			for (int i=0; i<name.length; i++)//prints out results in console and displays to users
			{
				System.out.println(name[i] + "\t:"+result[i]+"point");
				report += name[i] + "\t:"+result[i]+"point\n";
			}
		JOptionPane.showMessageDialog(null, report);
		save(name[0], result[0]);//runs method to save highest result from current game in txt file

	}//close film quiz method
	
	public static void save(String playerName, int playerResult)
	{
		try{
			String fileName = "FilmQuizResults.txt";
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			// wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
				
			int i = 0;
			String[] pNames = new String[11];//new String[] name storage
			int[] pResults = new int[11];//new int[] result storage
			boolean added = false;
			String name;
			while((name = bufferedReader.readLine()) != null && i < 11) //read each line and stores data and ends if line empty
			{
				String uPersonResult = bufferedReader.readLine();
				int pR = Integer.parseInt(uPersonResult);
				//check if user result can be added to highest of all time 
				if (pR < playerResult && added == false){
					pNames[i] = playerName;
					pResults[i] = playerResult;
					pNames[i+1] = name;
					pResults[i+1] = pR;
					added = true;
					i++;
				} else {
					pNames[i] = name;
					pResults[i] = pR;
				}

				i++;
			}
			
			if (added){
				sortResults(pNames, pResults); // double check sorted correctly
			}

		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	public static void sortResults(String[] names, int[] results)
	{
	    int i, j, tempResult;  //be sure that the temp variable is the same "type" as the array
	    String tempName;
	    for ( i = 0; i < results.length - 1; i ++ )  
	    {
	        for ( j = i + 1; j < results.length; j ++ )
	        {
	            if( results[ i ] < results[ j ] )         //sorting into descending order
	            {
                    tempResult = results[ i ];   //swapping
	                results[ i ] = results[ j ];
	                results[ j ] = tempResult; 
	                tempName = names[ i ];   //swapping
	                names[ i ] = names[ j ];
	                names[ j ] = tempName; 

	            }           
	        }
	    }
	    writeNewTopScores(names, results);
	}//close sortResults method

	public static void writeNewTopScores (String[] names, int[] results) 
	{
		try
		{
	    	File file =new File("Top10PlayerList.txt");// access file
	 		file.delete();
	    	file.createNewFile(); // create a new file
			FileWriter fileWritter = new FileWriter(file.getName(),true); //writer
	    	BufferedWriter bufferWritter = new BufferedWriter(fileWritter); //reader

	    	for (int i= 0; i< 10 ; i++){
	    		String line = names[i] +"\n"+ results[i];
	    		bufferWritter.write(line);//write content to file.
	    		bufferWritter.newLine();//new line
	    	}
	    	bufferWritter.close();//close buffer
	 
			System.out.println("Results file saved!");
			System.out.println("\nHighest Score Of All Time!");
	 
	    }
    		catch(Exception e)
		{
	    		e.printStackTrace();
		}
	}//close writeNewTopScore method

}	
