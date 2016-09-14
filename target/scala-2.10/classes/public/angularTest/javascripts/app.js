/**
 * Created by Yaacov on 29/12/2015.
 */
app = angular.module("root", ["ui.router", "ngMaterial"]);
app.config(function ($stateProvider, $urlRouterProvider) {
    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/wellcom");
    //
    // Now set up the states
    $stateProvider
        .state('wellcom', {
            url: "/wellcom",
            templateUrl: "template/wellcom.html",
            controller: 'wellcom'
        })
        .state('userInformation', {
            url: "/userInformation",
            templateUrl: "template/userInformation.html",
            controller: 'userInformation'
        })
        .state('Main', {
            url: "/main",
            templateUrl: "template/Main.html",
            controller: 'mainControl'
        });
});