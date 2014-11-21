var tournamentControllers = angular.module('tournamentControllers');

tournamentControllers.controller('PlayerListCtrl', function ($scope, $http, PlayerFactory, players) {
    $scope.players = players.data;

    $scope.deletePlayer = function (player) {
        PlayerFactory.deletePlayer(player.id)
            .success(function (data) {
                PlayerFactory.getPlayers()
                    .success(function (data) {
                        $scope.players = data;
                    })
            })
            .error(function (error) {

            })
    }
});


tournamentControllers.controller('PlayerAddCtrl', function ($scope, $location, $http, PlayerFactory, RaceFactory) {

    $scope.races = RaceFactory.getRace();

    $scope.submitPlayer = function (player) {
        PlayerFactory.postPlayer(player)
            .success(function () {
                $location.path('/players')
            })
    }
});

tournamentControllers.controller('PlayerEditCtrl', function ($scope, $location, $http, PlayerFactory, RaceFactory, player) {

    $scope.player = player.data;

    $scope.races = RaceFactory.getRace();

    $scope.editPlayer = function (player) {
        PlayerFactory.editPlayer(player.id, player)
            .success(function () {
                $location.path('/players')
            })
    }
});


