/**
 * 
 */
(function() {
	'user strict';
	angular.module('myApp', ['ngRoute', 'ngSanitize', 'ngMaterial', 'ui.bootstrap', 'angularUtils.directives.dirPagination', 'ckeditor']).config(function($routeProvider) {
		$routeProvider.otherwise({
			redirecTo: "/",
			controller: "employeeCtrl"
		});
	}).controller('appCtrl', appCtrl);
	function appCtrl($scope, $q, $http, $mdDialog) {
		$scope.username = "Administrator";
		$scope.hideShow = false;

		// Event open and close modal responsive
		$scope.openModal = openModalEvent;
		$scope.closeModal = closeModalEvent;
		// Check login
		if (!localStorage.user) {
			location.href = "http://localhost:8080/EmployeeManager/login";
		} else {
			$scope.dataUser = JSON.parse(localStorage.user);
		}
		// Event logout
		$scope.showConfirm = function(ev) {
			var confirm = $mdDialog.confirm()
				.title('Would you like logout ?')
				.targetEvent(ev)
				.ok('Yes')
				.cancel('No');

			$mdDialog.show(confirm).then(function() {
				logoutEvent();
			});
		};

		/*$scope.checkRole = function() {
			console.log(checkRoleEvent());
		};

		function checkRoleEvent() {
			let roleName = ["ADMIN", "CEO", "BOD", "MANAGER", "ENGINEER", "EMPLOYEE"];
			let getName;
			for (let i = 0; i < roleName.length; i++) {
				if (roleName[i] == $scope.getRoleName) {
					getName = roleName[i];
				}
			}
			return getName;
		}*/
	}
})();