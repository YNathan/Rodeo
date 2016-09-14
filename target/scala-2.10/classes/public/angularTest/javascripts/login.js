app.controller('Login', ['$scope', '$http', '$filter', '$state','$location','$anchorScroll', function ($scope, $http, $filter, $state,$location,$anchorScroll) {
    $scope.userName = "a";
    $scope.firstName = "a";
    $scope.lastName = "a";
    $scope.telephone = "0527879217";
    $scope.email = "a";
    $scope.password = "a";
    $scope.birthdate = 01 / 01 / 1990;
    $scope.userName = 'hello';
    $scope.email = "la@gmail.com";

    $scope.Username = 'Y.Nathan';
    $scope.Password = 'a';


    $scope.gotoBottom = function(location) {
        // set the location.hash to the id of
        // the element you wish to scroll to.
        $location.hash(location);
        // call $anchorScroll()
        $anchorScroll();
    };
    
    $scope.login = function () {
        var userName = $scope.Username;
        var password = $scope.Password;

        // Check if the name exist
        $http({
            method: 'POST',
            url: '/LOGIN/' + userName + '/' + password
        }).then(function successCallback(response) {
            if (response.data == "true") {
                swal({
                    title: "Wellcom!",
                    text: "Enjoy our service.",
                    imageUrl: "images/dolar.jpg"
                });
                setUserNameCookie("username", userName);
                // Go to the main application
                $state.go('Main');
            }
        }, function error(response) {
            swal(response.data);
        });
    };

    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }
    $scope.register = function () {
        $state.go('Register');
    };



    // Will input the user name into the cookie
    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }

    $scope.upload = function () {
        $.ajax({
            url: "/upload",
            type: "POST",
            data: $form.serialize(),
            success: function (response) {
                log("aaaaaaaaa");
            }
        });
    }


    
    $scope.register = function () {
        var userName = $scope.userName;
        var email = $scope.email;

        // Check if the name exist
        $http({
                method: 'GET',
                url: '/CHECK_USER_NAME/' + userName
            })
            .then(function successCallback(response) {
                    // Check if the email exist
                    $http({
                            method: 'GET',
                            url: '/CHECK_EMAIL/' + email
                        })
                        .then(function successCallback(response) {
                                var birthdateOrder = $filter('date')($scope.birthdate, 'yyyy-MM-dd');
                                setUserNameCookie("username", userName);
                                // Register new user
                                $http({
                                    method: 'POST',
                                    url: '/REGISTER/' + userName + '/' + $scope.firstName + '/' + $scope.lastName + '/' + $scope.telephone + '/' + $scope.email + '/' + $scope.password + '/' + birthdateOrder
                                }).then(
                                    function successCallback(response) {
                                        swal("Register successful!", "Wellcom!", "success");
                                        $state.go('Main')
                                    },
                                    function errorCallback(response) {
                                        alert(response.data);
                                    });
                            },
                            function errorCallback(response) {
                                $scope.email = '';
                                alert(response.data);
                            });
                },
                function errorCallback(response) {
                    alert(response.data);
                    $scope.userName = '';
                });
    };
    }]);
app.controller('mainControl', ['$scope', '$http', '$state', '$interval', function ($scope, $http, $state, $interval) {
    //$interval.clear(interval)
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + getCookie("username");
    $scope.DebterName = "No user detected";
    var bIsNumberChanged = false;
    var bIsNameChanged = false;
    // For the users-name
    $scope.data = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            name: 'default'
        }
    };

    // For the debts
    $scope.debts = {
        availableDebtsOptions: [],
        selectedDebtsOption: {
            id: '1',
            Debtes: 'default'
        }
    };

    // Get users name
    $http({
            method: 'GET',
            url: '/GET_USERS/' + getCookie("username")
        })
        .then(function successCallback(response) {
            var nCounter = 0;
            var tempArr = [];
            angular.forEach(response.data, function (value, key) {
                itemName = {
                    id: key,
                    name: value
                }
                tempArr.push(itemName);
                $scope.data.availableOptions = tempArr;
            }, $scope.data);
        });


    // Get debts conserning the user
    $http.get("/GET_GELT/" + getCookie("username"))
        .then(function (response) {
            $scope.debts = response.data.debts;
        });

    $interval(checkIfNeedConfirming, 10000);

    function checkIfNeedConfirming() {
        // Get if confirm
        $http({
                method: 'GET',
                url: '/IS_DEBTER/' + getCookie("username")
            })
            .then(function successCallback(response) {
                $scope.currDebt = response.data.currDebt[0];

                var amount = response.data.currDebt[0].Amount;
                var entitled = response.data.currDebt[0].Entitled;
                if (response.data != "")
                    var bIsConfirmed = confirm("do you confirm that you need to give " + amount + " to m. " + entitled)
                if (bIsConfirmed) {
                    // confirm a debts
                    // :szAmount/:szEntitledName
                    $http.post("/CONFIRMATION/" + getCookie("username") + '/' + amount + '/' + entitled)
                        .then(function (response) {
                            $scope.debts = response.data.debts;

                        });
                } else {
                    // dont confirm a debts
                    $http.post("/NOT_CONFIRMATION/" + getCookie("username") + '/' + amount + '/' + entitled)
                        .then(function (response) {
                            $scope.debts = response.data.debts;
                        });
                }
            });
    }

    // Logic methods section

    // Logout
    $scope.logout = function()
    {
        setUserNameCookie("username", null);
        sweetAlert("Good By! :-)");
         // Go to the main application
        $state.go('Login');
    }
    // Pay 
    $scope.pay = function (debtor, entitled, amount) {
            if (getCookie("username") == entitled) {
                // pay a debt
                $http.post("/PAY/" + debtor + '/' + amount + '/' + entitled)
                    .then(function (response) {
                        sweetAlert("Debt between M." + debtor + " and M." + entitled + " with amount of: " + amount + " was deleted successfully");

                    });
            } else {
                sweetAlert("only the entitled can confirm that was payed");
            }


        }
        // Get value from the cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
    }

    // Just check if there is a user name
    if (getCookie("username") == null) {
        // Go to the main application
        $state.go('Login');
    }

 // Will input the user name into the cookie
    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }

    // When client select a debter will update the text area field of debter
    $scope.updateBeforeInput = function () {
            bIsNumberChanged = true;
            if (bIsNameChanged) {
                $scope.alertBeforeInput = "do you confirm that " + $scope.data.selectedOption.name + " need to give you " + $scope.Amount + " dollars ?";
            } else {
                $scope.alertBeforeInput = "please select a debter";
            }
        }
    // When client select an amount will update the text area field of debter
    $scope.updateName = function () {
        bIsNameChanged = true;
        if (bIsNumberChanged) {
            $scope.updateBeforeInput();
        } else {
            $scope.alertBeforeInput = "please select an amount";
        }
    }



    // Send the new debt to the server
    $scope.submit = function () {
        if ((!bIsNameChanged)) {
            swal("please select a debter")
        } else if (!bIsNumberChanged) {
            swal("please select an amount")
        } else {
            // send the request to the server with the new debt
            $http({
                method: 'POST',
                url: '/INSERT_GELT/' + $scope.data.selectedOption.name + '/' + $scope.Amount + '/' + getCookie("username")
            }).then(
                function successCallback(response) {
                    if (response.data == "true") {
                        swal("gelt waiting for confirming");
                    }
                },
                function error(response) {
                    swal(response.data);
                });
        }


    };

}]);
