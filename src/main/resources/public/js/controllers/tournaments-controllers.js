var tournamentControllers = angular.module('tournamentControllers');

tournamentControllers.controller('TournamentDetailCtrl', function($scope, $http, $routeParams) {

    $http.get('tournaments/' + $routeParams.id)
        .success(function(data) {
            $scope.tournament = data;
        })

    $scope.editTournament = function(tournament) {
        $http.put('tournaments/' + $routeParams.id, tournament)
            .success(function(data) {
                $scope.tournament = data;
            })
    }
})

tournamentControllers.controller('TournamentCtrl', function($scope, $rootScope, $http, TestFactory) {


    $scope.deleteTournament = function(tournament) {
        $http.delete('tournaments/' + tournament.id)
            .success(function(data) {
                console.log(data);
                $scope.getTournaments();
            })
            .error(function(error) {
                console.log(error);
            })
    }

    $scope.submitTournament = function(tournament) {
        $http.post('tournaments', tournament)
            .success(function(data) {
                $scope.getTournaments();
            })
            .error(function(error) {
                console.log(error);
            })
    }

    $scope.getTournaments = function() {
        $http.get('tournaments')
            .success(function(data) {
                $scope.tournaments = data;
            })
            .error(function(errorData) {
                console.log(errorData);
            })
    }

})