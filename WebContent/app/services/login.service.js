/**
 * 
 */

(function() {
	"use strict";

	angular.module('flavoursRestaurant').service('LoginService', LoginService);

	LoginService.$inject = [ '$q', "$http" ];

	function LoginService($q, $http) {
		var self = this;
		var user_id = undefined;
		var owner_id = undefined;
		var obj;

		self.validateLogin = function(id, password) {

			var defer = $q.defer();

			$http.get('http://localhost:8080/RRS-Rest/api/customers/' + id)
					.then(function(response) {
						obj = response.data;
						obj.type = "customer";
						console.dir(obj);
						defer.resolve(obj);
					});

			$http.get('http://localhost:8080/RRS-Rest/api/staff/' + id).then(
					function(response) {
						obj = response.data;
						obj.type = "staff";
						console.dir(obj);
						defer.resolve(obj);
					}, function(error) {
					});

			return defer.promise;
		}

	}

})();