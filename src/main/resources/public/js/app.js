angular.module('tournamentControllers', []);
angular.module('tournamentServices', []);


var tournamentApp = angular.module('tournamentApp', ['ngRoute', 'angular-jwt',
    'tournamentControllers',
    'tournamentServices',
]);

tournamentApp.run(function ($window, $http, $rootScope, $location, jwtHelper, AuthService) {

    $window.googleSignInCallback = function (authResult) {
        $http.post('oauth2/google', null, {
            headers: { authorization_code: authResult.code }
        })
        .success(function (data) {
            console.log('Successful login');
            $http.defaults.headers.common['token'] = data.token;
            $rootScope.loggedIn = true;
            $rootScope.userData = jwtHelper.decodeToken(data.token);
            console.log($rootScope.userData);
        })
    }

    $rootScope.$on('$routeChangeStart', function(event, next, current) {

        if (next.authorization) {
            if (!AuthService.hasAuthorization(next.authorization.role)) {
                event.preventDefault();
                $rootScope.$evalAsync(function() {
                    $location.path('/auth-error');
                });

            }
        }
    })
})
tournamentApp.config(function($routeProvider) {

    $routeProvider
        .when('/auth-error', {
            templateUrl: 'partials/auth-error.html'
        })
        .when('/generate', {
            templateUrl: 'partials/generate-tournament.html',
            authorization: {
                role: 'ADMIN'
            }
        })
        .when('/templates', {
            templateUrl: 'partials/tournament-template-list.html',
            controller: 'TournamentTemplateListCtrl',
            resolve: {
                templates: function (TournamentFactory) {
                    return TournamentFactory.getTournamentTemplates();
                }
            },
            authorization: {
                role: 'USER'
            }
        })
        .when('/templates/:id', {
            templateUrl: 'partials/tournament-template-edit.html',
            controller: 'TournamentTemplateEditCtrl',
            resolve: {
                template: function ($http, $route) {
                    return $http.get('tournaments/' + $route.current.params.id);
                }
            }
        })
        .when('/templates/:id/tournaments', {
            templateUrl: 'partials/tournament-prediction-list.html',
            controller: 'TournamentPredictionListCtrl',
            resolve: {
                predictions: function ($http, $route) {
                    console.log('test');
                    return $http.get('tournament-templates/' + $route.current.params.id + '/tournaments');
                }
            }
        })
        .when('/templates/:templateId/tournaments/:id', {
            templateUrl: 'partials/tournament-prediction.html',
            controller: 'TournamentEditCtrl',
            resolve: {
                tournament: function ($http, $route) {
                    return $http.get('tournaments/' + $route.current.params.id);
                }
            }
        })

        .when('/tournaments', {
            templateUrl: 'partials/tournament-list.html',
            controller: 'TournamentListCtrl',
            resolve: {
                tournaments: function (TournamentFactory) {
                    return TournamentFactory.getTournamentTemplates();
                }
            }
        })
        .when('/tournaments/:id', {
            templateUrl: 'partials/tournament-edit-tournament.html',
            controller: 'TournamentEditCtrl',
            resolve: {
                tournament: function ($http, $route) {
                    return $http.get('tournaments/' + $route.current.params.id);
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
