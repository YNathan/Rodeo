package controllers;

import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BL.getterBL;
import akka.event.Logging;
import play.Logger;
import play.libs.Json;

import static play.mvc.Http.Context.Implicit.request;

/**
 * @author Yaacov
 */
public class getter extends Controller {
    private static final Lock lock = new ReentrantLock();
    private static getterBL getterBL = new getterBL();

    public static Result isLoginPermited(String szUserName, String szPassword) {
        Logger.info("<GETTER> Clien in IP : " + request().remoteAddress() + " Trying to connect");
        System.out.println("<GETTER> Clien in IP : " + request().remoteAddress() + " Trying to connect");

        if ((szUserName != null) && (szPassword != null)) {
            if (getterBL.isLoginPermited(szUserName, szPassword)) {
                play.Logger.info("<GETTER> " + szUserName + " is login from IP: " + request().remoteAddress());
                System.out.println("<GETTER> " + szUserName + " is login from IP: " + request().remoteAddress());
                return play.mvc.Results.ok("true");
            }
            System.out.println("[INFO] Error when trying to connect with wrong user-name or password.\nUSER_NAME : '"
                    + szUserName + "'\nPASSWORD : '" + szPassword + "'");
            return play.mvc.Results.badRequest("The user-name or the password is incorrect");
        } else {
            return play.mvc.Results.badRequest(
                    "Null pointer screw you! \nyou send your request with an empty user-name or an empty password!");
        }
    }

    public static Result isUserNameAlreadyExist(String szUserName) {
        if (szUserName != null) {
            if (getterBL.isUserNameExist(szUserName)) {
                return play.mvc.Results.badRequest("user name already exist");
            }
            return play.mvc.Results.ok("true");
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result isEmailAlreadyExist(String szEmail) {
        if (szEmail != null) {
            if (getterBL.isEmailAlreadyExist(szEmail)) {
                return play.mvc.Results.badRequest("email already exist");
            }
            return play.mvc.Results.ok("true");
        } else {
            return play.mvc.Results.badRequest("Null pointer screw you! \nyou send your request with an empty email!");
        }

    }

    public static Result getGelts(String szUserName) {
        if (szUserName != null) {
            String szResponce = getterBL.getGeltsByNameForOutput(szUserName).toString();
            play.Logger.info(szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result getGroupGelts(String szUserName,String szGroupName) {
        if (szUserName != null && szGroupName != null) {
            String szResponce = getterBL.getGeltsByNameAndGroupsForOutput(szUserName,szGroupName).toString();
            play.Logger.info(szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name or an group id!");
        }
    }

    public static Result getUsers(String szUserName) {
        if (szUserName != null) {
            return play.mvc.Results.ok(Json.toJson(getterBL.getUsers(szUserName)));
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result getUserNameOfGroup(String szUserName,String szGroupName) {
        if (szUserName != null) {
            return play.mvc.Results.ok(Json.toJson(getterBL.getUserNameOfGroups(szUserName,szGroupName)));
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name or a group id!");
        }
    }


    public static Result checkIfUserIsDebter(String szUserName) {
        play.Logger.info("<GETTER> " + szUserName + " ask if is debter");
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER> " + szUserName + " in IP : " + request().remoteAddress() + " : ask if is debter");
        if (szUserName != null) {
            String szResponce = getterBL.checkIfUserIsDebter(szUserName).toString();
            play.Logger.info("<GETTER> <DATA>" + szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result getUserInformation(String szUserName) {
        play.Logger.info("<GETTER> " + szUserName + " ask information on the user");
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER> " + szUserName + " in IP : " + request().remoteAddress() + " : ask information on user name : " + szUserName);
        if (szUserName != null) {
            String szResponce = getterBL.getUserInformation(szUserName).toString();
            play.Logger.info("<GETTER> <DATA>" + szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result getGroupsUser(String szUserName) {
        play.Logger.info("<GETTER> " + szUserName + " ask groups information on the user");
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER> " + szUserName + " in IP : " + request().remoteAddress() + " : ask groups information on user name : " + szUserName);
        if (szUserName != null) {
            String szResponce = getterBL.getGroupsUser(szUserName).toString();
            play.Logger.info("<GETTER> <DATA>" + szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }
    public static Result getOwnerGroupInformation(String szUserName) {
        play.Logger.info("<GETTER> " + request().remoteAddress() + " ask owner information on the user : "+ szUserName);
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER> " + szUserName + " in IP : " + request().remoteAddress() + " : ask information on owner : " + szUserName);
        if (szUserName != null) {
            String szResponce = getterBL.getOwnerGroupInformation(szUserName).toString();
            play.Logger.info("<GETTER> <DATA>" + szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }
    public static Result getUserGroupsName(String szUserName) {
        play.Logger.info("<GETTER> " + request().remoteAddress() + " ask groups name of the user : "+ szUserName);
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER> " + szUserName + " in IP : " + request().remoteAddress() + " : groups name of user : " + szUserName);
        if (szUserName != null) {
            ArrayList<String> szResponce = getterBL.getUserGroupsName(szUserName);
            play.Logger.info("<GETTER> <DATA>" + szResponce);
            return play.mvc.Results.ok(Json.toJson(szResponce));
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }
}
