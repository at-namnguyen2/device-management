


function pagination(item, totalPage, query, callback){
	$("#" + item + "").twbsPagination({
		totalPages : totalPage,
		visiblePages : 3,
		next : 'Next',
		prev : 'Prev',
		onPageClick : function(event, page) {
			callback(item,page,query);
		}
			});
}

$(document).ready(function() {
$("#left-content").load("left-container");
$("#top-content").load("topbar");
})