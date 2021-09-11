/**
 * 
 */
angular.module('myApp').config(function($routeProvider) {
	$routeProvider.when('/position', {
		templateUrl: 'file/position/defaultposition.html',
		controller: 'positionCtrl'
	});
}).controller('positionCtrl', function($scope, $http, $mdDialog) {
	$scope.addRole = function() {
		$mdDialog.show({
			templateUrl: 'dialogAddRole.tmpl.html',
			parent: angular.element(document.body),
			clickOutsideToClose: true,
			fullscreen: $scope.customFullscreen,
			controller: function($scope, $mdDialog) {
				$scope.cancelRole = function() {
					$mdDialog.cancel();
				};

				$scope.answerRole = function(answer) {
					$mdDialog.hide(answer);
				};
				$scope.addRole = function() {
					$http({
						method: "POST",
						url: '/EmployeeManager/api/position',
						data: angular.toJson($scope.positionForm)
					}).then(function(res) {
						_refreshPositionData();
					}, function(res) { // error
						console.log("Error: " + res.status + " : " + res.data);
					});
				};
			}
		}).then(function(answer) {

		}, function() {
		});
	};
	$scope.sortType = 'name';
	$scope.nameDefault = "Position";
	$scope.viewPosition = "file/position/listposition.html";
	$scope.sortReverse = false;
	$scope.checkPosition = checkAllScope;
	$scope.changeViewPosition = changeViewEventPosition;
	$scope.choseEventPosition = choseTeamPosition;
	$scope.employees;
	$scope.positiones;
	$scope.listemployees;
	$scope.positionForm = {
		id: -1,
		name: ""
	};
	$scope.employeeForm = {
		id: "",
		name: "",
		nickname: "",
		email: "",
		status: "",
		sex: "",
		dayStart: "",
		idRole: "",
		nameRole: "",

	};
	//load data
	_refreshPositionData();
	_refreshEmployeeData(0);

	$scope.chose = function() {
		let obj = $scope.x;
		console.log(obj.id);
		_refreshEmployeeData(obj.id);
	}

	//Get Position
	function _refreshPositionData() {
		$http({
			method: 'GET',
			url: '/EmployeeManager/api/position'
		}).then(
			function(res) {
				$scope.positiones = res.data;
			},
			function(res) {
				console.log("Error: " + res.status + " : " + res.data);
			}
		);
	}
	//


	//Get employee

	function _refreshEmployeeData(id) {
		$http({
			method: 'GET',
			url: '/EmployeeManager/api/user/' + id,
		}).then(
			function(res) {
				$scope.employees = res.data;
			},
			function(res) {
				console.log("Error: " + res.status + " : " + res.data);
			}
		);
	}
	/*$scope.arrlist;
	$scope.arremployee = function() {
		if (positiones.id == employees.idRole) {
			arrlist = employees;
		}
	}*/

	//
	$scope.roleID;
	$scope.deletePosition = function(obj) {
		console.log(obj.id);
		$http({
			method: 'DELETE',
			url: '/EmployeeManager/api/position/delete?id=' + obj.id
		}).then(_success, _error);
	};
	//===================================




	/*$scope.submitPosition = function(positionForm) {
		console.log(positionForm);

		var method = "POST";
		var url = "/EmployeeManager/api/position";

		$http({
			method: method,
			url: url,
			data: JSON.stringify(positionForm)
		}).then(_success, _error);
	};

	$scope.createPosition = function() {
		_clearFormData();
	}*/




	//=============================================

	$scope.submitPosition = function() {
		console.log($scope.positionForm);
		var method = "";
		var url = "";

		if ($scope.positionForm.id == -1) {
			method = "POST";
			url = '/EmployeeManager/api/position';
		} else {
			method = "PUT";
			url = '/EmployeeManager/api/position/' + $scope.positionForm.id;
		}

		$http({
			method: method,
			url: url,
			data: angular.toJson($scope.positionForm),
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(_success, _error);
	};




	function _success(res) {
		_refreshPositionData();
		_clearFormData();
	}
	function _error(res) {
		var data = res.data;
		var status = res.status;
		var header = res.header;
		var config = res.config;
		alert("Error: " + status + ":" + data);
	}




	//clear form


	function _clearFormData() {
		$scope.positionForm.id = -1;
		$scope.positionForm.name = "";
	};
	$scope.createPosition = function() {
		_clearFormData();
	}
	$scope.editPosition = function(position) {
		if (position != null) {
			$scope.positionForm.id = position.id;
			$scope.positionForm.name = position.name;
		} else {
			console.log(position);
		}
	};
	//


	//

});