import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DeckTest {
Deck dk=new Deck(),cp=new Deck();
	
	@Test
	public void testDeck() {
		dk=new Deck();
		cp=new Deck();
	}
	
	@Test 
	public void testDeckSize(){
		assertEquals(52, dk.DECK_SIZE);
	}

	@Test
	public void testDealFrom() {
		String deal=dk.dealFrom().toString();
		System.out.println(deal);
		assertEquals(true,"2C".equals(deal));
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false,dk.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(52,dk.size());
	}

	@Test
	public void testShuffle() {
		dk.shuffle();
		assertEquals(false,dk.toString().equals(cp.toString()));
	}

	@Test
	public void testToString() {
		assertEquals(true,dk.toString().equals(cp.toString()));
		dk.shuffle();
		assertEquals(false,dk.toString().equals(cp.toString()));
	}
	
	@Test 
	public void testMain() {
		dk.main(null);
	}

}
