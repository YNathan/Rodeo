import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import BL.getterBL;
import DB.getterDB;
import DB.setterDB;
import Entity.Gelt;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

/**
 *
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {

	
	
	@Test
	public void simpleCheck() {
		int a = 1 + 1;
		assertThat(a).isEqualTo(2);
	}

	@Test
	public void renderTemplate() {
		Content html = views.html.index.render("Your new application is ready.");
		assertThat(contentType(html)).isEqualTo("text/html");
		assertThat(contentAsString(html)).contains("Your new application is ready.");
	}
	
	@Test
	public void checkUpdateRow()
	{
		setterDB setterDB = new setterDB();
		ArrayList<Gelt> gelts = new ArrayList<>();
		Gelt gelt = new Gelt(10,100,20,1);
		gelts.add(new Gelt(10,100,20,1));
		setterDB.setGelts(gelts);
		ArrayList<Gelt> expectedGelts = new getterDB().getGelts();
		for (Gelt currGelt : expectedGelts) {
			System.out.println(currGelt);
		}
		setterDB.updateGelt(gelt, new Gelt(20, 56, 12,1));
		expectedGelts = new getterDB().getGelts();
		for (Gelt currGelt : expectedGelts) {
			System.out.println(currGelt);
		}
		
	}
}
