/**
 * 
 */
(function() {
	"use strict";
	
	angular.module('flavoursRestaurant').config(moduleConfig);

	moduleConfig.$inject = [ '$routeProvider' ];

	function moduleConfig($routeProvider) {
		$routeProvider.when('/view', {
			templateUrl : 'app/templates/viewalltables.cust.template.html',
			controller : 'CustViewAllTablesController',
			controllerAs : 'custVAT_Vm',
			activetab : 'view'
		}).when('/reservations', {
			templateUrl : 'app/templates/reservedtables.cust.template.html',
			controller : 'CustGetAllReservationsController',
			controllerAs : 'custGAR_Vm',
			activetab : 'reservations'
		}).otherwise({
			redirectTo : '/view'
		});
	}

})();