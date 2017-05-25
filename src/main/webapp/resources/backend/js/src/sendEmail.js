var sendEmail=(function(config,functions){
    return{

    }
})(config,functions);

$(document).ready(function(){
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