function ZYTableHandler(params) {
    this.ownTable = params.ownTable();
    this.removeUrl=params.removeUrl;
}
ZYTableHandler.prototype.tableRedraw = function () {
    this.ownTable.fnSettings()._iDisplayStart = 0;
    this.ownTable.fnDraw();
};
ZYTableHandler.prototype.delete = function (data) {
    functions.showLoading();
    var me = this;
    $.ajax({
        url: me.removeUrl,
        type: "post",
        data:data,
        dataType: "json",
        success: function (response) {
            if (response.success) {
                $().toastmessage("showSuccessToast", config.messages.optSuccess);
                me.ownTable.fnDraw();
                functions.hideLoading();
            } else {
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error: function () {
            functions.ajaxErrorHandler();
        }
    });
};