var tournamentServices = angular.module('tournamentServices');

tournamentServices.factory('TournamentFactory', function ($http, $route) {
    var factory = {};

    factory.getTournaments = function () {
        return $http.get('tournaments');
    }
    return factory;
})

tournamentServices.factory('PlayerFactory', function ($http, $route) {
    var factory = {};

    factory.getPlayer = function (id) {
        return $http.get('players/' + id)
    }
    factory.getPlayers = function () {
        return $http.get('players');
    };
    factory.deletePlayer = function (id) {
        return $http.delete('players/' + id);
    }
    factory.postPlayer = function (player) {
        return $http.post('players', player);
    }
    factory.editPlayer = function (id, player) {
        return $http.put('players/' + id, player);
    }


    return factory;
})

tournamentServices.factory('RaceFactory', function ($http, $route) {
    var factory = {};

    factory.getRace = function () {
        return ['Zerg', 'Terran', 'Protoss', 'Random'];
    };


    return factory;
})

tournamentServices.factory('RoundFactory', function ($http, $route) {
    var factory = {};

    factory.getRounds = function (tournamentId) {
        return $http.get('tournaments/' + tournamentId + '/rounds')
    }
    return factory;
})