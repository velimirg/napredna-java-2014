angular.module('tournamentControllers', []);
angular.module('tournamentServices', []);


var tournamentApp = angular.module('tournamentApp', ['ngRoute',
    'tournamentControllers',
    'tournamentServices',
]);

tournamentApp.run(function ($window, $http, $rootScope) {

    $window.googleSignInCallback = function (authResult) {

        $http.post('oauth2/google', null, {
            headers: {
                id_token: authResult.id_token,
                authorization_code: authResult.code
            }
        })
            .success(function (data) {
                $http.defaults.headers.common['token'] = data;
                $rootScope.loggedIn = data;
                console.log(data);
            })
        console.log(authResult);
    }
})
tournamentApp.config(function($routeProvider) {

    $routeProvider
        .when('/tournaments', {
            templateUrl: 'partials/tournament-list.html',
            controller: 'TournamentListCtrl',
            resolve: {
                tournaments: function (TournamentFactory) {
                    return TournamentFactory.getTournaments();
                }
            }
        })
        .when('/tournaments/:id', {
            templateUrl: 'partials/tournament-edit-tournament.html',
            controller: 'TournamentEditCtrl',
            resolve: {
                tournament: function ($http, $route) {
                    return $http.get('tournaments/' + $route.current.params.id);
                },
                rounds: function ($route, RoundFactory) {
                    return RoundFactory.getRounds($route.current.params.id);
                }
            }
        })
        .when('/tournaments-add-tournament', {
            templateUrl: 'partials/tournament-add-tournament.html',
            controller: 'TournamentAddCtrl'
        })
        .when('/players', {
            templateUrl: 'partials/player-list.html',
            controller: 'PlayerListCtrl',
            resolve: {
                players: function (PlayerFactory) {
                    return PlayerFactory.getPlayers();
                }
            }
        })
        .when('/players/:id', {
            templateUrl: 'partials/player-edit-player.html',
            controller: 'PlayerEditCtrl',
            resolve: {
                player: function (PlayerFactory, $route) {
                    return PlayerFactory.getPlayer($route.current.params.id);
                }
            }
        })
        .when('/players-add-player', {
            templateUrl: 'partials/player-add-player.html',
            controller: 'PlayerAddCtrl'
        })

        .otherwise({
            redirectTo: '/'
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
