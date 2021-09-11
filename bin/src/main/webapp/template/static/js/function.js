/**
 * 
 */
// Custom function.
//Tab item

let listItem = document.getElementsByClassName("navItem");
for (let i = 0; i < listItem.length; i++) {
	let index = i;
	listItem[i].addEventListener("click", function() {
		for (var j = 0; j < listItem.length; j++) {
			listItem[j].classList.remove("currentNavItem");
		}
		listItem[index].classList.add("currentNavItem");
	});
}

// Event dashboard

function logoutEvent() {
	localStorage.clear();
	sessionStorage.clear();
	location.href = "http://localhost:8080/EmployeeManager/login";
}

//Responsive dashboard

function openModalEvent() {
	console.log(window.screen.width);
	let _width = window.screen.width;
	if (_width > 580) {
		$('.navbars').css({
			'width': '45%',
			'transition': '.5s'
		});
	} else {
		$('.navbars').css({
			'width': '70%',
			'transition': '.5s'
		});
	}
}

function closeModalEvent() {
	$('.navbars').css({
		'width': '0%',
		'transition': '.5s'
	});
}

//Other Event

function getFeatures(scopeFeatures) {
	let features = scopeFeatures.map(key => {
		let keys = [];
		if (key.match(/add/g) !== null) {
			keys.push(key)
		}
		return keys;
	});
	features = [].concat(...features);
	return features;
}
function checkAllScope() {
	let checkboxs = document.getElementsByName("name[]");
	for (let i = 0; i < checkboxs.length; i++) {
		checkboxs[i].checked = !checkboxs[i].checked;
	}
}

function changeViewEvent(event) {
	this.viewTeam = event;
	if (event != "file/team/listteam.html") {
		this.nameDefault = "Detail team";
		this.hideShow = true;
	} else {
		this.nameDefault = "List team";
		this.hideShow = false;
	}
}

/*function choseTeam(event) {
	this.viewTeam = "file/team/inforteam.jsp";
	if (this.viewTeam != "file/team/inforteam.jsp") {
		this.hideShow = true;
	} else {
		this.hideShow = false;
	}
}*/

function changeViewEventEmployee(event) {
	this.viewEmployee = event;
	if (event != "file/employee/listemployee.html") {
		this.nameDefault = "Add Employee";
		this.hideShow = true;
	} else {
		this.nameDefault = "Employee";
		this.hideShow = false;
		_refreshEmployeeData(0);
	}
}

function choseTeamEmployee(event) {
	this.viewEmployee = "file/employee/addemployee.html";
	if (this.viewEmployee != "file/employee/addemployee.html") {
		this.hideShow = true;
		_refreshEmployeeData(0);
	} else {
		this.hideShow = false;
	}
}

function changeViewEventPosition(event) {
	this.viewPosition = event;
	if (event != "file/position/listposition.html") {
		this.nameDefault = "Edit Position";
		this.hideShow = true;
	} else {
		this.nameDefault = "Position";
		this.hideShow = false;
	}
}

function choseTeamPosition(event) {
	this.viewPosition = "file/position/addposition.html";
	if (this.viewPosition != "file/position/addposition.html") {
		this.hideShow = true;
	} else {
		this.hideShow = false;
	}
}