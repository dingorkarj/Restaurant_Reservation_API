(function() {

	"use strict";
	angular.module('flavoursRestaurant').controller(
			'CustReserveTableController', CustReserveTableController);

	CustReserveTableController.$inject = [ '$modalInstance', 'reservation',
			'$scope' ];

	function CustReserveTableController($modalInstance, reservation, $scope) {

		var custRTC_Vm = this;
		custRTC_Vm.customer = JSON.parse(localStorage.getItem('RRS_Object'));
		console.dir(custRTC_Vm.customer);

		custRTC_Vm.reservation = reservation;
	}

})();
