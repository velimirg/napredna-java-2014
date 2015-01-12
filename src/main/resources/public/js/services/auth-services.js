var tournamentServices = angular.module('tournamentServices');

tournamentServices.factory('AuthService', function ($rootScope) {

    var AuthService = {};

    AuthService.hasAuthorization = function(requiredRole) {
        var hasAuthorization = false;
        if (!$rootScope.userData) {
            return hasAuthorization;
        }
        $rootScope.userData.roles.forEach(function(role) {

            if (role === requiredRole) {
                hasAuthorization = true;
            }
        })

        return hasAuthorization;
    }

    return AuthService;

});