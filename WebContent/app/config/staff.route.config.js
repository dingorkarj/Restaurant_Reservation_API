(function() {
	"use strict";
	angular.module('flavoursRestaurant').config(moduleConfig);

	moduleConfig.$inject = [ '$routeProvider' ];

	function moduleConfig($routeProvider) {
		$routeProvider
				.when(
						'/View-Reservations',
						{
							templateUrl : 'app/templates/viewallreservations.emp.template.html',
							controller : 'StaffGetAllReservationController',
							controllerAs : 'staffGAR_Vm',
							activetab : 'view'
						})
				.when(
						'/Add-Reservation',
						{
							templateUrl : 'app/templates/addreservation.emp.template.html',
							controller : 'StaffAddReservationController',
							controllerAs : 'staffAD_Vm',
							activetab : 'add'
						})

				.when(
						'/Edit-Reservation',
						{
							templateUrl : 'app/templates/editreservation.emp.template.html',
							controller : 'StaffEditReservationController',
							controllerAs : 'staffEDR_Vm',
							activetab : 'edit'
						})

				.when(
						'/Customer-List',
						{
							templateUrl : 'app/templates/customerlist.emp.template.html',
							controller : 'StaffCustomerListController',
							controllerAs : 'staffCL_Vm',
							activetab : 'customerlist'
						}).otherwise({
					redirectTo : '/View-Reservations'
				});
	}

})();