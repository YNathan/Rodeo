package controllers;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import BL.setterBL;
import com.ning.http.multipart.FilePart;
import play.api.mvc.MultipartFormData;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.redirect;

/**
 * 
 * @author Yaacov
 *
 */
public class setter {
	private static setterBL setterBL = new setterBL();

	/**
	 * Inserting new debt without a group.
	 * 
	 * @param szDebterName
	 *            - the debtor name
	 * @param szAmount
	 *            - the amount for inserting
	 * @param szEntitledName
	 *            - the entitled name
	 * @return
	 */
	public static Result newGelt(String szDebterName, String szAmount, String szEntitledName) {
		if ((szDebterName != null) && (szAmount != null) && (szEntitledName != null)) {
			if (setterBL.insertGelt(szDebterName, szAmount, szEntitledName)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to insert the gelt");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty debter-name or an empty amount or an entitled-name!");
		}
	}

	/**
	 * Inserting new debt with a group.
	 *
	 * @param szDebterName
	 *            - the debtor name
	 * @param szAmount
	 *            - the amount for inserting
	 * @param szEntitledName
	 *            - the entitled name
	 * @return
	 */
	public static Result newTempGelt(String szDebterName, String szAmount, String szEntitledName, String szGroupName) {
		if ((szDebterName != null) && (szAmount != null) && (szEntitledName != null) && (szGroupName != null)) {
			if (setterBL.insertGelt(szDebterName, szAmount, szEntitledName, szGroupName)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to insert the temp gelt");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty debter-name or an empty amount or an entitled-name or an empty group-name!");
		}
	}

	/**
	 * Register new user into the system
	 * 
	 * @param szUserName
	 *            - user name
	 * @param szTelephone
	 *            - telephone
	 * @param szEmail
	 *            - email
	 * @param szPassword
	 *            - password
	 * @return
	 * @throws Exception
	 */
	public static Result registerNewUser(String szUserName, String szTelephone,
			String szEmail, String szPassword) throws Exception {
		// updateProfilePicture();
		// INFO
		play.Logger.info("<SETTER> Register new user : \n============================\nFor : =>>\nUser name : "
				+ szUserName +  "\nTelephone : "+ szTelephone + "\nEmail : " + szEmail + "\nPassword : " + szPassword
				+ "\n============================\n");

		System.out.println("[INFO] " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
				+ " <SETTER> Register new user : ");
		System.out.println("============================");
		System.out.println("For : =>>");
		System.out.println("User name : " + szUserName);
		System.out.println("Telephone : " + szTelephone);
		System.out.println("Email : " + szEmail);
		System.out.println("Password : " + szPassword);
		System.out.println("============================");

		if ((szUserName != null)  && (szTelephone != null)
				&& (szEmail != null) && (szPassword != null)) {
			if (setterBL.registerNewUser(szUserName, szTelephone, szEmail, szPassword)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to register");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty user-name or an empty first-name or an last-name or an telephone or an email or an password or an birthdate!");
		}
	}

	/**
	 * Confirm from a debtor if the data of this debt is true
	 * 
	 * @param szDebterName
	 *            - the debtor name
	 * @param szAmount
	 *            - the amount for inserting
	 * @param szEntitledName
	 *            - the entitled name
	 * @return
	 */
	public static Result confirm(String szDebterName, String szAmount, String szEntitledName) {
		play.Logger.info("<SETTER> Confiming");
		if ((szDebterName != null) && (szAmount != null) && (szEntitledName != null)) {
			if (setterBL.confirm(szDebterName, szAmount, szEntitledName)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to insert the gelt");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty debter-name or an empty amount or an entitled-name!");
		}

	}

	/**
	 * Confirm from a debtor in a group if the data of this debt is true
	 * 
	 * @param szDebterName
	 *            - the debtor name
	 * @param szAmount
	 *            - the amount for inserting
	 * @param szEntitledName
	 *            - the entitled name
	 * @return
	 */
	public static Result confirmDebt(String szDebterName, String szAmount, String szEntitledName, String szGroupName) {
		play.Logger.info("<SETTER> Confiming");
		if ((szDebterName != null) && (szAmount != null) && (szEntitledName != null) && (szGroupName != null)) {
			if (setterBL.confirmDebt(szDebterName, szAmount, szEntitledName, szGroupName)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to insert the gelt");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty debter-name or an empty amount or an empty entitled-name or an empty group name!");
		}

	}

	/**
	 * Disapprove debt from a debtor if the data of this debt is true
	 * 
	 * @param szDebterName
	 *            - the debtor name
	 * @param szAmount
	 *            - the amount for inserting
	 * @param szEntitledName
	 *            - the entitled name
	 * @return
	 */
	public static Result notConfirm(String szDebterName, String szAmount, String szEntitledName) {
		play.Logger.info("<SETTER> Not Confiming");
		if ((szDebterName != null) && (szAmount != null) && (szEntitledName != null)) {
			if (setterBL.notConfirm(szDebterName, szAmount, szEntitledName)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to insert the gelt");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty debter-name or an empty amount or an entitled-name!");
		}

	}

	/**
	 * Disapprove debt from a debtor in a group if the data of this debt is true
	 * 
	 * @param szDebterName
	 *            - the debtor name
	 * @param szAmount
	 *            - the amount for inserting
	 * @param szEntitledName
	 *            - the entitled name
	 * @return
	 */
	public static Result notConfirmDebt(String szDebterName, String szAmount, String szEntitledName,
			String szGroupName) {
		play.Logger.info("<SETTER> Not Confiming");
		if ((szDebterName != null) && (szAmount != null) && (szEntitledName != null) && (szGroupName != null)) {
			if (setterBL.notConfirm(szDebterName, szAmount, szEntitledName)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to insert the gelt");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty debter-name or an empty amount or an empty entitled-name or an empty group name!");
		}

	}

	/**
	 * pay a gelt
	 * 
	 * @param szDebterName
	 *            - the debtor name
	 * @param szAmount
	 *            - the amount for inserting
	 * @param szEntitledName
	 *            - the entitled name
	 * @return
	 */
	public static Result pay(String szDebterName, String szAmount, String szEntitledName) {
		play.Logger.info("<SETTER> " + szEntitledName + " Say that " + szDebterName
				+ " pay to hem the system send to delete the debt");
		if ((szDebterName != null) && (szAmount != null) && (szEntitledName != null)) {
			if (setterBL.pay(szDebterName, szAmount, szEntitledName)) {
				return play.mvc.Results.ok("true");
			} else {
				return badRequest("An internal error as ocurred when trying to insert the gelt");
			}

		} else {
			return badRequest(
					"Null pointer screw you! \nyou send your request with an empty debter-name or an empty amount or an entitled-name!");
		}

	}

	/**
	 * Get file(*can be a profile picture) from client and save in the server
	 * 
	 * @return
	 * @throws IOException
	 */
	public static play.mvc.Result uploadFile() throws IOException {
		if (updateProfilePicture()) {
			return redirect("assets/index.html");
		} else {
			flash("error", "Missing file");
			return badRequest();
		}
	}

	public static boolean updateProfilePicture(String szUserName) {
		play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
		play.mvc.Http.MultipartFormData.FilePart picture = body.getFile("file");
		if (picture != null) {
			java.io.File sourceFile = picture.getFile();
			File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + szUserName + ".jpg");
			try {
				play.Logger.info("<SETTER> save profile picture on file");
				setterBL.copyFile(sourceFile, dest);
			} catch (IOException e) {
				e.printStackTrace();
				play.Logger.info(e.getMessage());
			}
			return true;
		} else {
			flash("error", "Missing file");
			return false;
		}
	}

	public static boolean updateProfilePicture() {
		play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
		play.mvc.Http.MultipartFormData.FilePart picture = body.getFile("file");
		if (picture != null) {
			java.io.File sourceFile = picture.getFile();
			File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + picture.getFilename());
			try {
				play.Logger.info("<SETTER> save profile picture on file");
				setterBL.copyFile(sourceFile, dest);
			} catch (IOException e) {
				e.printStackTrace();
				play.Logger.info(e.getMessage());
			}
			return true;
		} else {
			flash("error", "Missing file");
			return false;
		}
	}

	/**
	 * Get file(*can be a profile picture) from client and save in the server
	 * 
	 * @return
	 * @throws IOException
	 */
	public static play.mvc.Result uploadFileWithName(String szUserName) throws IOException {
		if (updateProfilePicture(szUserName)) {
			return redirect("assets/index.html");
		} else {
			flash("error", "Missing file");
			return badRequest();
		}
	}

}
