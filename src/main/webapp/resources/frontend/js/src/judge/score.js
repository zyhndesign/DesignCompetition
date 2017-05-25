$(document).ready(function(){

    var pimageArray=JSON.parse(pimage);
    var pimageHtmlArray=[];
    for(var i= 0,len=pimageArray.length;i<len;i++){
        pimageHtmlArray.push('<img src="'+pimageArray[i]+'" style="margin:10px auto;">');
    }
    $("#zyWorkDetail").append(pimageHtmlArray.join(''));

    $("#zySaveScore").click(function(){
        var score = $("#zyScore").val(),
            reg=/^[0-9]+$/;
        if(reg.test(score)){
            functions.showLoading();
            $.ajax({
                url:"#",
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
