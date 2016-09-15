/**
 * Created by Yaacov on 29/12/2015.
 */
app = angular.module("root", ["ui.router", "ngMaterial"]);
app.config(function ($stateProvider, $urlRouterProvider,$mdIconProvider) {
    $mdIconProvider.defaultIconSet('./svg/avatars.svg',128);
    $mdIconProvider.icon('share','./svg/share.svg',24);
    $mdIconProvider.icon('menu','./svg/menu.svg',24);
    $mdIconProvider.icon('group','./svg/group.svg',24);
    $mdIconProvider.icon('account_circle','./svg/account_circle.svg');
    $mdIconProvider.icon('attach_money','./svg/attach_money.svg');
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
        .state('Groups', {
            url: "/groups",
            templateUrl: "template/Groups.html",
            controller: 'groups'
        })
        .state('Main', {
            url: "/main",
            templateUrl: "template/Main.html",
            controller: 'mainControl'
        });
});