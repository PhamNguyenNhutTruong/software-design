const HTTP = "http:";
function getHostname(url) {
	const protocol = $(location).attr('protocol');
	const m = url.match(/^http:\/\/[^/]+/);
	const hostname = m ? m[0] : null;
	if (protocol === HTTP) {
		return hostname + '/CoursesRegistrationApp';
	}
	return hostname;
}

function softDeleteUser() {
	let boardId = "";
	const deleteBtns = $('.btn-delete');


	deleteBtns.click((e) => {
		boardId = e.currentTarget.dataset.bsId;
	})

	$('.btn-confirm-delete').click(() => {
		const url = getHostname($(location).attr('href'));
		$.ajax
			({
				type: "GET",
				url: `${url}/admin/board/delete`,
				data: { boardId },
				success: function(html) {		
					//alert(html);
					window.location.reload();
				}
			})
	})
}

softDeleteUser();