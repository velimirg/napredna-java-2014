angular.module('tournamentControllers', []);

var tournamentApp = angular.module('tournamentApp', ['ngRoute', 'tournamentControllers']);

tournamentApp.config(function($routeProvider) {

    $routeProvider
        .when('/tournaments', {
            templateUrl: 'partials/tournaments.html',
            controller: 'TournamentCtrl'
        })
        .when('/tournaments/:id', {
            templateUrl: 'partials/tournaments-details.html',
            controller: 'TournamentDetailCtrl'
        })

})

tournamentApp.factory('TestFactory', function($rootScope) {

    var service = {};

    service.square = function(a) {
        $rootScope.num = a * a;
        return a * a;
    }

    return service;
})
