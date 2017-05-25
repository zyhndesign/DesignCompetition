var sendEmail=(function(config,functions){
    return{
        loadJudgeRound:function(callback){
            var me=this;
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.judgeRoundGetByPage,
                type:"get",
                data:{
                    iDisplayStart:0,
                    iDisplayLength:100,
                    sEcho:"xxxx"
                },
                success: function (response) {
                    if (response.success) {
                        var htmlArray=[];
                        for(var i= 0,len=response.aaData.jrList.length;i<len;i++){
                            htmlArray.push("<option value='"+response.aaData.jrList[i].id+"'>"+response.aaData.jrList[i].roundName+"</option>")
                        }
                        $("#judgeRound").html(htmlArray.join(""));
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
})(config,functions);

$(document).ready(function(){

    sendEmail.loadJudgeRound();

    var zyFormHandler=new ZYFormHandler({
        submitUrl:config.ajaxUrls.sendEmail,
        redirectUrl:null
    });

    $("#myForm").validate({
        ignore:[],
        rules:{
            round:{
                required:true
            }
        },
        messages:{
            round:{
                required:config.validErrors.required
            }
        },
        submitHandler:function(form) {
            zyFormHandler.submitForm(form,null);
        }
    });
});