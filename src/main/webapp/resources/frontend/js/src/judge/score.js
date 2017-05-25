var score = (function (config, functions) {
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
$(document).ready(function(){

    score.loadWorkDetail(function(response){
        var pimageArray=JSON.parse(response.pimage);
        var pimageHtmlArray=[];
        for(var i= 0,len=pimageArray.length;i<len;i++){
            pimageHtmlArray.push('<img src="'+pimageArray[i]+'" style="margin:10px auto;">');
        }
        $("#zyWorkDetail").append(pimageHtmlArray.join(''));
    });

    $("#zySaveScore").click(function(){
        var score = $("#zyScore").val(),
            reg=/^[0-9]+$/;
        if(reg.test(score)){
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.judgeScore,
                type:"post",
                data:{
                    score:parseInt(score)
                },
                success:function(response){
                    if (response.success) {
                        $().toastmessage("showSuccessToast",config.messages.scoreSaved);

                        functions.hideLoading();
                    } else {
                        functions.ajaxReturnErrorHandler(response.message);
                    }
                },
                error:function(){
                    functions.ajaxErrorHandler();
                }
            })
        }else{
            $().toastmessage("showErrorToast",config.messages.scoreError);
        }
    });
});
