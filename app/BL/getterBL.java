package BL;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.MessagingException;


import DB.getterDB;
import Entity.Gelt;
import Entity.Group;
import Entity.User;
import play.Logger;
import play.mvc.BodyParser;

import static play.mvc.Http.Context.Implicit.request;

/**
 * Will do all the logic of the data who asked from the server
 *
 * @author Yaacov
 */
public class getterBL {
    getterDB getterDB = new getterDB();

    /**
     * @param szUserName - the user-name that the user send
     * @param szPassword - the password that the user send
     * @return true is the user-name and the password is correct
     */
    public boolean isLoginPermited(String szUserName, String szPassword) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get is login permited");

        ArrayList<User> userList = getterDB.getUsers();
        boolean isGreateLogin = false;
        for (User currUser : userList) {
            if (currUser.getUserName().equals(szUserName) && currUser.getPassword().equals(szPassword)) {
                isGreateLogin = true;
                mailBL mail = new mailBL();
                try {
                    mail.sendLoginSuccess(szUserName, request().remoteAddress());
                } catch (MessagingException e) {
                    Logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return isGreateLogin;
    }

    /**
     * Get all the debts from the data-base
     *
     * @return an array-list contain all debts
     */
    public ArrayList<Gelt> getGelts() {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get gelts");

        return getterDB.getGelts();
    }

    /**
     * @param szUserName - the name of the user who ask
     * @return an array that will contain the debts that concern the user
     */
    public ArrayList<Gelt> getGeltsByName(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get gelt by name");

        int nUserId = getIdByName(szUserName);
        ArrayList<Gelt> getls = getterDB.getGelts();
        ArrayList<Gelt> geltConcern = new ArrayList<>();
        for (Gelt currGelts : getls) {
            if (currGelts.getDebterID() == nUserId || currGelts.getEntitledID() == nUserId) {
                geltConcern.add(currGelts);
            }
        }
        return geltConcern;
    }

    /**
     * Get all debts that concern the user if he is a debter are user
     *
     * @param szUserName - the name of the user who ask
     * @return an array that will contain the debts that concern the user
     */
    public StringBuilder getGeltsByNameForOutput(String szUserName) {
        // This array will contain all the debts that concern the user
        ArrayList<Gelt> getlsConcerne = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int nUserId = getIdByName(szUserName);
        // Get all debts from the data-base
        ArrayList<Gelt> getls = getterDB.getGelts();
        // Looping over all debts from the data base and checking if there is
        // concerned debts
        for (Gelt currGelts : getls) {
            // check if the debter or the entitled is this user
            if ((currGelts.getDebterID() == nUserId) || (currGelts.getEntitledID() == nUserId)) {
                // Put the concerned debts in an array that contain the
                // concerned debts
                getlsConcerne.add(currGelts);
                // Print for the fun to the screen
                play.Logger.info("<BUSINESS_LOGIC> Get debt " + getNameById(currGelts.getDebterID()) + " : "
                        + currGelts.getAmount() + " : " + getNameById(currGelts.getEntitledID()));
            }
        }
        stringBuilder.append("{ \"debts\":[");

        Iterator<Gelt> curr = getlsConcerne.iterator();

        Gelt currentGelt = null;
        if (curr.hasNext()) {
            currentGelt = curr.next();
        }
        while (currentGelt != null) {
            if (currentGelt.getDebterID() == nUserId || currentGelt.getEntitledID() == nUserId) {
                stringBuilder.append(" {\"Debter\":\"" + getNameById(currentGelt.getDebterID()) + "\",");
                stringBuilder.append("\"Amount\":\"" + currentGelt.getAmount() + "\",");
                stringBuilder.append("\"Entitled\":\"" + getNameById(currentGelt.getEntitledID()) + "\"}");

                if (curr.hasNext()) {
                    stringBuilder.append(",");
                    currentGelt = curr.next();
                } else {
                    currentGelt = null;
                }
            }

        }

        stringBuilder.append(" ]}");
        return stringBuilder;
    }

    /**
     * @param szUserName - the user-name that will found for hem the id
     * @return the id of the user in the system
     */
    public int getIdByName(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get Id by name");

        int nUserId = -1;
        ArrayList<User> userLst = getterDB.getUsers();
        for (User currUser : userLst) {
            if (currUser.getUserName().equals(szUserName)) {
                nUserId = Integer.parseInt(currUser.getUserId());
            }
        }
        return nUserId;
    }

    /**
     * @param nUserId - the user-id that will found for hem the user-name
     * @return the name of the user in the system
     */
    public String getNameById(int nUserId) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get Name by Id");

        String szUserName = "";
        ArrayList<User> userLst = getterDB.getUsers();
        for (User currUser : userLst) {
            if (Integer.parseInt(currUser.getUserId()) == nUserId) {
                szUserName = currUser.getUserName();
            }
        }
        return szUserName;
    }

    /**
     * @param szUserName - the userName that we want to check if still exist
     * @return true if there still a user name like this in the system
     */
    public boolean isUserNameAlreadyExist(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get is user_name already exist");

        boolean isUserNameAlreadyExist = false;
        ArrayList<String> usersName = getterDB.getUserNames();
        for (String currName : usersName) {
            if (currName.equals(szUserName)) {
                isUserNameAlreadyExist = true;
            }
        }
        return isUserNameAlreadyExist;
    }

    /**
     * @param szEmail - the email that we want to check if still exist
     * @return true if there still a email like this in the system
     */
    public boolean isEmailAlreadyExist(String szEmail) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get is email already exist");

        boolean isEmailAlreadyExist = false;
        ArrayList<String> emails = getterDB.getEmails();
        for (String currEmail : emails) {
            if (currEmail.equals(szEmail)) {
                isEmailAlreadyExist = true;
            }
        }
        return isEmailAlreadyExist;
    }

    public ArrayList<String> getUsers(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get users");

        ArrayList<String> usersName = getterDB.getUserNames();
        usersName.remove(szUserName);
        return usersName;
    }

    public Date getDateByString(String szDate) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get date by string");

        String szYear = szDate.substring(0, 4);
        int nYear = Integer.parseInt(szYear);
        nYear -= 1900;
        String szMonth = szDate.substring(5, 7);
        int nMonth = Integer.parseInt(szMonth);
        nMonth -= 1;
        String szDay = szDate.substring(8);
        int nDay = Integer.parseInt(szDay);
        return new Date(nYear, nMonth, nDay);
    }

    /**
     * Check if there is debts concerning to this person Will return in a string
     * the debt and will delete from the data base the temp debts
     *
     * @param szUserName - the user that suppose to be the debter
     * @return a string with the amount and the entitled
     */
    public StringBuilder checkIfUserIsDebter(String szUserName) {

        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get user if is a debter");
        StringBuilder sbGeltToReturn = new StringBuilder();
        ArrayList<Gelt> tempGelts = getterDB.getTempGelts();
        Iterator<Gelt> itterGelt = tempGelts.iterator();

        boolean bWasFound = false;
        while ((itterGelt.hasNext()) && (!bWasFound)) {
            Gelt currGelt = itterGelt.next();
            if (currGelt.getDebterID() == getIdByName(szUserName)) {
                sbGeltToReturn.append("{ \"currDebt\":[ {\"Amount\":\"" + currGelt.getAmount() + "\",\"Entitled\":\""
                        + getNameById(currGelt.getEntitledID()) + "\"} ]}");

                bWasFound = true;
            }
        }
        return sbGeltToReturn;
    }

    /***
     *
     * @param szUserName the name of the user
     * @return all personal information about a user
     */
    public StringBuilder getUserInformation(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get user information with user name : " + szUserName);
        int nUserId = getterDB.getUserIdByName(szUserName);
        StringBuilder sbUserInformationToReturn = new StringBuilder();
        User userToReturn = getterDB.getUser(nUserId);
        sbUserInformationToReturn.append("{ \"user\":[ {\"user_name\":\"" + userToReturn.getUserName() + "\",\"first_name\":\"" + userToReturn.getfirstName() + "\",\"last_name\":\"" + userToReturn.getLastName() + "\",\"email\":\"" + userToReturn.getEmail() + "\",\"telephone\":\"" + userToReturn.getTelephone() + "\",\"password\":\"" + userToReturn.getPassword() + "\",\"birthdate\":\"" + userToReturn.getBirthday() + "\",\"user_id\":\"" + userToReturn.getUserId() + "\"} ]}");
        return sbUserInformationToReturn;
    }

    public StringBuilder getGroupsUser(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get the groups information for the user name : " + szUserName);
        int nUserId = getterDB.getUserIdByName(szUserName);
        StringBuilder sbUserInformationToReturn = new StringBuilder();
        ArrayList<Group> lstGroupsToReturn = getterDB.getGroupsUser(nUserId);
        sbUserInformationToReturn.append("{ \"groups\":[");

        Iterator<Group> groupItr = lstGroupsToReturn.iterator();
        Group currGroup = null;
        if (groupItr.hasNext()) {
            currGroup = groupItr.next();
        }
        while (currGroup != null) {
            sbUserInformationToReturn.append(" {\"group_name\":\"" + currGroup.getGroupName() + "\",");

            sbUserInformationToReturn.append("\"group_owner_name\":\"" + getterDB.getUserNameById(currGroup.getOwnerId()) + "\"}");

            if (groupItr.hasNext()) {
                sbUserInformationToReturn.append(",");
                currGroup = groupItr.next();
            } else {
                currGroup = null;
            }

        }
            sbUserInformationToReturn.append(" ]}");



        return sbUserInformationToReturn;
    }
}
