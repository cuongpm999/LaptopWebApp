<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tich hop jstl vao jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- D E L E T E -->
<div class="modal fade" tabindex="-1" role="dialog" id="modalDeleteForm">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Xoá dữ liệu</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Bạn có chắc chắn muốn xoá dữ liệu không ?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger mr-auto" id="deleteOk">Đồng ý</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" tabindex="-1" role="dialog"
	id="modalEditItem">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Sửa dữ liệu</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Bạn có chắc chắn muốn sửa mặt hàng này không ?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success mr-auto" id="editOk">Đồng ý</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" tabindex="-1" role="dialog"
	id="modalDeleteItem">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Xóa dữ liệu</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Bạn có chắc chắn muốn xóa mặt hàng này không ?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger mr-auto"
					id="deleteproduct">Đồng ý</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
			</div>
		</div>
	</div>
</div>

<!-- ---------------------------------------------------------------------------------------------- -->
<div class="modal fade" tabindex="-1" role="dialog"
	id="modalAddItem">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<p>Bạn đã thêm hàng vào trong giỏ hàng</p>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" tabindex="-1" role="dialog"
	id="modalAddItemFailed">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<p>Bạn đã click số lần vượt quá số sản phẩm tồn trong kho</p>
			</div>
		</div>
	</div>
</div>

<!-- ---------------------------------------------------------------------------------------------- -->



