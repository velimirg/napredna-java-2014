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

tournamentControllers.controller('TournamentEditCtrl', function ($scope, $routeParams, $http, $location, tournament) {

    $scope.tournament = tournament.data;
    console.log(tournament);

    $scope.editTournament = function(tournament) {
        $http.put('tournaments/' + $routeParams.id, tournament)
            .success(function(data) {
                $location.path('/tournaments');
            })
    }

    $scope.postTournament = function(tournament) {

        tournament = angular.fromJson(tournament);
        delete tournament.id;
        tournament.template = false;

        tournament.rounds.forEach(function(round) {
            delete round.id;
            round.matches.forEach(function(match) {
                delete match.id;
                match.games.forEach(function(game) {
                    delete game.id;''
                    game.winner = angular.fromJson(game.winner);
                })
            })
        })

        console.log(tournament);
        $http.post('/tournaments-full', tournament)
            .success(function(data) {
                console.log(data);
                $location.path('/tournaments/' + data.id);
            })
            .error(function(data) {
                console.log(data);
            });
    }

    $scope.changedWinner = function(game, match, round, tournament) {
        var player1won = 0;
        var player2won = 0;
        match.games.forEach(function(game) {
            if (game.winner) {

                if (angular.fromJson(game.winner).id == match.player1.id) {
                    player1won += 1;
                } else {
                    player2won += 1;
                }
            }
        })
        var winner = false;
        if (player1won >= 3) {
            winner = true;
            match.winner = match.player1;
        }
        if (player2won >= 3) {
            winner = true;
            match.winner  = match.player2;
        }
        if (!winner) {
            match.winner = undefined;
        }
        if (match.winner) {
            var nextRound = tournament.rounds[tournament.rounds.indexOf(round) + 1]
            if (nextRound) {
                var matchIndex = round.matches.indexOf(match);
                if (matchIndex % 2 == 0) {
                    nextRound.matches[Math.floor(matchIndex / 2)].player1 = match.winner;
                } else {
                    nextRound.matches[Math.floor(matchIndex / 2)].player2 = match.winner;
                }
            }
        }



        console.log(round.matches.indexOf(match));

    }
})

tournamentControllers.controller('TournamentListCtrl', function ($scope, $rootScope, $http, TournamentFactory, tournaments) {

    $scope.tournaments = tournaments.data;

    $scope.generateDummyTournament = function() {
        TournamentFactory.generateDummyTournament()
            .success(function(data) {
                TournamentFactory.getTournamentTemplates()
                    .success(function(data) {
                        $scope.tournaments = data;
                    })
                    .error(function(error) {
                        console.log(error);
                    })
            })
            .error(function(error) {
                console.log(error);
            })
    }

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