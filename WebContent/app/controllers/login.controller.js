(function() {
	"use strict";

	angular.module('flavoursRestaurant',[]);

	angular.module('flavoursRestaurant').controller('LoginController',
			LoginController);

	LoginController.$inject = [ 'LoginService', '$window' ];
	
	function LoginController(LoginService, $window) {
		var loginVm = this;
		localStorage.removeItem('RRS_Object');
		loginVm.id = null;
		loginVm.password = null;

		loginVm.tryLogin = function() {

			LoginService.validateLogin(loginVm.id, loginVm.password).then(
					function(result) {
						console.log(result.type);
						console.log(result.fname);
						if (result.type == "customer") {
							localStorage.setItem('RRS_Object', JSON.stringify(result));
							var location = $window.location.origin
									+ '/RRS-Rest/welcome.html';
							
							$window.location.href = location;
						} else {
							if (result.type == "staff") {
								localStorage.setItem('RRS_Object', JSON.stringify(result));
								var location = $window.location.origin
										+ '/RRS-Rest/staff.html';
								$window.location.href = location;
							}
						}
					});

			loginVm.id = null;
			loginVm.passwrod = null;
		};

	}

})();