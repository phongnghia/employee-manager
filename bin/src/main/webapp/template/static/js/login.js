/**
 * 
 */
angular.module('loginApp', ['ngSanitize', 'ngMaterial']).controller('loginCtrl', function($scope, $http) {
	$scope.inputType = "password";
	$scope.icon = '<i class="fas fa-eye-slash"></i>';
	$scope.hideShow = function() {
		if ($scope.inputType == "password") {
			$scope.inputType = "text";
			$scope.icon = '<i class="fas fa-eye"></i>';
		} else {
			$scope.inputType = "password";
			$scope.icon = '<i class="fas fa-eye-slash"></i>';
		}
	};
	$scope.viewIndex = "file/login/login.html";
	var arr = ["file/login/login.html", "file/login/forgotpassword.html", "file/login/createpassword.html"];
	$scope.loadIndex = function(event) {
		$scope.viewIndex = arr[event];
		$scope.error = true;
	};
	$scope.email;
	$scope.password;
	$scope.newpassword;
	$scope.repassword;
	$scope.error = true;

	$scope.onSubmit = function(email, password) {
		if (check(email) == true) {
			if (email != null && password != null) {
				let login = {};
				login.email = email;
				login.password = password;
				$http({
					method: "POST",
					url: "http://localhost:8080/EmployeeManager/api/login",
					data: JSON.stringify(login)
				}).then(function(response) {
					if (response.data.message == "true") {
						location.href = "http://localhost:8080/EmployeeManager/home";
						localStorage.setItem("user", JSON.stringify(response.data));
						$scope.error = true;
					} else {
						$scope.error = false;
						$scope.alert = "Invalid email or password";
					}
				}, function myError(response) {
					$scope.error = false;
					$scope.alert = "Not connect to server";
				});
			} else {
				$scope.error = false;
				$scope.alert = "Please, you entry field !";
			}
		} else {
			$scope.error = false;
			$scope.alert = "Email is not invalid";
		}
	}
	$scope.onSubmitForgot = function(email) {
		$scope.error = false;
		$scope.success = {
			'color': 'green'
		};
		$scope.alert = "Please, wait a few minutes !";
		if (check(email) == true) {
			$http({
				url: "http://localhost:8080/EmployeeManager/api/sendemail?email=" + email,
				method: "POST"
			}).then(function(response) {
				if (response.data.message == "true") {
					$scope.error = false;
					$scope.success = {
						'color': 'green'
					};
					$scope.alert = "Please, check your email !";
					let list = document.getElementsByTagName('input');
					for (let i = 0; i < list.length; i++) {
						if (list[i] != document.querySelector('input[type="button"]')) {
							list[i].value = "";
						}
					}
				} else {
					$scope.error = false;
					$scope.success = {};
					$scope.alert = "Invalid email !";
				}
			}, function(response) {
				$scope.error = false;
				$scope.alert = "Not connect to server";
			});
		} else {
			$scope.error = false;
			$scope.alert = "Email is not invalid";
		}
	};
	$scope.onSubmitCreate = function(email, password, newpassword, repassword) {
		if (check(email) == true) {
			if (newpassword == repassword && newpassword != null) {
				let create = {};
				create.email = email;
				create.password = password;
				create.newPassword = newpassword;
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
					$scope.error = false;
					$scope.alert = "Not connect to server";
				});
			} else {
				if (newpassword == null || repassword == null) {
					$scope.alert = "Please, you entry field !";
				} else {
					$scope.alert = "Re-enter incorrect password!";
				}
				$scope.error = false;
				$scope.success = {};
			}
		} else {
			$scope.error = false;
			$scope.alert = "Email is not invalid";
		}
	}
});