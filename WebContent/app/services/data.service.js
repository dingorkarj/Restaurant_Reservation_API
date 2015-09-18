(function() {
	"use strict";

	angular.module('flavoursRestaurant').service('DataService', DataServiceFn);

	DataServiceFn.$inject = [ '$http', '$q', '$window' ];

	function DataServiceFn($http, $q, $window) {
		var self = this;

		self.reservation = {};

		self.setReservation = function(reservation) {
			self.reservation = reservation;
		};

		self.getReservation = function() {
			return self.reservation;
		};

		self.getAllCustomers = function() {

			var defer = $q.defer();

			var url = $window.location.origin + '/RRS-Rest/api/customers';
			
			$http.get(url).then(function(response) {
				defer.resolve(response.data);
			}, function(error) {
				defer.reject(error);
			});

			return defer.promise;
		}

	}

})();