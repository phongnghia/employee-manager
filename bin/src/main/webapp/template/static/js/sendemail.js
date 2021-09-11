/**
 * 
 */
angular.module('changeApp', []).controller('changeCtrl', function($scope, $http) {
	$scope.password;
	$scope.repassword;
	$scope.error = true;
	$scope.alert;
	$scope.loadIndex = function(){
		location.href = "http://localhost:8080/EmployeeManager/login";
	}
	$scope.changePass = function(email) {
		let oldPassword = document.querySelector('input[type="email"]').getAttribute("data-value");
		console.log(email + " " + oldPassword + " " + $scope.password + " " + $scope.repassword);
		if ($scope.password != null && $scope.repassword != null) {
			if ($scope.password == $scope.repassword) {
				let create = {};
				create.email = email;
				create.password = oldPassword;
				create.newPassword = $scope.password;
				$http({
					url: "http://localhost:8080/EmployeeManager/api/password",
					method: "PUT",
					data: JSON.stringify(create)
				}).then(function(response) {
					if (response.data.message == "false") {
						$scope.success = {};
						$scope.error = false;
						$scope.alert = "Invalid email or password";
					} else {
						$scope.error = false;
						$scope.success = {
							'color': 'green'
						};
						$scope.alert = "Change password success !";
						let list = document.getElementsByTagName('input');

						for (let i = 0; i < list.length; i++) {
							if (list[i] != document.querySelector('input[type="button"]')) {
								list[i].value = "";
							}
						}
					}
				}, function(response) {
					console.log(response);
				});
			} else {
				$scope.error = false;
				$scope.alert = "Re-enter incorrect password!"
			}
		} else {
			$scope.error = false;
			$scope.alert = "Please, you entry field !"
		}
	};
});