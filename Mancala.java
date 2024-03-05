import java.util.Scanner;

/* Christian Jobe 
 * Mancala 
 * This program with print a well made Mancala game board using methods, printf, and assign values using arrays
 */

public class Mancala
{
	static Scanner input;
	static final int BINS = 14; // number of bins in the game

	public static void main( String[ ] args )
	{
		input = new Scanner ( System.in );
		int[ ] beadArray; // Array that holds the values of the beads in each bin
		beadArray = new int[BINS];
		int winner = -1;
		int player = 1;

		System.out.println ( );
		startingArray ( beadArray );
		// printArray ( beadArray );
		System.out.println ( );
		showBoard ( beadArray );

		do
		{
			dropBeads ( beadArray, player );
			winner = gameOverCheck ( beadArray );
			player = player % 2 + 1;
		} while ( winner == -1 );

		showBoard ( beadArray );
		winner = gameOverCheck ( beadArray );

		System.out.println ( );

		if ( winner == 3 )
		{
			System.out.println ( "The game ends in a tie." );
		}
		else
		{
			System.out.println ( "Winner is player " + winner );
		}

		input.close ( );
	} // End of Main Method

	/********************************************************************************************************
	 * printArray outputs the array values above the board with a | in between the top and bottom row. Parameters integer
	 * [ ] beadArray Array that holds the values of the beads in each bin. Return nothing
	 */
	public static void printArray( int[ ] beadArray )
	{
		for ( int i = 0; i < BINS; i++ )
		{
			System.out.printf ( "%3d", beadArray[i] );
			if ( i == 6 )
			{
				System.out.print ( "  | " );
			}
		}
	} // End of printArray Method

	/*********************************************************************************************************
	 * makeSolidLine outputs a solid line of * when called. Parameters integer numStars. numStars is how long the line
	 * will be. Return nothing.
	 */

	public static void makeSolidLine( int numStars )
	{
		int count = 0; // LCV
		while ( count < numStars )
		{
			System.out.print ( "*" );
			count++;
		}

	} // End of makeSolidLine Method

	/********************************************************************************************************
	 * makeDottedLine outputs a dotted line of * when called. There are no parameters because its just printing a symbol
	 * over and over. Return nothing
	 */

	public static void makeDottedLine( )
	{
		int count = 0;
		while ( count < 9 )
		{
			System.out.print ( "*      " );
			count++;
		}
		System.out.println ( );
	} // End of makeDottedLine Method

	/********************************************************************************************************
	 * showBoard outputs the actual game board with the number of beads in each bin. Parameters integer [] beadArray
	 * Array that holds the values of the beads in each bin. Return nothing
	 */

	public static void showBoard( int[ ] beadArray )
	{
		makeSolidLine ( 57 );
		System.out.println ( );
		makeDottedLine ( );
		showTopRowNumbers ( );
		System.out.println ( );
		makeDottedLine ( );
		showTopBins ( beadArray );
		System.out.println ( );
		makeDottedLine ( );
		System.out.print ( "*  13  " );
		makeSolidLine ( 43 );
		System.out.println ( "   6  *" );
		makeDottedLine ( );
		showBottomRowNumbers ( );
		System.out.println ( );
		makeDottedLine ( );
		showBottomBins ( beadArray );
		System.out.println ( );
		makeDottedLine ( );
		makeSolidLine ( 57 );
		System.out.println ( );
	} // End of showBoard Method

	/********************************************************************************************************
	 * showTopBins outputs the number of beads in each of the 5 top bins inside the board. Parameters integer []
	 * beadArray Array that holds the values of the beads in each bin. Return nothing
	 */

	public static void showTopBins( int[ ] beadArray )
	{
		System.out.print ( "*      *" );
		for ( int i = 0; i < 6; i++ )
		{
			System.out.printf ( "%4d%3s", beadArray[i], "*" );
		}
		System.out.print ( "      *" );
	}// End of showTopBins Method

	/********************************************************************************************************
	 * showTopRowNumbers outputs the number of each bin 0-5. Parameters nothing. Return nothing
	 */

	public static void showTopRowNumbers( )
	{
		System.out.print ( "*      *" );
		for ( int i = 0; i < 6; i++ )
		{
			System.out.printf ( "%4d%3s", i, "*" );
		}
		System.out.print ( "      *" );
	} // End of showTopRowNumbers Method

	/********************************************************************************************************
	 * showBottomBins outputs the number of beads in each of the 5 bottom bins inside the board. Parameters integer []
	 * beadArray Array that holds the values of the beads in each bin. Return nothing
	 */

	public static void showBottomBins( int[ ] beadArray )
	{
		System.out.print ( "*" );
		for ( int i = 13; i > 5; i-- )
		{
			System.out.printf ( "%4d%3s", beadArray[i], "*" );
		}
	} // End of showBottomBins

	/********************************************************************************************************
	 * outputs the number of each bin 12-7. Parameters nothing. Return nothing
	 */

	public static void showBottomRowNumbers( )
	{
		System.out.print ( "*      *" );
		for ( int i = 12; i > 6; i-- )
		{
			System.out.printf ( "%4d%3s", i, "*" );
		}
		System.out.print ( "      *" );
	} // End of showBottomRowNumbers Method

	/********************************************************************************************************
	 * startingArray outputs nothing it assigns each beadArray value to 4 except beadArray[6] and beadArray[13] it sets
	 * to zero. Parameters integer [] beadArray Array that holds the values of the beads in each bin. Return nothing
	 */

	public static void startingArray( int[ ] beadArray )
	{

		for ( int i = 0; i < BINS; i++ )
		{
			beadArray[i] = 4;
		}
		beadArray[6] = 0;
		beadArray[13] = 0;
	} // End of startingArray

	/********************************************************************************************************
	 * dropBeads outputs the game sequence, when the player chooses a bin
	 */

	public static void dropBeads( int[ ] beadArray, int player )
	{
		int bin;
		int hand;
		int endBin = 6;
		int oppEndBin = 13;

		if ( player == 2 )
		{
			endBin = 13;
			oppEndBin = 6;
		}

		do
		{
			bin = getStartingBin ( beadArray, player );
			do
			{
				hand = beadArray[bin];
				beadArray[bin] = 0;
				while ( hand > 0 )
				{
					bin++;
					if ( bin == oppEndBin )
					{
						bin++;
					}
					if ( bin == 14 )
					{
						bin = 0;
					}
					beadArray[bin]++;
					hand--;
				}
			} while ( beadArray[bin] > 1 && bin != endBin );
			showBoard ( beadArray );
		} while ( gameOverCheck ( beadArray ) == -1 && bin == endBin );

	}

	/********************************************************************************************************
	 * startingArrayTest outputs nothing it assigns each bin to the values listed below in the method. Parameters integer
	 * [] beadArray Array that holds the values of the beads in each bin. Return nothing
	 */

	public static void startingArrayTest( int[ ] beadArray )
	{
		beadArray[0] = 0;
		beadArray[1] = 0;
		beadArray[2] = 1;
		beadArray[3] = 0;
		beadArray[4] = 0;
		beadArray[5] = 1;
		beadArray[6] = 0;
		beadArray[7] = 0;
		beadArray[8] = 0;
		beadArray[9] = 0;
		beadArray[10] = 0;
		beadArray[11] = 0;
		beadArray[12] = 1;
		beadArray[13] = 0;
	} // End of startingArrayTest

	public static int getStartingBin( int[ ] beadArray, int player )
	{
		int binChoice;
		int high = 5;
		int low = 0;

		if ( player == 2 )
		{
			high = 12;
			low = 7;
		}

		do
		{
			System.out.println ( "Player " + player + " Chose a bin between (" + low + "-" + high + ")" );
			binChoice = input.nextInt ( );
			if ( binChoice > high || binChoice < low || beadArray[binChoice] == 0 )
			{
				System.out.println ( "Invalid choice, chose again" );
			}
		} while ( binChoice > high || binChoice < low || beadArray[binChoice] == 0 );

		return binChoice;
	}

	/********************************************************************************************************
	 * gameOverCheck outputs the winner of the game, if no one has one yet it outputs a -1. Parameters integer
	 * winningPlayer is zero, integer [] beadArray Array that holds the values of the beads in each bin. Returns new
	 * winningPlayer value.
	 */

	public static int gameOverCheck( int[ ] beadArray )
	{
		int player1 = 0;
		int player2 = 0;
		int winner = -1;
		for ( int i = 0; i < 6; i++ )
		{
			player1 = beadArray[i] + player1;
		} // how many beads are in player1's bins

		for ( int i = 12; i > 6; i-- )
		{
			player2 = beadArray[i] + player2;
		} // how many beads are in player2's bins

		if ( player1 == 0 || player2 == 0 )
		{
			beadArray[6] = beadArray[6] + player2;
			beadArray[13] = beadArray[13] + player1;
			for ( int i = 0; i < 6; i++ )
			{
				beadArray[i] = 0;
			}
			for ( int i = 7; i < 13; i++ )
			{
				beadArray[i] = 0;
			}
			if ( beadArray[6] > beadArray[13] )
			{
				winner = 1;
			}
			else if ( beadArray[6] < beadArray[13] )
			{
				winner = 2;
			}
			else
			{
				winner = 3;
			}
		}

		return winner;

	}
}

/*
 * Problems: I had trouble getting the dotted lines to perfectly align with the dotted lines above and below, Bob helped
 * me. Had trouble figuring out the spacing with the numbers using printf, had some help from Aaron. Had help in class
 * 
 * 
 * 
 */
