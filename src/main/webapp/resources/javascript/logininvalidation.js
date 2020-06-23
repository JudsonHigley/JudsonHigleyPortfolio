//window.onload = (function(){
//  $.ajax({url: "/Project1BackendWAR/invalidate.change"});
//});
//$(document).ready(function(){
//	$.ajax({ url: "/Project1BackendWAR/invalidate.change",
//	        success: function(){
//	           alert("done");
//	        }});
//	});
$( document ).ready(function(f){
            $.ajax({
                url: '/Project1BackendWAR/invalidate.change',
                type: 'POST',
                data: 'stuff'
            });
        });