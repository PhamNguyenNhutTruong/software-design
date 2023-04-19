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
	let personId = "";
	const deleteBtns = $('.btn-delete');


	deleteBtns.click((e) => {
		personId = e.currentTarget.dataset.bsId;
	})

	$('.btn-confirm-delete').click(() => {
		const url = getHostname($(location).attr('href'));
		$.ajax
			({
				type: "GET",
				url: `${url}/admin/users/delete`,
				data: { personId },
				success: function(response) {
					if (response === "Success") {
						Swal.fire("Thông báo!", "Xóa tài người dùng thành công", "success").then((result) => {
							/* Read more about isConfirmed, isDenied below */
							if (result.isConfirmed) {
								window.location.reload();
							}
						})
					} else {
						Swal.fire("Thông báo!", "Không thể xóa người dùng này", "error").then((result) => {
							/* Read more about isConfirmed, isDenied below */
							if (result.isConfirmed) {
								window.location.reload();
							}
						})
					}
				}
			})
	})
}

softDeleteUser();


