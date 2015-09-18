(function() {
	"use strict";

	angular.module('flavoursRestaurant').service('TableService', TableService);

	TableService.$inject = [ '$q', '$http', '$window' ];

	function TableService($q, $http, $window) {

		var self = this;

		self.getAllTables = function() {
			var defer = $q.defer();

			var url = $window.location.origin + '/RRS-Rest/api/tables/';
			$http.get(url).then(
					function(response) {
						defer.resolve(response.data);
					}, function(error) {
						defer.reject(error);
					});

			return defer.promise;
		};

		self.custGetReservations = function(cust_id) {

			var defer = $q.defer();

			var url = $window.location.origin + '/RRS-Rest/api/customers/' + cust_id
					+ '/reservations';

			$http.get(url).then(function(response) {
				defer.resolve(response.data);
			}, function(error) {
				defer.reject(error);
			});

			return defer.promise;
		};

		self.custReserveTable = function(reservation) {

			var defer = $q.defer();

			var url = $window.location.origin + '/RRS-Rest/api/customers/'
					+ reservation.cid + '/reservations';

			$http.post(url, reservation).then(function(response) {
				defer.resolve(response.data);
			}, function(error) {
				defer.reject(error);
			});

			return defer.promise;

		};

		self.custEditReservation = function(reservation) {

			var defer = $q.defer();

			var url = $window.location.origin +'/RRS-Rest/api/customers/'
					+ reservation.cid + '/reservations/' + reservation.rid;

			$http.put(url, reservation).then(function(response) {
				defer.resolve(response.data);
			}, function(error) {
				defer.reject(error);
			});

			return defer.promise;
		};
		
		self.custDeleteReservation = function(cid,rid,confirmationCode){
	
			var defer = $q.defer();
			
			var url = $window.location.origin + '/RRS-Rest/api/customers/'
				+ cid + '/reservations/' + rid +'/confirmation-code/' + confirmationCode;
			
			$http.delete(url)
			.then(function(response){
				defer.resolve(response.data);
			},function(error){
				 defer.reject(error);
			});
			
			return defer.promise;
		};
		
		self.staffGetAllReservations = function(){
			var defer = $q.defer();
			
			var url= $window.location.origin + '/RRS-Rest/api/staff/reservations';
			
			$http.get(url)
			.then(function(response){
				defer.resolve(response.data);
			},function(error){
				 defer.reject(error);
			});
			
			return defer.promise;
		};
		
		self.staffGetAllCustomers = function(){
			var defer = $q.defer();
			
			var url= $window.location.origin + '/RRS-Rest/api/customers';
			
			$http.get(url)
			.then(function(response){
				defer.resolve(response.data);
			},function(error){
				 defer.reject(error);
			});
			
			return defer.promise;
		};
		
		self.staffDeleteReservation = function(rid){
			
			var defer = $q.defer();
			var url = $window.location.origin + '/RRS-Rest/api/staff/reservations/' + rid;
			
			$http.delete(url).then(function(response){
				defer.resolve(response.data);
			},function(error){
				 defer.reject(error);
			});
			
			return defer.promise;
		};
		
		self.staffUpdateReservation = function(reservation){
			
			var defer = $q.defer();
			
			var url = $window.location.origin +'/RRS-Rest/api/staff/reservations/' + reservation.rid;
			$http.put(url,reservation).then(
			function(response){
				defer.resolve(response.data);
			},function(error){
				defer.reject(error);
			}		
			);
			
			return defer.promise;
		};
		
		
		self.staffAddReservation = function(reservation){
			
			var defer = $q.defer();
			
			var url = $window.location.origin +'/RRS-Rest/api/staff/reservations';
			
			$http.post(url,reservation).then(function(response){
				defer.resolve(response.data);
				},function(error){defer.reject(error);});
			
			
			return defer.promise;
		};
		
		
	}// END OF SERVICE FUNCTION

})();
