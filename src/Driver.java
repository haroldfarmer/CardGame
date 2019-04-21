import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
//Work in progress
public class Driver {
	static boolean won = false;
	static int count = 0;
	public static void main(String[] args) {
		
		
		// creates LinkedLists/Queues to hold cards, human player cards, computer cards
		// draw pile cards, and discard pile
		Queue<Card> cardLink = new LinkedList<Card>();
		ArrayList<Card> pOne = new ArrayList<Card>();
		ArrayList<Card> computer = new ArrayList<Card>();
		Queue<Card> drawPile = new LinkedList<Card>();
		ArrayDeque<Card> discardPile = new ArrayDeque<Card>();
		
		//creates the  Ace, Jack, King, Queen cards
		cardLink.add(new Card(0, 11, "Jack", "Hearts"));
		cardLink.add(new Card(0, 11, "Jack", "Spades"));
		cardLink.add(new Card(0, 11, "Jack", "Diamonds"));
		cardLink.add(new Card(0, 11, "Jack", "Clubs"));
		
		cardLink.add(new Card(0, 12, "Queen", "Hearts"));
		cardLink.add(new Card(0, 12, "Queen", "Spades"));
		cardLink.add(new Card(0, 12, "Queen", "Diamonds"));
		cardLink.add(new Card(0, 12, "Queen", "Clubs"));
		
		cardLink.add(new Card(0, 13, "King", "Hearts"));
		cardLink.add(new Card(0, 13, "King", "Spades"));
		cardLink.add(new Card(0, 13, "King", "Diamonds"));
		cardLink.add(new Card(0, 13, "King", "Clubs"));
		
		cardLink.add(new Card(0, 14, "Ace", "Hearts"));
		cardLink.add(new Card(0, 14, "Ace", "Spades"));
		cardLink.add(new Card(0, 14, "Ace", "Diamonds"));
		cardLink.add(new Card(0, 14, "Ace", "Clubs"));
		
		//creates the numbered cards
		for (int i = 2; i <= 10; i++) {
			cardLink.add(new Card(i, 0, "", "Hearts"));
			cardLink.add(new Card(i, 0, "", "Spades"));
			cardLink.add(new Card(i, 0, "", "Diamonds"));
			cardLink.add(new Card(i, 0, "", "Clubs"));
		}
		Collections.shuffle((List<?>) cardLink);
		// decides whos turn it is.
		while(won == false)
			if(count == 0 ) {
				takeTurn(discardPile, drawPile, pOne, cardLink);
				count++;
			}
			else {
				checkDraw(drawPile, discardPile);
				if(count % 2 == 0) {
					takeTurn(discardPile, drawPile, pOne, cardLink);
					count++;
				}
				else {
					computerTurn(discardPile, drawPile, pOne, cardLink);
					count++;
				}
			}
			
		
		
		
	}
	
	public static void takeTurn(ArrayDeque<Card> discardPile, Queue<Card> draw, ArrayList<Card> p1, Queue<Card> s) {
		
		Scanner in = new Scanner(System.in);
		Card a = null;
		if(count == 0) {
			Card one = s.poll();
			Card two = s.poll();
			Card three = s.poll();
			Card four = s.poll();
			
			
			p1.add(one);
			p1.add(two);
			p1.add(three);
			p1.add(four);
			System.out.println("Your cards are:");
			System.out.println("      " + one);
			System.out.println("      " + two);
			System.out.println("      " + three);
			System.out.println("      " + four);
			
			// takes the remainder cards and puts them in drawPile
			
			while(s.size() > 0) {
				Card c = s.poll();
				draw.offer(c);
				
			}
			
			System.out.println("The discard pile is currently empty -- you must draw a card");
			
			Card five = draw.poll();
			p1.add(five);
			System.out.println("You drew the " + five);
			
			System.out.println("Now your cards are: ");
			System.out.println("      1. " + one);
			System.out.println("      2. " + two);
			System.out.println("      3. " + three);
			System.out.println("      4. " + four);
			System.out.println("      5. " + five);
			
			System.out.println("Wich one will you discard?");
			try {
				int input = in.nextInt();
				while(input < 0 || input > 5) {
					System.out.println("Must enter a number 1 - 5: ");
					System.out.println("Wich one will you discard?");
					input = in.nextInt();
				}
				
				if(input == 1) {
					 a = p1.remove(0);
		
				}
				else if( input == 2) {
					a = p1.remove(1);
					
				}
				else if( input == 3) {
					a = p1.remove(2);
					
				}
				else if( input == 4) {
					a = p1.remove(3);
					
				}
				else  {
					a = p1.remove(4);
					
				}
				discardPile.add(a);
			} catch(InputMismatchException e) {
				System.out.println("Must Enter 1 through 5.");
			}
			
		}
		else {
			display(p1);
			
			if(discardPile.peek() !=null) {
				System.out.println("The top card in the discard pile is the " + discardPile.peek());
				System.out.println("Do you want to pick up the " + discardPile.peek() + " (1) or draw a card (2)?");
			}
			else {
				System.out.println("The discard pile is empty.");
				
			}
			
			
			
			try {
				int choice = in.nextInt();
				
				while(choice != 1 && choice != 2) {
					System.out.println("Must enter 1 or 2");
					choice = in.nextInt();
				}
				if(choice == 1) {
						Card b = discardPile.pop();
						p1.add(b);
					}
					else if (choice == 2){
						Card b = draw.poll();
						p1.add(b);
						
					}
					
			} catch(InputMismatchException ex) {
				System.out.println("Must enter 1 or 2.");
			}
			catch(EmptyStackException exx) {
				System.out.println("Stack is Empty");
			}
			
			
			 display(p1);
			

			System.out.println("Wich one will you discard?");
			try {
				int input = in.nextInt();
				while(input < 0 || input > 5) {
					System.out.println("Must enter a number 1 - 5: ");
					System.out.println("Wich one will you discard?");
					input = in.nextInt();
				}
				
				if(input == 1) {
					 a = p1.remove(0);
					
				}
				else if( input == 2) {
					a = p1.remove(1);
					
				}
				else if( input == 3) {
					a =p1.remove(2);
					
				}
				else if( input == 4) {
					a = p1.remove(3);
					
				}
				else  {
					a = p1.remove(4);
					
				}
				discardPile.add(a);
			} catch(InputMismatchException e) {
				System.out.println("Must Enter 1 through 5.");
			}
			
			checkWin(p1);
			
			
		}
		
		
	}
	public static void computerTurn(ArrayDeque<Card> discardPile, Queue<Card> draw, ArrayList<Card> p1, Queue<Card> s) {
		
	}
	
	public static void display(ArrayList<Card> p1) {
		System.out.println("Your cards are: ");
		for(int i = 0; i < p1.size(); i++) {
			int c = i + 1;
			System.out.println("     " + c  +". "+ p1.get(i));
		}
	}
	
	public static void checkWin(ArrayList<Card> user) {
		System.out.println("Im in");
		int q = user.get(0).getValue();
		int w = user.get(0).getlabelValue();
		int check = 1;
		for(int i = 1; i < user.size(); i++) {
			int e = user.get(i).getValue();
			int n = user.get(i).getlabelValue();
			
			if(e != 0 && e == q ) {
				check++;
				if(check == 4) {
					won = true;
					System.out.println("You Won!");
				}
				
			}
			else if(w != 0 && n == w) {
				check++;
				if(check == 4) {
					won = true;
					System.out.println("You Won!");
				}
			}
			
		}
	}
	
	public static void checkDraw(Queue<Card> draw, ArrayDeque<Card> discard) {
		
		if(draw.isEmpty()) {
			Collections.shuffle((List<?>) discard);
			while(discard.size() > 0) {
				Card c = discard.pop();
				draw.offer(c);
				
			}
			
		}
		
			
	}
	

}
