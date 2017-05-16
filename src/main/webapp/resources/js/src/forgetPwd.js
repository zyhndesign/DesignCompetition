$(document).ready(function(){
    $("#myForm").validate({
        ignore:[],
        rules:{
            email:{
                required:true,
                email:true
            },
            rand:{
                required:true
            }
        },
        messages:{
            email:{
                required:config.validErrors.required,
                email:config.validErrors.email
            },
            rand:{
                required:config.validErrors.required
            }
        },
        submitHandler:function(form) {
            var formObj=$(form).serializeObject();
            $.ajax({
                url:"user/findYourPwd",
                type:"post",
                dataType:"json",
                contentType :"application/json; charset=UTF-8",
                data:JSON.stringify(formObj),
                success:function(response){
                    if(response.success){
                        $().toastmessage("showSuccessToast","请进入邮箱进行密码的修改！");
                    }else{
                        $().toastmessage("showSuccessToast",response.message);
                    }
                },
                error:function(){
                    $().toastmessage("showSuccessToast","网络出错，请稍后重试！");
                }
            });
        }
    });
});
