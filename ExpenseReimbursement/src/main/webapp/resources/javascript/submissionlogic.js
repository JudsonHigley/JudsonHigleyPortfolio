//
//var form = document.getElementById('data');
//var fd = new FormData(form);
//fd.onload = function(event){ 
//    alert("Success! Please click the back button to return to the employee screen.");
//};
//fd.onerror = function(event){ 
//    alert("Oh no, something went wrong.");
//}; 
var form = document.getElementById('data');
var fd = new FormData(form);
var req = new XMLHttpRequest();
req.open('POST', '/Project1BackendWAR/newticket.json', true);
req.send(fd);