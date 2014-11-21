var tournamentControllers = angular.module('tournamentControllers');

tournamentControllers.controller('TournamentAddCtrl', function ($scope, $http, $location) {

    $scope.submitTournament = function (tournament) {
        $http.post('tournaments', tournament)
            .success(function (data) {
                $location.path('/tournaments');
            })
            .error(function (error) {
                console.log(error);
            })
    }
})

tournamentControllers.controller('TournamentEditCtrl', function ($scope, $routeParams, $http, $location, tournament, rounds) {

    $scope.tournament = tournament.data;
    $scope.rounds = rounds.data;

    $scope.editTournament = function(tournament) {
        $http.put('tournaments/' + $routeParams.id, tournament)
            .success(function(data) {
                $location.path('/tournaments');
            })
    }
})

tournamentControllers.controller('TournamentListCtrl', function ($scope, $rootScope, $http, TournamentFactory, tournaments) {

    $scope.tournaments = tournaments.data;

    $scope.deleteTournament = function(tournament) {
        $http.delete('tournaments/' + tournament.id)
            .success(function(data) {
                $scope.getTournaments();
            })
            .error(function(error) {
                console.log(error);
            })
    }

    $scope.getTournaments = function() {
        TournamentFactory.getTournaments()
            .success(function(data) {
                $scope.tournaments = data;
            })
            .error(function(errorData) {
                console.log(errorData);
            })
    }


})