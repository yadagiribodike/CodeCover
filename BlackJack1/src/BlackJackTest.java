import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;


public class BlackJackTest {

	BlackJack blackJack = new BlackJack();
	Player dealer = blackJack.dealer;
	Player gambler = blackJack.gambler;

	@Test
	public void test1() {
		BlackJack blackJack1 = new BlackJack();
		dealer=blackJack1.dealer;
		gambler=blackJack1.gambler;
		assertEquals(blackJack1.draw.width,blackJack1.WIDTH); //checking the object data member values
	}
	
	@Test
	public void test2() {								//checking the button status
		assertTrue(!blackJack.hitButton.isEnabled());
		assertTrue(!blackJack.stickButton.isEnabled());
		assertTrue(blackJack.dealButton.isEnabled());
	}
	
	@Test
	public void test3() {
		blackJack.deal();
		if(blackJack.deck == null || blackJack.deck.size() < 15){
			assertEquals("Shuffling",blackJack.status.getText());		//checking the status value
		}
	}
	
	@Test
	public void test4() {
		blackJack.deck=new Deck();
		gambler.reset();
		blackJack.hit(gambler);
		blackJack.hit(gambler);
		assertEquals(2, gambler.N); // checking that after gambler has 2 cards after hitting twice
	}
	
	@Test
	public void test5() {
		gambler.reset();
		dealer.reset();
		blackJack.deal();
		assertEquals(2,gambler.N);
		assertEquals(2,dealer.N);	//checking that deal() gives 2 card both to player & gambler
	}
	
	@Test
	public void test6() {
		gambler.reset();
		dealer.reset();
		blackJack.deal();
		assertTrue(blackJack.dealer.peak().isConcealed);	//checking that dealer top card is concealed
	}

	@Test
	public void test7() {
		blackJack.deck=new Deck();
		gambler.reset();
		dealer.reset();
		blackJack.deal();
		blackJack.checkWinner();					//Checking the checkwinner against the status
		if(gambler.value() >  21){
			assertEquals("Gambler busts",blackJack.status.getText());
		}else if(dealer.value()  >  21){
			assertEquals("Dealer busts",blackJack.status.getText());
		}else if(gambler.value() == dealer.value()){
			assertEquals("Push",blackJack.status.getText());
		}else if(gambler.value() >  dealer.value()){
			assertEquals("Gambler wins",blackJack.status.getText());
		}else 
			assertEquals("Dealer wins",blackJack.status.getText());
	}
	
	@Test
	public void test8() {
		ActionEvent e=new ActionEvent(blackJack.hitButton,1,"");
		blackJack.deck=new Deck(); //as deck is not init unitll deal()

		e.setSource(blackJack.hitButton);
		blackJack.actionPerformed(e);
		if (blackJack.gambler.value() < 17) {
			while(dealer.value() < 17)
				blackJack.hit(dealer);
			blackJack.checkWinner();
			assertTrue(!blackJack.hitButton.isEnabled());
			assertTrue(!blackJack.stickButton.isEnabled());
			assertTrue(blackJack.dealButton.isEnabled());
		}
	}
	
	@Test
	public void test9(){
		ActionEvent e=new ActionEvent(blackJack.stickButton,1,"");
		blackJack.deck=new Deck(); //as deck is not init unitll deal()

		e.setSource(blackJack.stickButton);
		blackJack.actionPerformed(e);
		if (blackJack.gambler.value() > 21) {
			assertTrue(!blackJack.hitButton.isEnabled());
			assertTrue(!blackJack.stickButton.isEnabled());
			assertTrue(blackJack.dealButton.isEnabled());
		}
	}
	
	@Test
	public void test10(){
		ActionEvent e=new ActionEvent(blackJack.dealButton,1,"");
		blackJack.deck=new Deck(); //as deck is not init unitll deal()

		e.setSource(blackJack.dealButton);
		blackJack.actionPerformed(e);
		gambler.reset();
		dealer.reset();
		blackJack.deal();
		assertTrue(blackJack.hitButton.isEnabled());
		assertTrue(blackJack.stickButton.isEnabled());
		assertTrue(!blackJack.dealButton.isEnabled());
	}
	
	@Test
	public void test11(){
		blackJack.main(null);
		assertEquals(400, blackJack.WIDTH);
	}
}
