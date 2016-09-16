app.controller('mainControl', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav) {
    //$interval.clear(interval)
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + getCookie("username");
    $scope.DebterName = "No user detected";
    $scope.usersName = [];
    $scope.user = null;
    $scope.userName = getCookie("username");
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

    $scope.toggleLeft = function () {
        $mdSidenav('left').toggle();
    }
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

    $scope.goToCopyright = function () {
    $state.go('Copyright');
    }
    $scope.goToUserInformation = function () {
        $state.go('userInformation');
    }
    $scope.goToGroups = function () {
        $state.go('Groups');
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