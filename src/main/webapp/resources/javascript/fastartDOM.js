window.onload = function() {
	getUserOptions();
}

function getUserOptions() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let users = JSON.parse(xhttp.responseText);
			console.log(users);
			
			let inputselection = document.getElementById("inputhistoryuser");
			let cur;
			for(cur of users) {
				let option = document.createElement("option");
				option.text = cur.username;
				inputselection.add(option);
			}
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1BackendWAR/getusers.json");
	xhttp.send();
}