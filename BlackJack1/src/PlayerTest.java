import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerTest {
	Player yadi=new Player("yadi",10,20);
	Draw draw = new Draw();
	
	@Test
	public void test1(){
		assertEquals(52,yadi.MAX_CARDS);
	}

	@Test
	public void testPeak() {
		yadi.dealTo(new Card(51,"--","--"));
		yadi.dealTo(new Card(9,"--","--"));
		assertEquals(true,yadi.peak().toString().equals("AS"));		//checking for peak card
	}

	@Test
	public void testDealTo() {
		yadi.dealTo(new Card(0,"--","--"));
		assertEquals(true,yadi.peak().toString().equals("2C"));
		yadi.reset(); 
		yadi.dealTo(new Card(2,"--","--"));
		System.out.println(yadi.peak().toString());
		assertEquals(false,yadi.peak().toString().equals("null"));	//checking the card dealt is peak card
	}

	@Test
	public void testReset() {
		yadi.dealTo(new Card(51,"--","--"));
		yadi.dealTo(new Card(9,"--","--"));
		yadi.reset();
		assertEquals(true,yadi.peak().toString().equals("AS"));		//checking reset
	}

	@Test
	public void testValue() {
		yadi.dealTo(new Card(12,"--","--"));				//checking the value of the card
		assertEquals(11,yadi.value());
		yadi.dealTo(new Card(11,"--","--"));
		assertEquals(21,yadi.value());
		yadi.dealTo(new Card(0,"--","--"));
		assertEquals(13,yadi.value());
		yadi.reset();
		assertEquals(0,yadi.value());
	}

	@Test
	public void testDraw() {
		for (int i = 0; i < yadi.N; i++)
			yadi.cards[i].draw(draw, yadi.x + i*25, yadi.y);
		yadi.dealTo(new Card(1,"--","--"));
		assertEquals(3,yadi.value());
	}

	@Test
	public void testToString() {
		assertEquals(false, yadi.toString().equals("yadi  (0)"));
		yadi.dealTo(new Card(51,"--","--"));
		assertEquals(false,yadi.toString().equals("yadi  (11)  AS"));		//checking the player name value and card name
	}

}
