angular.module('myApp').config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl: 'file/employee/defaultemployee.html',
		controller: 'employeeCtrl'
	});
}).directive('customFileInput', [function() {
	return {
		link: function($scope, element, attrs) {
			element.on('change', function(evt) {
				var files = evt.target.files;
				$scope.eachUser.image = files[0].name;
				console.log($scope.eachUser.image);
			});
		}
	}
}]).controller('detailEmployeeCtrl', function($scope, $http, $mdDialog) {
	$scope.options = {
		language: 'en',
		allowedContent: true,
		entities: false
	};
	// Get data User
	$scope.detailUser = JSON.parse(sessionStorage.detailuser);
	$scope.authorize = JSON.parse(localStorage.user)

	function _loadRoleData() {
		$http({
			method: 'GET',
			url: '/EmployeeManager/api/position'
		}).then(
			function(res) {
				$scope.roles = res.data;
			},
			function(res) {
				console.log("Error: " + res.status + " : " + res.data);
			}
		);
	}

	function _loadTeamData() {
		$http({
			method: 'GET',
			url: '/EmployeeManager/api/team?page=1&limit=2'
		}).then(function(res) { // success
			$scope.teams = res.data;

			$scope.selectTeam = $scope.teams.listresult;

		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}

	_loadTeamData();
	_loadRoleData();

	$scope.eachUser = {
		id: $scope.detailUser.id,
		name: $scope.detailUser.name,
		nickName: $scope.detailUser.nickName,
		age: $scope.detailUser.age,
		dayStart: $scope.detailUser.dayStart,
		profile: $scope.detailUser.profile,
		status: $scope.detailUser.status,
		email: $scope.detailUser.email,
		message: $scope.detailUser.message,
		nameRole: $scope.detailUser.roleName,
		sex: $scope.detailUser.sex,
		idRole: $scope.detailUser.idRole,
		idTeam: $scope.detailUser.idTeam,
		image: $scope.detailUser.image
	}

	$scope.displaySex = [
		{
			id: 0,
			name: "Male"
		},
		{
			id: 1,
			name: "Female"
		}
	];

	function convertSex() {
		if ($scope.eachUser.sex == "0") {
			$scope.icon = '<span class="icon_male"><i class="fas fa-mars"</i></span>';
		} else {
			$scope.icon = '<span class="icon_female"><i class="fas fa-venus"</i></span>';
		}
	}
	convertSex();

	// edit User
	$scope.editUser = function(eachUser) {
		console.log(eachUser);
		console.log($scope.authorize.id == $scope.detailUser.id)
        if ($scope.authorize.roleName != "ADMIN" && $scope.authorize.id != $scope.eachUser.id) {
            _loadDiaLogPermission();
        } else {
            if (eachUser.idRole == null || eachUser.idTeam == null) {
                alert("Form can't blank");
            } else {
                $http({
                    method: 'PUT',
                    url: '/EmployeeManager/api/user/' + eachUser.id,
                    data: angular.toJson(eachUser),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(function(response) {
                    console.log(response);
                }, function(response) {
                    console.log(response);
                });
            }
        }
	};

	// Back to home
	$scope.backEmployee = function() {
		sessionStorage.clear();
		$scope.viewEmployee = "file/employee/listemployee.html";
	};

	// Click select row
	$scope.toggle = function(item, list) {
		var idx = list.indexOf(item);
		if (idx > -1) {
			list.splice(idx, 1);
		} else {
			list.push(item);
		}
	};

	// Check select row
	$scope.exists = function(item, list) {
		return list.indexOf(item) > -1;
	};

	function _error(res) {
		var data = res.data;
		var status = res.status;
		var header = res.header;
		var config = res.config;
		alert("Error: " + status + ":" + data);
	}

	/*--- Tab TECHNICAL ---*/
	$scope.TechnicalForm = {
		skill: "",
		level: ""
	}
	$scope.technicals = [];
	$scope.selectCheckTechnical = [];


	// method GET data with API Technical
	function _loadTechnicalData(detailUser) {
		// Call API findAll Data
		$http({
			method: 'GET',
			url: '/EmployeeManager/' + $scope.detailUser.id + '/api/technical'
		}).then(function(res) { // success
			$scope.technicals = res.data;

			// Check select all
			$scope.isIndeterminateTechnical = function() {
				return ($scope.selectCheckTechnical.length !== 0 &&
					$scope.selectCheckTechnical.length !== $scope.technicals.length);
			};

			// Check select all
			$scope.isCheckedTechnical = function() {
				return $scope.selectCheckTechnical.length === $scope.technicals.length;
			};

			// Click select all
			$scope.toggleAllTechnical = function() {
				if ($scope.selectCheckTechnical.length === $scope.technicals.length) {
					$scope.selectCheckTechnical = [];
				} else if ($scope.selectCheckTechnical.length === 0 || $scope.selectCheckTechnical.length > 0) {
					$scope.selectCheckTechnical = $scope.technicals.slice(0);
				}
			};
			/*================panageble==========================================================*/
			$scope.filteredTechnical = []
			$scope.currentPageTech = { page: 1 };
			$scope.numPerPageTech = 4;
			$scope.maxSizeTech = 10;

			$scope.makeTodosTechnical = function() {
				$scope.todosTech = [];
				for (i = 0; i < $scope.technicals.length; i++) {
					$scope.todosTech.push($scope.technicals[i]);
				}
			};
			$scope.makeTodosTechnical();

			$scope.$watch('currentPageTech.page', function() {
				var beginTech = (($scope.currentPageTech.page - 1) * $scope.numPerPageTech)
					, endTech = beginTech + $scope.numPerPageTech;

				$scope.filteredTechnical = $scope.todosTech.slice(beginTech, endTech);

			}, true);
			/*============================*/
		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}

	// Load the data Technical from server
	_loadTechnicalData($scope.detailUser);


	// Method Delete List Technical
	$scope.deleteListTechnical = function(selectCheckTechnical) {
        if ($scope.authorize.roleName != "ADMIN" && $scope.authorize.id != $scope.detailUser.id) {
            _loadDiaLogPermission();
        } else {
            // array ID Technical
            let idTechnicals = [];
            for (let i of selectCheckTechnical) {
                idTechnicals.push(i.id);
            }

            // Delete call API delete
            $http({
                method: 'DELETE',
                url: '/EmployeeManager/' + $scope.detailUser.id + '/api/technical/' + idTechnicals,
            }).then(function(response) {
                _loadTechnicalData();
            }, function(response) {
                console.log(response + selectCheckTechnical);
            });
        }
	};

	// method POST data with API Technical
	$scope.submitTechnical = function() {
        if ($scope.authorize.roleName != "ADMIN" && $scope.authorize.id != $scope.detailUser.id) {
            _loadDiaLogPermission();
        } else {
            if ($scope.TechnicalForm.skill == "" || $scope.TechnicalForm.level == "") {
                return alert("Blank Form!");
            } else {
                $http({
                    method: 'POST',
                    url: '/EmployeeManager/' + $scope.detailUser.id + '/api/technical',
                    data: angular.toJson($scope.TechnicalForm),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(_successTechnical, _error);
            }
        }
	};

	$scope.showAdvancedTechnical = function(technical) {
	    if ($scope.authorize.roleName != "ADMIN" && $scope.authorize.id != $scope.detailUser.id) {
            _loadDiaLogPermission();
        } else {
            $mdDialog.show({
                templateUrl: 'dialogEditTechnical.tmpl.html',
                // Appending dialog to document.body to cover sidenav in docs app
                // Modal dialogs should fully cover application to prevent
                // interaction outside of dialog
                parent: angular.element(document.body),
                targetEvent: technical,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen, // Only for -xs, -sm
                // breakpoints.
                controller: function($scope, $mdDialog) {
                    $scope.dataValue = technical;
                    $scope.hideTech = function() {
                        $mdDialog.hide();
                    };

                    $scope.cancelTech = function() {
                        $mdDialog.cancel();
                    };

                    $scope.answerTech = function(answer) {
                        $mdDialog.hide(answer);
                    };
                    // edit Technical
                    $scope.editTechnical = function(tech) {
                        $http({
                            method: 'PUT',
                            url: '/EmployeeManager/api/technical/' + tech.id,
                            data: angular.toJson(tech),
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        }).then(_successAdvantage, _error);
                    };
                }
            }).then(function(answer) {
            }, function() {
            });
        }
	};

	function _clearTechnicalFormData() {
		$scope.TechnicalForm.skill = "",
			$scope.TechnicalForm.level = ""
	};

	function _successTechnical(res) {
		_loadTechnicalData();
		_clearTechnicalFormData();
	}


	/*--- Tab ADVANTAGE ---*/
	$scope.advantageForm = {
		name: ""
	}

	$scope.advantages = [];
	$scope.selectCheckAdvantage = [];
	// method GET data with API Technical
	function _loadAdvantageData(detailUser) {
		// Call API findAll Data
		$http({
			method: 'GET',
			url: '/EmployeeManager/' + $scope.detailUser.id + '/api/advantage'
		}).then(function(res) { // success
			$scope.advantages = res.data;

			// Check select all
			$scope.isIndeterminateAdvantage = function() {
				return ($scope.selectCheckAdvantage.length !== 0 &&
					$scope.selectCheckAdvantage.length !== $scope.advantages.length);
			};

			// Check select all
			$scope.isCheckedAdvantage = function() {
				return $scope.selectCheckAdvantage.length === $scope.advantages.length;
			};

			// Click select all
			$scope.toggleAllAdvantage = function() {
				if ($scope.selectCheckAdvantage.length === $scope.advantages.length) {
					$scope.selectCheckAdvantage = [];
				} else if ($scope.selectCheckAdvantage.length === 0 || $scope.selectCheckAdvantage.length > 0) {
					$scope.selectCheckAdvantage = $scope.advantages.slice(0);
				}
			};

			/*================panageble==========================================================*/
			$scope.filteredAdvantage = []
			$scope.currentPageAdvan = { page: 1 };
			$scope.numPerPageAdvan = 7;
			$scope.maxSizeAdvan = 3;

			$scope.makeTodosAdvantage = function() {
				$scope.todosAdvan = [];
				for (i = 0; i < $scope.advantages.length; i++) {
					$scope.todosAdvan.push($scope.advantages[i]);
				}
			};
			$scope.makeTodosAdvantage();

			$scope.$watch('currentPageAdvan.page', function() {
				var beginAdvan = (($scope.currentPageAdvan.page - 1) * $scope.numPerPageAdvan)
					, endAdvan = beginAdvan + $scope.numPerPageAdvan;

				$scope.filteredAdvantage = $scope.todosAdvan.slice(beginAdvan, endAdvan);

			}, true);
			/*============================*/

		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}

	// Load the data Technical from server
	_loadAdvantageData($scope.detailUser);

	// Method Delete List Advantage
	$scope.deleteListAdvantage = function(selectCheckAdvantage) {
        if ($scope.authorize.roleName != "ADMIN" && $scope.authorize.id != $scope.detailUser.id) {
            _loadDiaLogPermission();
        } else {
            // array ID Advantage
            let idAdvantages = [];
            for (let i of selectCheckAdvantage) {
                idAdvantages.push(i.id);
            }

            // Delete call API delete
            $http({
                method: 'DELETE',
                url: '/EmployeeManager/' + $scope.detailUser.id + '/api/advantage/' + idAdvantages,
            }).then(function(response) {
                _loadAdvantageData();
            }, function(response) {
                console.log(response + selectCheckAdvantage);
            });
        }
	};

	// method POST data with API Technical
	$scope.submitAdvantage = function() {
        if ($scope.authorize.roleName != "ADMIN" && $scope.authorize.id != $scope.detailUser.id) {
            _loadDiaLogPermission();
        } else {
            if ($scope.advantageForm.name == "") {
                return alert("Blank Form!");
            } else {
                $http({
                    method: 'POST',
                    url: '/EmployeeManager/' + $scope.detailUser.id + '/api/advantage',
                    data: angular.toJson($scope.advantageForm),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(_successAdvantage, _error);
            }
        }
	};

	$scope.showAdvanced = function(advantage) {
        if ($scope.authorize.roleName != "ADMIN" && $scope.authorize.id != $scope.detailUser.id) {
            _loadDiaLogPermission();
        } else {
            $mdDialog.show({
                templateUrl: 'dialogEditAdvantage.tmpl.html',
                // Appending dialog to document.body to cover sidenav in docs app
                // Modal dialogs should fully cover application to prevent
                // interaction outside of dialog
                parent: angular.element(document.body),
                targetEvent: advantage,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen, // Only for -xs, -sm
                // breakpoints.
                controller: function($scope, $mdDialog) {
                    $scope.dataValue = advantage;
                    $scope.hideAdvantage = function() {
                        $mdDialog.hide();
                    };

                    $scope.cancelAdvantage = function() {
                        $mdDialog.cancel();
                    };

                    $scope.answerAdvantage = function(answer) {
                        $mdDialog.hide(answer);
                    };
                    // edit Advantage
                    $scope.editAdvantage = function(advantage) {
                        $http({
                            method: 'PUT',
                            url: '/EmployeeManager/api/advantage/' + advantage.id,
                            data: angular.toJson(advantage),
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        }).then(_successAdvantage, _error);
                    };
                }
            }).then(function(answer) {
            }, function() {
            });
        }
	};

	function _clearAdvantageFormData() {
		$scope.advantageForm.name = ""
	};

	function _successAdvantage(res) {
		_loadAdvantageData();
		_clearAdvantageFormData();
	}

    function _loadDiaLogPermission(){
        $mdDialog.show({
        	templateUrl: 'dialogPermission.tmpl.html',
        	parent: angular.element(document.body),
        	clickOutsideToClose: true,
        	fullscreen: $scope.customFullscreen,
        	controller: function($scope, $mdDialog) {
        		$scope.cancelPermission = function() {
        			$mdDialog.cancel();
        			location.reload();
        		};
        	}
        });
    }

});
