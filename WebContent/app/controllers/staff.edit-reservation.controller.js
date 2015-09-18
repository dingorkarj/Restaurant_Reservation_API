(function() {

	angular.module('flavoursRestaurant').controller(
			'StaffEditReservationController', StaffEditReservationController);

	StaffEditReservationController.$inject = [ 'DataService', 'TableService', '$scope' ];

	function StaffEditReservationController(DataService, TableService, $scope) {
		var staffEDR_Vm = this;

		staffEDR_Vm.reservation = DataService.getReservation();
		staffEDR_Vm.message = '';

		console.dir(staffEDR_Vm.reservation);

		staffEDR_Vm.updateReservation = function() {

			var reservation = {};
			reservation.rid = staffEDR_Vm.reservation.rid;
			reservation.cid = staffEDR_Vm.reservation.cid;
			reservation.tid = staffEDR_Vm.reservation.tid;
			reservation.date = staffEDR_Vm.reservation.date;
			reservation.confirmationCode = staffEDR_Vm.reservation.confirmationCode;
			
			console.dir(reservation);
			TableService.staffUpdateReservation(reservation).then(
					function(response) {
						staffEDR_Vm.message = "Update Successful";
					}, function(error) {
						staffEDR_Vm.message = "Internal Error";
					});

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