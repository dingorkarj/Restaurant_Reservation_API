(function() {

	angular.module('flavoursRestaurant').controller(
			'StaffCustomerListController', StaffCustomerListController);

	StaffCustomerListController.$inject = [ 'DataService', '$route', '$scope' ];

	function StaffCustomerListController(DataService, $route, $scope) {

		$scope.$route = $route;
		
		var staffCL_Vm = this;

		DataService.getAllCustomers().then(function(data) {
			staffCL_Vm.allCustomers = data;
			console.log(data);
		}, function(error) {
			console.log(error);
		});
		
		// LOGOUT FUNCTION
		$scope.logout = function() {
			localStorage.removeItem('RRS_Object');
			var location = $window.location.origin + '/RRS-Rest/index.html';
			$window.location.href = location;
		};
		// END OF LOGOUT FUNCTION

	}
})();