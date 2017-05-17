$(document).ready(function(){
    $("#myForm").validate({
        ignore:[],
        rules:{
            email:{
                required:true,
                email:true
            },
            newPwd:{
                required:true,
                rangelength:[6,20]
            },
            confirmPwd:{
                equalTo:"#newPwd"
            }
        },
        messages:{
            email:{
                required:config.validErrors.required,
                email:config.validErrors.email
            },
            newPwd:{
                required:config.validErrors.required,
                rangelength:config.validErrors.rangLength.replace("${max}",20).replace("${min}",6)
            },
            confirmPwd:{
                equalTo:config.validErrors.pwdNotEqual
            }
        },
        submitHandler:function(form) {
            var formObj=$(form).serializeObject();
            $.ajax({
                url:config.ajaxUrls.setPwd,
                type:"post",
                dataType:"json",
                //contentType :"application/json; charset=UTF-8",
                data:formObj,
                success:function(response){
                    if(response.success){
                        $().toastmessage("showSuccessToast",config.messages.setPwdSuccess);
                        setTimeout(function(){
                            window.location.href=config.viewUrls.login;
                        },3000);
                    }else{
                        $().toastmessage("showSuccessToast",response.message);
                    }
                },
                error:function(){
                    $().toastmessage("showSuccessToast",config.messages.networkError);
                }
            });
        }
    });
});
