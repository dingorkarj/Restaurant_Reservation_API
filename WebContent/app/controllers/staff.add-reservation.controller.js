(function() {

	angular.module('flavoursRestaurant').controller(
			'StaffAddReservationController', StaffAddReservationController);

	StaffAddReservationController.$inject = ['$scope', '$route', 'TableService'];

	function StaffAddReservationController($scope, $route, TableService) {

		$scope.$route = $route;
		var staffAD_Vm = this;
		staffAD_Vm.reservation = {};
		staffAD_Vm.message = '';
		
		staffAD_Vm.addReservation = function() {

			TableService.staffAddReservation(staffAD_Vm.reservation).then(function(data) {
				console.dir(data);
				staffAD_Vm.message = "Reservation done successfully!"
		 	}, function(error) {
		 		console.dir(error);
		 		staffAD_Vm.message = 'Internal Error!';
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