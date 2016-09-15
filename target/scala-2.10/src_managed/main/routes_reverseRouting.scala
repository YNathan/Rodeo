// @SOURCE:C:/devl/work/Rodeo2016-07-28/preprod/conf/routes
// @HASH:9780cc1f3e5714107fc4d5883298870e9b28614b
// @DATE:Thu Sep 15 21:05:27 IDT 2016

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
package controllers {

// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
class Reversegetter {
    

// @LINE:13
def getGelts(userName:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "GET_GELT/" + implicitly[PathBindable[String]].unbind("userName", dynamicString(userName)))
}
                                                

// @LINE:11
def isUserNameAlreadyExist(USER_NAME:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "CHECK_USER_NAME/" + implicitly[PathBindable[String]].unbind("USER_NAME", dynamicString(USER_NAME)))
}
                                                

// @LINE:14
def getUsers(userName:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "GET_USERS/" + implicitly[PathBindable[String]].unbind("userName", dynamicString(userName)))
}
                                                

// @LINE:12
def isEmailAlreadyExist(Email:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "CHECK_EMAIL/" + implicitly[PathBindable[String]].unbind("Email", dynamicString(Email)))
}
                                                

// @LINE:23
def getGroupsUser(szUserName:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "GET_GROUPS_INFORMATION/" + implicitly[PathBindable[String]].unbind("szUserName", dynamicString(szUserName)))
}
                                                

// @LINE:21
def getUserInformation(szUserName:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "GET_USER_INFORMATION/" + implicitly[PathBindable[String]].unbind("szUserName", dynamicString(szUserName)))
}
                                                

// @LINE:22
def getOwnerGroupInformation(szUserName:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "GET_OWNER_INFORMATION/" + implicitly[PathBindable[String]].unbind("szUserName", dynamicString(szUserName)))
}
                                                

// @LINE:17
def checkIfUserIsDebter(szUserName:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "IS_DEBTER/" + implicitly[PathBindable[String]].unbind("szUserName", dynamicString(szUserName)))
}
                                                

// @LINE:10
def isLoginPermited(Username:String, Password:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "LOGIN/" + implicitly[PathBindable[String]].unbind("Username", dynamicString(Username)) + "/" + implicitly[PathBindable[String]].unbind("Password", dynamicString(Password)))
}
                                                
    
}
                          

// @LINE:26
// @LINE:25
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:16
// @LINE:15
class Reversesetter {
    

// @LINE:18
def confirm(szDebterName:String, szAmount:String, szEntitledName:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "CONFIRMATION/" + implicitly[PathBindable[String]].unbind("szDebterName", dynamicString(szDebterName)) + "/" + implicitly[PathBindable[String]].unbind("szAmount", dynamicString(szAmount)) + "/" + implicitly[PathBindable[String]].unbind("szEntitledName", dynamicString(szEntitledName)))
}
                                                

// @LINE:25
def uploadFile(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "upload")
}
                                                

// @LINE:19
def notConfirm(szDebterName:String, szAmount:String, szEntitledName:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "NOT_CONFIRMATION/" + implicitly[PathBindable[String]].unbind("szDebterName", dynamicString(szDebterName)) + "/" + implicitly[PathBindable[String]].unbind("szAmount", dynamicString(szAmount)) + "/" + implicitly[PathBindable[String]].unbind("szEntitledName", dynamicString(szEntitledName)))
}
                                                

// @LINE:20
def pay(szDebterName:String, szAmount:String, szEntitledName:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "PAY/" + implicitly[PathBindable[String]].unbind("szDebterName", dynamicString(szDebterName)) + "/" + implicitly[PathBindable[String]].unbind("szAmount", dynamicString(szAmount)) + "/" + implicitly[PathBindable[String]].unbind("szEntitledName", dynamicString(szEntitledName)))
}
                                                

// @LINE:16
def registerNewUser(userName:String, firstName:String, lastName:String, telephone:String, email:String, password:String, birthdate:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "REGISTER/" + implicitly[PathBindable[String]].unbind("userName", dynamicString(userName)) + "/" + implicitly[PathBindable[String]].unbind("firstName", dynamicString(firstName)) + "/" + implicitly[PathBindable[String]].unbind("lastName", dynamicString(lastName)) + "/" + implicitly[PathBindable[String]].unbind("telephone", dynamicString(telephone)) + "/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)) + "/" + implicitly[PathBindable[String]].unbind("password", dynamicString(password)) + "/" + implicitly[PathBindable[String]].unbind("birthdate", dynamicString(birthdate)))
}
                                                

// @LINE:26
def uploadFileWithName(szUserName:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "upload/" + implicitly[PathBindable[String]].unbind("szUserName", dynamicString(szUserName)))
}
                                                

// @LINE:15
def newGelt(szDebterName:String, szAmount:String, szEntitledName:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "INSERT_GELT/" + implicitly[PathBindable[String]].unbind("szDebterName", dynamicString(szDebterName)) + "/" + implicitly[PathBindable[String]].unbind("szAmount", dynamicString(szAmount)) + "/" + implicitly[PathBindable[String]].unbind("szEntitledName", dynamicString(szEntitledName)))
}
                                                
    
}
                          

// @LINE:28
class ReverseAssets {
    

// @LINE:28
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:9
// @LINE:8
// @LINE:7
class ReverseApplication {
    

// @LINE:9
def Main(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "MAIN")
}
                                                

// @LINE:8
def Register(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "REGISTER_PAGE")
}
                                                

// @LINE:7
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
package controllers.javascript {

// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
class Reversegetter {
    

// @LINE:13
def getGelts : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.getGelts",
   """
      function(userName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "GET_GELT/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userName", encodeURIComponent(userName))})
      }
   """
)
                        

// @LINE:11
def isUserNameAlreadyExist : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.isUserNameAlreadyExist",
   """
      function(USER_NAME) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "CHECK_USER_NAME/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("USER_NAME", encodeURIComponent(USER_NAME))})
      }
   """
)
                        

// @LINE:14
def getUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.getUsers",
   """
      function(userName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "GET_USERS/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userName", encodeURIComponent(userName))})
      }
   """
)
                        

// @LINE:12
def isEmailAlreadyExist : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.isEmailAlreadyExist",
   """
      function(Email) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "CHECK_EMAIL/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("Email", encodeURIComponent(Email))})
      }
   """
)
                        

// @LINE:23
def getGroupsUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.getGroupsUser",
   """
      function(szUserName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "GET_GROUPS_INFORMATION/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szUserName", encodeURIComponent(szUserName))})
      }
   """
)
                        

// @LINE:21
def getUserInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.getUserInformation",
   """
      function(szUserName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "GET_USER_INFORMATION/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szUserName", encodeURIComponent(szUserName))})
      }
   """
)
                        

// @LINE:22
def getOwnerGroupInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.getOwnerGroupInformation",
   """
      function(szUserName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "GET_OWNER_INFORMATION/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szUserName", encodeURIComponent(szUserName))})
      }
   """
)
                        

// @LINE:17
def checkIfUserIsDebter : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.checkIfUserIsDebter",
   """
      function(szUserName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "IS_DEBTER/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szUserName", encodeURIComponent(szUserName))})
      }
   """
)
                        

// @LINE:10
def isLoginPermited : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.getter.isLoginPermited",
   """
      function(Username,Password) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "LOGIN/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("Username", encodeURIComponent(Username)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("Password", encodeURIComponent(Password))})
      }
   """
)
                        
    
}
              

// @LINE:26
// @LINE:25
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:16
// @LINE:15
class Reversesetter {
    

// @LINE:18
def confirm : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.setter.confirm",
   """
      function(szDebterName,szAmount,szEntitledName) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "CONFIRMATION/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szDebterName", encodeURIComponent(szDebterName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szAmount", encodeURIComponent(szAmount)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szEntitledName", encodeURIComponent(szEntitledName))})
      }
   """
)
                        

// @LINE:25
def uploadFile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.setter.uploadFile",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload"})
      }
   """
)
                        

// @LINE:19
def notConfirm : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.setter.notConfirm",
   """
      function(szDebterName,szAmount,szEntitledName) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "NOT_CONFIRMATION/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szDebterName", encodeURIComponent(szDebterName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szAmount", encodeURIComponent(szAmount)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szEntitledName", encodeURIComponent(szEntitledName))})
      }
   """
)
                        

// @LINE:20
def pay : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.setter.pay",
   """
      function(szDebterName,szAmount,szEntitledName) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "PAY/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szDebterName", encodeURIComponent(szDebterName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szAmount", encodeURIComponent(szAmount)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szEntitledName", encodeURIComponent(szEntitledName))})
      }
   """
)
                        

// @LINE:16
def registerNewUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.setter.registerNewUser",
   """
      function(userName,firstName,lastName,telephone,email,password,birthdate) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "REGISTER/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userName", encodeURIComponent(userName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("firstName", encodeURIComponent(firstName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("lastName", encodeURIComponent(lastName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("telephone", encodeURIComponent(telephone)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("password", encodeURIComponent(password)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("birthdate", encodeURIComponent(birthdate))})
      }
   """
)
                        

// @LINE:26
def uploadFileWithName : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.setter.uploadFileWithName",
   """
      function(szUserName) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szUserName", encodeURIComponent(szUserName))})
      }
   """
)
                        

// @LINE:15
def newGelt : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.setter.newGelt",
   """
      function(szDebterName,szAmount,szEntitledName) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "INSERT_GELT/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szDebterName", encodeURIComponent(szDebterName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szAmount", encodeURIComponent(szAmount)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("szEntitledName", encodeURIComponent(szEntitledName))})
      }
   """
)
                        
    
}
              

// @LINE:28
class ReverseAssets {
    

// @LINE:28
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:9
// @LINE:8
// @LINE:7
class ReverseApplication {
    

// @LINE:9
def Main : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Main",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "MAIN"})
      }
   """
)
                        

// @LINE:8
def Register : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Register",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "REGISTER_PAGE"})
      }
   """
)
                        

// @LINE:7
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
package controllers.ref {


// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
class Reversegetter {
    

// @LINE:13
def getGelts(userName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.getGelts(userName), HandlerDef(this, "controllers.getter", "getGelts", Seq(classOf[String]), "GET", """""", _prefix + """GET_GELT/$userName<[^/]+>""")
)
                      

// @LINE:11
def isUserNameAlreadyExist(USER_NAME:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.isUserNameAlreadyExist(USER_NAME), HandlerDef(this, "controllers.getter", "isUserNameAlreadyExist", Seq(classOf[String]), "GET", """""", _prefix + """CHECK_USER_NAME/$USER_NAME<[^/]+>""")
)
                      

// @LINE:14
def getUsers(userName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.getUsers(userName), HandlerDef(this, "controllers.getter", "getUsers", Seq(classOf[String]), "GET", """""", _prefix + """GET_USERS/$userName<[^/]+>""")
)
                      

// @LINE:12
def isEmailAlreadyExist(Email:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.isEmailAlreadyExist(Email), HandlerDef(this, "controllers.getter", "isEmailAlreadyExist", Seq(classOf[String]), "GET", """""", _prefix + """CHECK_EMAIL/$Email<[^/]+>""")
)
                      

// @LINE:23
def getGroupsUser(szUserName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.getGroupsUser(szUserName), HandlerDef(this, "controllers.getter", "getGroupsUser", Seq(classOf[String]), "GET", """""", _prefix + """GET_GROUPS_INFORMATION/$szUserName<[^/]+>""")
)
                      

// @LINE:21
def getUserInformation(szUserName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.getUserInformation(szUserName), HandlerDef(this, "controllers.getter", "getUserInformation", Seq(classOf[String]), "GET", """""", _prefix + """GET_USER_INFORMATION/$szUserName<[^/]+>""")
)
                      

// @LINE:22
def getOwnerGroupInformation(szUserName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.getOwnerGroupInformation(szUserName), HandlerDef(this, "controllers.getter", "getOwnerGroupInformation", Seq(classOf[String]), "GET", """""", _prefix + """GET_OWNER_INFORMATION/$szUserName<[^/]+>""")
)
                      

// @LINE:17
def checkIfUserIsDebter(szUserName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.checkIfUserIsDebter(szUserName), HandlerDef(this, "controllers.getter", "checkIfUserIsDebter", Seq(classOf[String]), "GET", """""", _prefix + """IS_DEBTER/$szUserName<[^/]+>""")
)
                      

// @LINE:10
def isLoginPermited(Username:String, Password:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.getter.isLoginPermited(Username, Password), HandlerDef(this, "controllers.getter", "isLoginPermited", Seq(classOf[String], classOf[String]), "POST", """""", _prefix + """LOGIN/$Username<[^/]+>/$Password<[^/]+>""")
)
                      
    
}
                          

// @LINE:26
// @LINE:25
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:16
// @LINE:15
class Reversesetter {
    

// @LINE:18
def confirm(szDebterName:String, szAmount:String, szEntitledName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.setter.confirm(szDebterName, szAmount, szEntitledName), HandlerDef(this, "controllers.setter", "confirm", Seq(classOf[String], classOf[String], classOf[String]), "POST", """""", _prefix + """CONFIRMATION/$szDebterName<[^/]+>/$szAmount<[^/]+>/$szEntitledName<[^/]+>""")
)
                      

// @LINE:25
def uploadFile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.setter.uploadFile(), HandlerDef(this, "controllers.setter", "uploadFile", Seq(), "POST", """ Upload file to the server""", _prefix + """upload""")
)
                      

// @LINE:19
def notConfirm(szDebterName:String, szAmount:String, szEntitledName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.setter.notConfirm(szDebterName, szAmount, szEntitledName), HandlerDef(this, "controllers.setter", "notConfirm", Seq(classOf[String], classOf[String], classOf[String]), "POST", """""", _prefix + """NOT_CONFIRMATION/$szDebterName<[^/]+>/$szAmount<[^/]+>/$szEntitledName<[^/]+>""")
)
                      

// @LINE:20
def pay(szDebterName:String, szAmount:String, szEntitledName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.setter.pay(szDebterName, szAmount, szEntitledName), HandlerDef(this, "controllers.setter", "pay", Seq(classOf[String], classOf[String], classOf[String]), "POST", """""", _prefix + """PAY/$szDebterName<[^/]+>/$szAmount<[^/]+>/$szEntitledName<[^/]+>""")
)
                      

// @LINE:16
def registerNewUser(userName:String, firstName:String, lastName:String, telephone:String, email:String, password:String, birthdate:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.setter.registerNewUser(userName, firstName, lastName, telephone, email, password, birthdate), HandlerDef(this, "controllers.setter", "registerNewUser", Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]), "POST", """""", _prefix + """REGISTER/$userName<[^/]+>/$firstName<[^/]+>/$lastName<[^/]+>/$telephone<[^/]+>/$email<[^/]+>/$password<[^/]+>/$birthdate<[^/]+>""")
)
                      

// @LINE:26
def uploadFileWithName(szUserName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.setter.uploadFileWithName(szUserName), HandlerDef(this, "controllers.setter", "uploadFileWithName", Seq(classOf[String]), "POST", """""", _prefix + """upload/$szUserName<[^/]+>""")
)
                      

// @LINE:15
def newGelt(szDebterName:String, szAmount:String, szEntitledName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.setter.newGelt(szDebterName, szAmount, szEntitledName), HandlerDef(this, "controllers.setter", "newGelt", Seq(classOf[String], classOf[String], classOf[String]), "POST", """""", _prefix + """INSERT_GELT/$szDebterName<[^/]+>/$szAmount<[^/]+>/$szEntitledName<[^/]+>""")
)
                      
    
}
                          

// @LINE:28
class ReverseAssets {
    

// @LINE:28
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:9
// @LINE:8
// @LINE:7
class ReverseApplication {
    

// @LINE:9
def Main(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Main(), HandlerDef(this, "controllers.Application", "Main", Seq(), "GET", """""", _prefix + """MAIN""")
)
                      

// @LINE:8
def Register(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Register(), HandlerDef(this, "controllers.Application", "Register", Seq(), "GET", """""", _prefix + """REGISTER_PAGE""")
)
                      

// @LINE:7
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """""", _prefix + """""")
)
                      
    
}
                          
}
        
    