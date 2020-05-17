
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CardTest {
	Card ace=new Card(12,"--","--");
	Card low=new Card(4,"--","--");
	Card high=new Card(8,"--","--");

	@Test
	public void testCard() { // is this necessary
		ace=new Card(12,"--","--");
		low=new Card(4,"--","--");
		high=new Card(8,"--","--");
	}
	
	@Test
	public void testIsAce(){
		assertEquals(ace.isAce(),true);// check for ace true case
		assertEquals(low.isAce(),false);// check for ace false case
	}
	@Test
	public void testRank() {
		assertEquals(ace.rank(),1);//check for ace 12
		assertEquals(low.rank(),6);// check for <8
		assertEquals(high.rank(),10);//check for >=8
	}
	
	@Test
	public void testConceal() {
		ace.conceal();
		assertEquals(true,ace.isConcealed);		//check whether ace is concealed
	}
	
	@Test
	public void testReveal() {
		low.reveal();
		assertEquals(false,low.isConcealed);	//check whether ace is revealed
	}

	@Test
	public void testToString() {
		//check for string value of each character
		assertEquals(ace.toString(),"AC");
		assertEquals(low.toString(),"6C");
		assertEquals(high.toString(),"TC");
	}
}
