(function() {
	angular.module('flavoursRestaurant').controller(
			'StaffGetAllReservationController', StaffGAR_Fn);

	StaffGAR_Fn.$inject = [ '$scope', '$route', '$window', 'TableService',
			'DataService' ];

	function StaffGAR_Fn($scope, $route, $window, TableService, DataService) {
		$scope.$route = $route;
		var staffGAR_Vm = this;
		staffGAR_Vm.staff = JSON.parse(localStorage.getItem('RRS_Object'));
		$scope.fname = staffGAR_Vm.staff.fname;

		staffGAR_Vm.initialLoad = function() {
			//
			// GET ALL RESERVATIONS
			TableService.staffGetAllReservations().then(function(data) {
				staffGAR_Vm.allReservations = data;
				{
					// GET ALL CUSTOMER
					TableService.staffGetAllCustomers().then(function(data) {
						staffGAR_Vm.allCustomers = data;
						{
							// PERFORM CUSTOMER ID AND NAME MAPPING
							// RESERVATION AND CUSTOMER OBJECTS ARE FINALLY
							// RECEIVED
							staffGAR_Vm.getCustomerName();
						}
					}, function(error) {
						console.dir(error);
					});
				}
			}, function(error) {
				console.dir(error);
			});
		}

		staffGAR_Vm.initialLoad();

		// METHOD TO MAP CUSTOMER NAME FROM CUSTOMER ARRAY WITH CUSTOMER ID
		// INSIDE RESERVATION ARRAY
		staffGAR_Vm.getCustomerName = function() {

			for (var i = 0; i < staffGAR_Vm.allReservations.length; i++) {
				for (var j = 0; j < staffGAR_Vm.allCustomers.length; j++) {
					if (staffGAR_Vm.allReservations[i].cid == staffGAR_Vm.allCustomers[j].id) {
						staffGAR_Vm.allReservations[i].fname = staffGAR_Vm.allCustomers[j].fname;
						staffGAR_Vm.allReservations[i].lname = staffGAR_Vm.allCustomers[j].lname;
						break;
					}
				}
			}
		};

		// DELETE RESERVATION
		staffGAR_Vm.deleteReservation = function(rid) {
			TableService.staffDeleteReservation(rid).then(function(data) {
				staffGAR_Vm.initialLoad();
			}, function(error) {
				console.dir(error);
			});
		};
		// END OF DELETE RESERVATION

		// EDIT RESERVATION
		staffGAR_Vm.editReservation = function(reservation) {
			DataService.setReservation(reservation);
			var url = $window.location.origin + '/RRS-Rest/staff.html'
					+ "#/Edit-Reservation"
			$window.location.href = url;
		};
		
		// LOGOUT FUNCTION
		$scope.logout = function() {
			localStorage.removeItem('RRS_Object');
			var location = $window.location.origin + '/RRS-Rest/index.html';
			$window.location.href = location;
		};
		// END OF LOGOUT FUNCTION
	}

})();