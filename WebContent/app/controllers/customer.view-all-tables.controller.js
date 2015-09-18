(function() {
	"use strict";

	angular.module('flavoursRestaurant').controller(
			'CustViewAllTablesController', CustViewAllTablesController);

	CustViewAllTablesController.$inject = [ 'TableService', '$location',
			'$scope', '$modal', '$route', '$window' ];

	function CustViewAllTablesController(TableService, $location, $scope,
			$modal, $route, $window) {

		$scope.$route = $route; //To highlight current view on the side menu.
		var custVAT_Vm = this;
		custVAT_Vm.customer = JSON.parse(localStorage.getItem('RRS_Object'));
		console.dir(custVAT_Vm.customer);
		$scope.fname = custVAT_Vm.customer.fname;

		TableService.getAllTables().then(function(result) {
			custVAT_Vm.allTables = result;
		}, function(error) {
			console.log(error);
		});

		custVAT_Vm.reservation = {
			cid : custVAT_Vm.customer.id
		};

		// RESERVATION MODAL 1
		$scope.openModal1 = function(tableid) {
			custVAT_Vm.reservation.tid = tableid;

			var modalInstance = $modal.open({
				animation : true,
				templateUrl : 'reservationModal1',
				size : 'md',
				scope : $scope

			})
		}

		// END OF RESERVATION MODAL 1

		// CALENDAR UI BOOTSTRAP
		$scope.today = function() {
			$scope.dt = new Date();
			$scope.minDate = new Date();
			$scope.format = 'dd-MMMM-yyyy';
		};
		$scope.today();

		$scope.status = {
			opened : false

		};

		$scope.openDate = function($event) {
			console.log("inn")
			$scope.status.opened = true;
		};

		// END OF CALENDER UI BOOTSTRAP

		// RESERVATION MODAL 2
		$scope.openModal2 = function(dt) {

			custVAT_Vm.reservation.date = dt;
			$scope.date = dt;

			TableService.custReserveTable(custVAT_Vm.reservation).then(
					function(result) {
						var modalInstance2 = $modal.open({
							animation : true,
							templateUrl : 'reservationModal2',
							size : 'md',
							scope : $scope
						});
						console.dir(result);
						$scope.confirmationCode = result.confirmationCode;
					}, function(error) {
						console.dir(error);
						console.log(error);
					});

		};
		// END OF RESERVATION MODAL 2

		// LOGOUT FUNCTION
		$scope.logout = function() {
			localStorage.removeItem('RRS_Object');
			var location = $window.location.origin + '/RRS-Rest/index.html';
			$window.location.href = location;
		};
		// END OF LOGOUT FUNCTION

	}

})();
