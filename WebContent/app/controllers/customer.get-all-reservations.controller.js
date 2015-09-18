(function() {
	"use strict";

	angular.module('flavoursRestaurant').controller(
			'CustGetAllReservationsController',
			CustGetAllReservationsController);

	CustGetAllReservationsController.$inject = [ 'TableService', '$location',
			'$scope', '$modal', '$route' ];

	function CustGetAllReservationsController(TableService, $location, $scope,
			$modal, $route) {

		$scope.$route = $route;
		var custGAR_Vm = this;
		custGAR_Vm.customer = JSON.parse(localStorage.getItem('RRS_Object'));
		console.dir(custGAR_Vm.customer);
		$scope.fname = custGAR_Vm.customer.fname;

		custGAR_Vm.initialLoad = function() {
			TableService.custGetReservations(custGAR_Vm.customer.id).then(
					function(result) {
						custGAR_Vm.reservations = result;

					}, function(error) {
						console.log(error);
					});
		}

		custGAR_Vm.initialLoad();

		custGAR_Vm.reservation = {
			cid : custGAR_Vm.customer.id
		};

		$scope.confirmationCode;

		// EDIT RESERVATION MODAL 1
		$scope.openEditModal1 = function(rid, tid) {
			custGAR_Vm.reservation.rid = rid;
			custGAR_Vm.reservation.tid = tid;
			var modalInstance = $modal.open({
				animation : true,
				templateUrl : 'editReservationModal1',
				size : 'md',
				scope : $scope

			})
		}

		// END OF EDIT RESERVATION MODAL 1

		// EDIT RESERVATION MODAL 2
		$scope.openEditModal2 = function(confirmationCode) {
			$scope.confirmationCode = confirmationCode;
			custGAR_Vm.reservation.confirmationCode = confirmationCode;

			var modalInstance = $modal.open({
				animation : true,
				templateUrl : 'editReservationModal2',
				size : 'md',
				scope : $scope

			})
		};
		// END OF RESERVATION MODAL 2

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

		// EDIT RESERVATION MODAL 3
		$scope.openEditModal3 = function(dt) {

			custGAR_Vm.reservation.date = dt;

			TableService.custEditReservation(custGAR_Vm.reservation).then(
					function(result) {
						var modalInstance2 = $modal.open({
							animation : true,
							templateUrl : 'editReservationModal3',
							size : 'md',
							scope : $scope
						});

						custGAR_Vm.initialLoad();
					}, function(error) {
						console.dir(error);

					});

		};
		// END OF EDIT RESERVATION MODAL 3

		// EDIT DELETE RESERVATION MODAL 1
		$scope.openDeleteModal1 = function(rid, tid) {
			custGAR_Vm.reservation.rid = rid;
			custGAR_Vm.reservation.tid = tid;
			$scope.rid = rid;
			$scope.tid = tid;

			var modalInstance = $modal.open({
				animation : true,
				templateUrl : 'deleteReservationModal1',
				size : 'md',
				scope : $scope

			})
		}

		// END OF DELETE RESERVATION MODAL 1

		$scope.deleteReservation = function(rid, confirmationCode) {
			custGAR_Vm.reservation.confirmationCode = confirmationCode;

			TableService.custDeleteReservation(custGAR_Vm.customer.id, rid,
					confirmationCode).then(function() {
			}, function() {
			});

			$scope.openDeleteModal2();

		};

		// EDIT DELETE RESERVATION MODAL 2
		$scope.openDeleteModal2 = function() {

			var modalInstance = $modal.open({
				animation : true,
				templateUrl : 'deleteReservationModal2',
				size : 'md',
				scope : $scope

			});
			custGAR_Vm.initialLoad();
		}

		// END OF DELETE RESERVATION MODAL 2

	}
})();