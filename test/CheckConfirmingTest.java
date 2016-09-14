

import BL.getterBL;
import BL.setterBL;
import DB.getterDB;
import controllers.getter;

import org.junit.*;

/**
 *
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 *
 */
public class CheckConfirmingTest {

	@Test
	public void checkResponce() {
		setterBL setterBl = new setterBL();
		getterBL getterBl = new getterBL();
		setterBl.insertTempGelt("Y.Nathan", "100", "david");
		StringBuilder sb = getterBl.checkIfUserIsDebter("Y.Nathan");
		System.out.println(sb);

		 sb = getterBl.checkIfUserIsDebter("Y.Nathan");
		System.out.println("scond \n" + sb);
	}

	@Test
	public void renderTemplate() {
		getter getter = new getter();
		System.out.println(getter.getGelts("Y.Nathan"));

	}
}
