
import BL.setterBL;
import DB.getterDB;
import DB.setterDB;
import Entity.Gelt;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

/**
 *
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 *
 */
public class insertingTest {
	private static setterBL setterBL;

	@BeforeClass
	public static void beforeClass() {
		setterBL = new setterBL();
	}

	@Before
	public void before() {
		setterDB setterDB = new setterDB();
		ArrayList<Gelt> getlsToInput = new ArrayList<>();
		getlsToInput.add(new Gelt(2, 100, 3,1));
		getlsToInput.add(new Gelt(6, 150, 7,1));
		getlsToInput.add(new Gelt(9, 50, 10,1));
		setterDB.setGelts(getlsToInput);
	}

	// Shiboude section
	@Test
	public void insertEqualToZeroTest() {
		boolean isExpectedResaultFound = false;
		Gelt geltEqualToZero = new Gelt(1, 100, 2,1);
		Gelt expectedGelt = new Gelt(1, 100, 3,1);
		setterBL.insertGelt(geltEqualToZero.getDebterID(), geltEqualToZero.getAmount(),
				geltEqualToZero.getEntitledID());
		ArrayList<Gelt> geltLst = new getterDB().getGelts();
		for (Gelt currGelt : geltLst) {
			if ((currGelt.getDebterID() == expectedGelt.getDebterID())
					&& (currGelt.getAmount() == expectedGelt.getAmount())
					&& (currGelt.getEntitledID() == expectedGelt.getEntitledID())) {
				isExpectedResaultFound = true;
			}
			System.out.println(currGelt);
		}
		assertTrue(isExpectedResaultFound);
	}

	@Test
	public void insertPlusThanZeroTest() {
		boolean isExpectedAddedResaultFound = false;
		boolean isExpectedEditedResaultFound = false;
		Gelt geltPlusThanZero = new Gelt(8, 100, 6,1);
		Gelt expectedEditedGelt = new Gelt(6, 50, 7,1);
		Gelt expectedGelt = new Gelt(8, 100, 7,1);
		setterBL.insertGelt(geltPlusThanZero.getDebterID(), geltPlusThanZero.getAmount(),
				geltPlusThanZero.getEntitledID());
		ArrayList<Gelt> geltLst = new getterDB().getGelts();
		for (Gelt currGelt : geltLst) {
			// check the edited
			if ((currGelt.getDebterID() == expectedEditedGelt.getDebterID())
					&& (currGelt.getAmount() == expectedEditedGelt.getAmount())
					&& (currGelt.getEntitledID() == expectedEditedGelt.getEntitledID())) {
				isExpectedEditedResaultFound = true;
			}
			// check the added
			if ((currGelt.getDebterID() == expectedGelt.getDebterID())
					&& (currGelt.getAmount() == expectedGelt.getAmount())
					&& (currGelt.getEntitledID() == expectedGelt.getEntitledID())) {
				isExpectedAddedResaultFound = true;
			}
			System.out.println(currGelt);
		}
		assertTrue(isExpectedEditedResaultFound);		
		assertTrue(isExpectedAddedResaultFound);
	}

	@Test
	public void insertMinusThanZeroTest() {
		boolean isExpectedAddedResaultFound = false;
		boolean isExpectedEditedResaultFound = false;
		Gelt geltMinusThanZero = new Gelt(11, 100, 9,1);
		Gelt expectedEditedGelt = new Gelt(11, 50, 10,1);
		Gelt expectedGelt = new Gelt(11, 50, 9,1);
		setterBL.insertGelt(geltMinusThanZero.getDebterID(), geltMinusThanZero.getAmount(),
				geltMinusThanZero.getEntitledID());
		ArrayList<Gelt> geltLst = new getterDB().getGelts();
		for (Gelt currGelt : geltLst) {
			// check the edited
			if ((currGelt.getDebterID() == expectedEditedGelt.getDebterID())
					&& (currGelt.getAmount() == expectedEditedGelt.getAmount())
					&& (currGelt.getEntitledID() == expectedEditedGelt.getEntitledID())) {
				isExpectedEditedResaultFound = true;
			}
			// check the added
			if ((currGelt.getDebterID() == expectedGelt.getDebterID())
					&& (currGelt.getAmount() == expectedGelt.getAmount())
					&& (currGelt.getEntitledID() == expectedGelt.getEntitledID())) {
				isExpectedAddedResaultFound = true;
			}
			System.out.println(currGelt);
		}
		assertTrue(isExpectedEditedResaultFound);		
		assertTrue(isExpectedAddedResaultFound);
	}
	// Same personne section

	// Work
	@Test
	public void insertEqualZeroTestSamePersonne() {
		System.out.println("9,50,10");
		Gelt geltPlusThanZero = new Gelt(10, 50, 9,1);
		setterBL.insertGelt(geltPlusThanZero.getDebterID(), geltPlusThanZero.getAmount(),
				geltPlusThanZero.getEntitledID());
		ArrayList<Gelt> geltLst = new getterDB().getGelts();
		for (Gelt currGelt : geltLst) {
			System.out.println(currGelt);
		}
	}

	// Work
	@Test
	public void insertMoreThanZeroTestSamePersonne() {
		System.out.println("9,50,10");
		Gelt geltPlusThanZero = new Gelt(10, 25, 9,1);
		setterBL.insertGelt(geltPlusThanZero.getDebterID(), geltPlusThanZero.getAmount(),
				geltPlusThanZero.getEntitledID());
		ArrayList<Gelt> geltLst = new getterDB().getGelts();
		for (Gelt currGelt : geltLst) {
			System.out.println(currGelt);
		}
	}

	// Work
	@Test
	public void insertMinusThanZeroTestSamePersonne() {
		System.out.println("9,50,10");
		Gelt geltPlusThanZero = new Gelt(10, 100, 9,1);
		setterBL.insertGelt(geltPlusThanZero.getDebterID(), geltPlusThanZero.getAmount(),
				geltPlusThanZero.getEntitledID());
		ArrayList<Gelt> geltLst = new getterDB().getGelts();
		for (Gelt currGelt : geltLst) {
			System.out.println(currGelt);
		}
	}

	// Havroussess section
	@Test
	public void insertHavrousse() {
		System.out.println("9,50,10");
		Gelt geltPlusThanZero = new Gelt(9, 50, 10,1);
		setterBL.insertGelt(geltPlusThanZero.getDebterID(), geltPlusThanZero.getAmount(),
				geltPlusThanZero.getEntitledID());
		ArrayList<Gelt> geltLst = new getterDB().getGelts();
		for (Gelt currGelt : geltLst) {
			System.out.println(currGelt);
		}
	}
}
