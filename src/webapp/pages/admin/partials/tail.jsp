<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
	integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
<!-- Alert mess -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
	(function() {
		'use strict';
		// Toggle sidebar on Menu button click
		$('#sidebarCollapse').on('click', function() {
			$('#sidebar').toggleClass('active');
			$('#body').toggleClass('active');
			console.log()

			if (!$('#body').hasClass('active')) {
				$('#body').css({
					"width" : "100%"
				});
				$('#body>.navbar').css({
					"left" : "0"
				})
			} else {
				$('#body').css({
					"width" : "calc(100% - 250px)"
				});
				$('#body>.navbar').css({
					"left" : "250px"
				})
			}
		});
	})();
</script>