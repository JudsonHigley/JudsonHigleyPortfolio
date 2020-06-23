// Tabulator table creation for single employee records from FA page
var fatable = new Tabulator("#single-employee-history", {
	ajaxURL : "http://localhost:8080/Project1BackendWAR/getnotsessionuserhistory.json",
	ajaxConfig : "POST",
	ajaxContentType: "json",
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
		field : "submitDate",
		formatter : "datetime",
		formatterParams:{inputFormat: moment.ISO_8601, outputFormat: moment.ISO_8601, invalidPlaceholder: "(invalid date)"},
		sorter : "datetime",
	}, {
		title : "Receipt",
		field : "image",
		formatter : "image",
	}, {
		title : "Explaination",
		field : "elaboration",
	}, {
		title : "Status",
		field : "ticketStatus",
		sorter : "string",
	}, {
		title : "Evaluating FA",
		field : "ticketEvaluator",
		sorter : "string",
	}, {
		title : "Resolution Date",
		field : "resolutionDate",
		sorter : "datetime",
		formatter : "datetime",
	} ],
});