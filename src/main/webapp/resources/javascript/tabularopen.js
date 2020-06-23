//Tabulator table creation for all open tickets
var opentable = new Tabulator("#open-employee-tickets", {
	ajaxURL : "http://localhost:8080/Project1BackendWAR/getallopen.json",
	ajaxConfig : "POST",
	ajaxContentType : "json",
	height : "300px",
	columns : [ {
		title : "Ticket ID",
		field : "ticketId",
		sorter : "number",
	}, {
		title : "Owner Username",
		field : "ticketOwner",
		sorter : "string",
	}, {
		title : "Reimbursement Type",
		field : "ticketType",
		sorter : "string",
	}, {
		title : "Amount Requested",
		field : "reqAmount",
		formatter : "money",
		sorter : "number",
	}, {
		title : "Submission Date",
		field : "submissionDate",
		formatter : "datetime",
		formatterParams : {inputFormat: moment.ISO_8601, outputFormat: moment.ISO_8601, invalidPlaceholder: "(invalid date)"},
		sorter : "datetime",
	}, {
		title : "Receipt",
		field : "image",
		formatter : "image",
	}, {
		title : "Explaination",
		field : "elaboration",
	}],
});