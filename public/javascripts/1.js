app.controller('wellcom', ['$scope', '$http', '$filter', '$state', function ($scope, $http, $filter, $state) {
    $scope.userName = 'RobertDupont';
    $scope.telephone;
    $scope.email = "Robert@gmail.com";
    $scope.password = "a";

    // For login scope
    $scope.Username = 'Y.Nathan';
    $scope.Password = 'a';

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
                                setUserNameCookie("username", userName);
                                // Register new user
                                $http({
                                    method: 'POST',
                                    url: '/REGISTER/' + userName + '/' + $scope.telephone + '/' + $scope.email + '/' + $scope.password})
                                    .then(
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
                alert("wellcom");
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
    }).then(function successCallback(response) {
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
                            swal(response.data);
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

    // Just check if there is a user name
    if (getCookie("username") == null) {
        // Go to the main application
        $state.go('Login');
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
                        swal("The Gelt was deleted successfully");
                    }
                },
                function error(response) {
                    swal(response.data);
                });
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