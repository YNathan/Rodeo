app.controller('wellcom', ['$scope', '$http', '$filter', '$state', '$mdDialog', function ($scope, $http, $filter, $state, $mdDialog) {
    $scope.userName = 'RobertDupont';
    $scope.firstName = "Robert";
    $scope.lastName = "Dupont";
    $scope.telephone;
    $scope.email = "Robert@gmail.com";
    $scope.password = "a";
    $scope.birthdate = 01 / 01 / 1990;

    // For login scope
    $scope.Username = 'Y.Nathan';
    $scope.Password = 'a';


    function showAlert(title, textContent, ariaLabel) {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title(title)
                .textContent(textContent)
                .ariaLabel(ariaLabel)
        );
    }


    // Will input the user name into the cookie
    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
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

    $scope.uploadFile = function (files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + getCookie("username"), fd, {
            withCredentials: true,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(
            swal("Yeah!!!")
        ).error(
            swal("Oups! something wrong was hapening")
        );

    };


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
                                        alert("Register successful!");
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


    $scope.login = function () {
        var userName = $scope.Username;
        var password = $scope.Password;

        // Check if the name exist
        $http({
            method: 'POST',
            url: '/LOGIN/' + userName + '/' + password
        }).then(function successCallback(response) {
            if (response.data == "true") {


                $mdDialog.show(
                    $mdDialog.alert()
                        .parent(angular.element(document.querySelector('#popupContainer')))
                        .clickOutsideToClose(true)
                        .title('Wellcom')
                        .textContent('Enjoy our services.')
                        .ariaLabel('Alert Dialog Demo')
                        .ok('Lets start!')
                );
                setUserNameCookie("username", userName);
                // Go to the main application
                $state.go('Main');
            }
        }, function error(response) {
            alert(response.data);
        });
    };

    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }

}]);
app.controller('mainControl', ['$scope', '$http', '$state', '$interval', '$mdDialog', function ($scope, $http, $state, $interval, $mdDialog) {
    //$interval.clear(interval)
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + getCookie("username");
    $scope.DebterName = "No user detected";
    $scope.usersName = [];
    $scope.user = null;
    $scope.userName = "";
    $scope.firstName = "";
    $scope.lastName = "";
    $scope.telephone;
    $scope.email = "";
    $scope.password = "";
    $scope.birthdate;
    $scope.userId = "";
    $scope
    var bIsNumberChanged = false;
    var bIsNameChanged = false;

    function showAlert(title, textContent, ariaLabel) {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title(title)
                .textContent(textContent)
                .ariaLabel(ariaLabel)
        );
    }

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
    }).then(function successCallback(response) {
        var tempArr = [];
        angular.forEach(response.data, function (value, key) {
            itemName = {
                id: key,
                name: value
            }
            tempArr.push(itemName);
            $scope.data.availableOptions = tempArr;
        }, $scope.data);

        angular.forEach(tempArr, function (value, key) {
            $scope.usersName.push(value.name);
        }, $scope.data);


    });

    // Get debts conserning the user
    $http.get("/GET_GELT/" + getCookie("username"))
        .then(function (response) {
            $scope.debts = response.data.debts;
        });

    $interval(checkIfNeedConfirming, 20000);

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
                    $http.post("/CONFIRMATION/" + getCookie("username") + '/' + amount + '/' + entitled)
                        .then(function (response) {
                            $scope.debts = response.data.debts;
                        });
                } else {
                    // reject a debts
                    $http.post("/NOT_CONFIRMATION/" + getCookie("username") + '/' + amount + '/' + entitled)
                        .then(function (response) {
                            showAlert("responce from server", response.data, "great");
                        });
                }
            });
    }

    // Logic methods section

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

    $scope.$watch('data.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            $scope.DebterName = newVal.name;
            bIsNameChanged = true;
            if (bIsNumberChanged) {
                $scope.updateBeforeInput();
            } else {
                $scope.alertBeforeInput = "please select an amount";
            }
        }
    })
    // Just check if there is a user name
    if (getCookie("username") == null) {
        // Go to the main application
        $state.go('wellcom');
    }

    // When client select a debter will update the text area field of debter
    $scope.updateBeforeInput = function () {
        bIsNumberChanged = true;
        if (bIsNameChanged) {
            $scope.alertBeforeInput = "do you confirm that " + $scope.DebterName + " need to give you " + $scope.Amount + " dollars ?";
        } else {
            $scope.alertBeforeInput = "please select a debter";
        }
    }
    // When client select an amount will update the text area field of debter
    $scope.updateName = function () {
        var a = 7;
        console.log("hayyy")
    }

    // Pay
    $scope.pay = function (debter, entitled, amount) {
        if (getCookie("username") == debter) {
            alert("Only the entitled can confirm that you pay");
        }
        else {
            // send the request to the server for delete the gelt
            $http({
                method: 'POST',
                url: '/PAY/' + debter + '/' + amount + '/' + entitled
            }).then(
                function successCallback(response) {
                    if (response.data == "true") {
                        showAlert("responce from server", "The Gelt was deleted successfully", "great");
                    }
                },
                function error(response) {
                    showAlert("responce from server", response.data, "great");
                });
        }
    }

    // Send the new debt to the server
    $scope.submit = function () {
        if ((!bIsNameChanged)) {
            showAlert("Your attention please", "please select a debter", "great");
        } else if (!bIsNumberChanged) {
            showAlert("Your attention please", "please select an amount", "great");
        } else {
            // send the request to the server with the new debt
            $http({
                method: 'POST',
                url: '/INSERT_GELT/' + $scope.data.selectedOption.name + '/' + $scope.Amount + '/' + getCookie("username")
            }).then(
                function successCallback(response) {
                    if (response.data == "true") {
                        showAlert("Your attention please", "gelt waiting for confirming", "great");
                    }
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "great");
                });
        }
    };

    $scope.goToUserInformation = function () {
        $state.go('userInformation');
    }
    $scope.getUserInformation = function () {
        // Get information conserning the user
        $http.get("/GET_USER_INFORMATION/" + getCookie("username"))
            .then(function successCallback(response) {
                    $scope.userName = response.data.user[0].user_name;
                    $scope.firstName = response.data.user[0].first_name;
                    $scope.lastName = response.data.user[0].last_name;
                    $scope.email = response.data.user[0].email;
                    $scope.telephone = response.data.user[0].telephone;
                    $scope.password = response.data.user[0].password;
                    $scope.birthdate = response.data.user[0].birthdate;
                    $scope.userId = response.data.user[0].user_id;


                    var userInformationNiceDisplay = $scope.userName + " : " +
                        $scope.firstName + " : " +
                        $scope.lastName + " : " +
                        $scope.email + " : " +
                        $scope.telephone + " : " +
                        $scope.password + " : " +
                        $scope.birthdate + " : " +
                        $scope.userId;

                    showAlert("Information About User" + getCookie("username"), userInformationNiceDisplay, "Enjoy!")
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load user information");
                });
    }

    // Update profile picture
    $scope.uploadFile = function (files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + getCookie("username"), fd, {
            withCredentials: true,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(
            swal("Yeah!!!")
        ).error(
            swal("Oups! something wrong was hapening")
        );

    };
}]);
app.controller('userInformation', ['$scope', '$http', '$filter', '$state', '$mdDialog', function ($scope, $http, $filter, $state, $mdDialog) {

    $scope.username = 'Developer';
    $scope.firstName = '';
    $scope.lastName = '';
    $scope.telephone = '052787878';
    $scope.email = 'ipsum@lorem.com';
    $scope.password = '';
    $scope.birthdate = '';
    $scope.userId = '';


    // For login scope
    $scope.Username = 'Y.Nathan';
    $scope.Password = 'a';

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

    // Get information conserning the user
    $http.get("/GET_USER_INFORMATION/" + getCookie("username"))
        .then(function successCallback(response) {
                $scope.username = response.data.user[0].user_name;
                $scope.firstname = response.data.user[0].first_name;
                $scope.lastname = response.data.user[0].last_name;
                $scope.email = response.data.user[0].email;
                $scope.telephone = response.data.user[0].telephone;
                $scope.password = response.data.user[0].password;
                $scope.birthdate = response.data.user[0].birthdate;
                $scope.userId = response.data.user[0].user_id;


                var userInformationNiceDisplay = $scope.username + " : " +
                    $scope.firstname + " : " +
                    $scope.lastname + " : " +
                    $scope.email + " : " +
                    $scope.telephone + " : " +
                    $scope.password + " : " +
                    $scope.birthdate + " : " +
                    $scope.userId;

                showAlert("Information About User" + getCookie("username"), userInformationNiceDisplay, "Enjoy!")
            },
            function error(response) {
                showAlert("Your attention please", response.data, "cant load user information");
            });

    function showAlert(title, textContent, ariaLabel) {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title(title)
                .textContent(textContent)
                .ariaLabel(ariaLabel)
        );
    }


    // Will input the user name into the cookie
    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }

    $scope.uploadFile = function (files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + getCookie("username"), fd, {
            withCredentials: true,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(
            swal("Yeah!!!")
        ).error(
            swal("Oups! something wrong was hapening")
        );

    };

    $scope.reedit = function () {
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
                                        alert("Register successful!");
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