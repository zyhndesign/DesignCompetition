var workDetail = (function (config, functions) {
    return {
        loadWorkDetail: function (id,callback) {
            $.ajax({
                url: config.ajaxUrls.workDetail,
                type: "get",
                data: {
                    id:id
                },
                success: function (response) {
                    if (response.success) {

                        if(callback){
                            callback();
                        }
                        functions.hideLoading();
                    } else {
                        functions.ajaxReturnErrorHandler(response.message);
                    }
                },
                error: function () {
                    functions.ajaxErrorHandler();
                }
            })
        }
    }
})(config, functions);

$(document).ready(function () {
    workDetail.loadWorkDetail(function(response){
        var pimageArray=JSON.parse(response.pimage);
        var pimageHtmlArray=[];
        for(var i= 0,len=pimageArray.length;i<len;i++){
            pimageHtmlArray.push('<img src="'+pimageArray[i]+'" style="margin:10px auto;">');
        }
        $("#zyWorkDetail").append(pimageHtmlArray.join(''));
    });
});