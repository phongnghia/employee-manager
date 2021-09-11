/**
 * 
 */
angular.module('myApp').config(function($routeProvider) {
	$routeProvider.when('/team', {
		templateUrl: "file/team/indexTeam.html",
		controller: "teamCtrl"
	}).when('/AddTeam', { //Routing for show list of employee
		templateUrl: 'file/team/editTeam.html',
		controller: 'teamCtrl'
	}).when('/UpdateTeam', { //Routing for add employee
		templateUrl: 'file/team/updateTeam.html',
		controller: 'teamCtrl'
	}).when('/InformationTeam', {
		templateUrl: 'file/team/informationteam.html',
		controller: 'teamCtrl'
	})
}).controller('teamCtrl', function($scope, $http, $location, filterFilter, $mdDialog,
	$routeParams) {

	// Dialog
	$scope.addTeam = function() {
		$mdDialog.show({
			templateUrl: 'dialogAddTeam.tmpl.html',
			parent: angular.element(document.body),
			clickOutsideToClose: true,
			fullscreen: $scope.customFullscreen,
			controller: function($scope, $mdDialog) {
				$scope.cancelTeam = function() {
					$mdDialog.cancel();
				};

				$scope.answerTeam = function(answer) {
					$mdDialog.hide(answer);
				};
				$scope.addTeam = function() {
					$http({
						method: 'POST',
						url: 'http://localhost:8080/EmployeeManager/api/team',
						data: $scope.loadteams
					}).then(function(res) {
						_loadTeamData();
					}, function(res) { // error
						console.log("Error: " + res.status + " : " + res.data);
					});
				};
			}
		}).then(function(answer) {

		}, function() {
		});
	};

	$scope.pleaseChose = "Please chose !";
	$scope.checkChose = true;
	$scope.nameDefault = "Team";
	$scope.showMe = false;
	if (sessionStorage.loadbyids) {
		$scope.loadteamss = JSON.parse(sessionStorage.loadbyids);
	}


	/*==========================================================================*/
	/*loadAllTeam========================================*/
	_loadTeamData();

	$scope.teams;

	function _loadTeamData() {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/EmployeeManager/api/team'
		}).then(function(res) { // success
			$scope.teams = res.data;

			/*================panageble==========================================================*/
			$scope.filteredTodos = []
				, $scope.currentPage = 1
				, $scope.numPerPage = 9
				, $scope.maxSize = 3;

			$scope.makeTodos = function() {
				$scope.todos = [];
				for (i = 0; i < $scope.teams.listresult.length; i++) {
					$scope.todos.push({ text: $scope.teams.listresult[i], done: false });
				}
			};
			$scope.makeTodos();

			$scope.$watch('currentPage + numPerPage', function() {
				var begin = (($scope.currentPage - 1) * $scope.numPerPage)
					, end = begin + $scope.numPerPage;

				$scope.filteredTodos = $scope.todos.slice(begin, end);
			});
			/*============================*/

		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}

	/*///////////loadAllTeam====================================================*/
	/*=========checked================*/
	$scope.userChecked = [];
	$scope.checkedAll = false;

	$scope.checkItem = function(id, checked) {
		if (checked) {
			$scope.userChecked.push(id);
		} else {
			$scope.userChecked.pop();
			if ($scope.userChecked.length < 1) {
				$scope.checkedAll = true;
			}
		}

		console.log('$scope.userChecked', $scope.userChecked);
	};

	$scope.checkAll = function(checked) {
		$scope.userChecked = [];

		angular.forEach($scope.teams.listresult, function(value, key) {
			value.selected = checked;
			$scope.userChecked.push(value.id);
		});

		if (!checked) {
			$scope.userChecked = [];
		}

		console.log('$scope.userChecked', $scope.userChecked);
	};




	/*insertTeam===============================================*/

	$scope.Add = function() {
		$http({
			method: 'POST',
			url: 'http://localhost:8080/EmployeeManager/api/team',
			data: $scope.loadteams
		}).then(function(res) { // success
			$location.path('/AddTeam');
			$scope.success = "insert success";
			$scope.showMe = !$scope.showMe;

		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}

	/*updateTeam==============================================*/
	/*loadbyidteam*/
	$scope.loadupdateteam;
	$scope.loadbyid = function(loadteams) {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/EmployeeManager/api/team/loadteam/' + loadteams + ''
		}).then(function(res) { // success
			$location.path('/UpdateTeam');
			sessionStorage.setItem("loadbyids", JSON.stringify(res.data));
		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}
	if (sessionStorage.loadbyids) {
		$scope.loadupdateteam = JSON.parse(sessionStorage.loadbyids);
		$scope.idTeam = $scope.loadupdateteam.idTeam;
		$scope.nameTeam = $scope.loadupdateteam.nameTeam;
		$scope.managername = $scope.loadupdateteam.managername;
	}



	$scope.GetValue = function() {

		$scope.message = [];
		$scope.managername;
		$scope.nameTeam;
		$scope.idTeam;
		for (var i = 0; i < $scope.loadupdateteam.listallUser.length; i++) {
			if ($scope.loadupdateteam.listallUser[i].Selected || $scope.loadupdateteam.listallUser[i].checked != null) {
				var fruitId = $scope.loadupdateteam.listallUser[i].id;
				$scope.message.push(fruitId);
			}
		}

		$scope.result = {
			checkeds: $scope.message,
			managername: $scope.managername,
			nameTeam: $scope.nameTeam,
			idTeam: $scope.idTeam
		};
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/EmployeeManager/api/team',
			data: $scope.result
		}).then(function(res) { // success
			_loadTeamData()
			$location.path('/team');
		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});

	}

	/*deleteTeam================================================*/

	$scope.Delete = function() {
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/EmployeeManager/api/team/' + $scope.userChecked
		}).then(function(res) { // success
			$scope.deletesuccsess = "xoa thanh cong";
			_loadTeamData();
			$location.path('/team');
			$scope.userChecked = [];
		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}

	/*detailsTeamByIDteam================================================*/


	/*inforteam=====================================================================*/
	$scope.Information = function(id) {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/EmployeeManager/api/team/inforteam/' + id + '',
		}).then(function(res) { // success
			sessionStorage.setItem("inforname", JSON.stringify(res.data));
			$location.path('/InformationTeam');

		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}

	$scope.infornames;
	if (sessionStorage.inforname) {
		$scope.infornames = JSON.parse(sessionStorage.inforname);
	}


	/*============================================infornameitem============*/

	$scope.informationitem = function(userid) {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/EmployeeManager/api/user/' + userid + '/inforteamitem',
		}).then(function(res) { // success
			$scope.checkChose = false;
			$scope.infornameitem = res.data;

		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}



});