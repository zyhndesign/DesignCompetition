var works=(function(config,functions){
    return {
        remove:function(el){
            functions.showLoading();
            var me = this;
            $.ajax({
                url: config.ajaxUrls.workRemove.replace(":id",el.attr("href")),
                type: "get",
                dataType: "json",
                success: function (response) {
                    if (response.success) {
                        $().toastmessage("showSuccessToast", config.messages.optSuccess);
                        el.parents("tr").remove();
                        functions.hideLoading();
                    } else {
                        functions.ajaxReturnErrorHandler(response.message);
                    }

                },
                error: function () {
                    functions.ajaxErrorHandler();
                }
            });
        },
        loadData:function(start){
            $.ajax({
                url:config.ajaxUrls.worksGetByPage,
                type:"get",
                data:{
                    groupId:0,
                    category:0,
                    status:0,
                    userId:0,
                    iDisplayStart:start,
                    iDisplayLength:10,
                    sEcho:"zy"
                },
                success:function(response){
                    if (response.success) {
                        var results=response.aaData,
                            totalCount=response.iTotalRecords, trTpl;
                        for(var i= 0, len = results.length; i<len; i++){
                            results[i].canEdit=true;
                            if(results[i].status>1){
                                results[i].canEdit=false;
                            }
                            results[i].category=config.workType[results[i].category];
                            results[i].status=config.workStatus[results[i].status];
                            results[i].group=config.workGroup[results[i].groupId];
                        }

                        trTpl=$("#zyTrTpl").html();
                        $("#myTable tbody").html(juicer(trTpl,{
                            items:results
                        }));

                        functions.hideLoading();
                    } else {
                        functions.ajaxReturnErrorHandler(response.message);
                    }
                },
                error:function(){
                    functions.ajaxErrorHandler();
                }
            })
        }
    }
})(config,functions);

$(document).ready(function(){
    //重新定义juicer的取变量标签，因为和jstl的重复了
    juicer.set({
        'tag::interpolateOpen': '$ZY{'
    });

    works.loadData(0);

    $("#myTable").on("click",".zyAction.zyIconRemove",function(){
        if(confirm(config.messages.confirmDelete)){
            works.remove($(this));
        }
        return false;
    })
});