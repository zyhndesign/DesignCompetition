var worksMgr=(function(config,functions){

    return {
        loadJudgeRound:function(){
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
        },
        setStatusOfWork:function(id,statusValue){
            var me=this;
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.workSetStatus,
                type:"post",
                data:{
                    id:id,
                    statusValue:statusValue
                },
                success: function (response) {
                    if (response.success) {
                        me.dataTable.tableRedraw();
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

    worksMgr.loadJudgeRound();

    worksMgr.dataTable= new ZYTableHandler({
        ownTable:function(){
            var ownTable=$("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.worksGetByPage,
                "bInfo":true,
                "bLengthChange": false,
                "bFilter": false,
                "bProcessing":true,
                "bSort":false,
                "bAutoWidth": false,
                "iDisplayLength":config.perLoadCounts.table,
                "sPaginationType":"full_numbers",
                "oLanguage": {
                    "sUrl":config.dataTable.langUrl
                },
                "aoColumns": [
                    { "mDataProp": "thumb",
                        "fnRender":function(oObj){
                            return '<img class="thumb" src="'+oObj.aData.thumb+'">';
                        }
                    },
                    { "mDataProp": "title"},
                    { "mDataProp": "groupId",
                        "fnRender":function(oObj){
                            return config.workGroup[oObj.aData.groupId];
                    }},
                    { "mDataProp": "realname"},
                    { "mDataProp": "status",
                        "fnRender":function(oObj){
                            var htmlArray=[];
                            htmlArray.push("<select class='setWorkStatus' data-id='"+oObj.aData.id+"'>");
                            for(var o in config.workStatus){
                                htmlArray.push("<option value='"+o+"'>"+config.workStatus[o]+"</option>");
                            }

                            htmlArray.push("</select>");
                            return htmlArray.join("");
                    }},
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            //return '<a href="'+oObj.aData.pId+'" class="delete">删除</a>';

                            return '';
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {
                    aoData.push(/*{
                        name:"title",
                        value:$("#searchContent").val()
                    },*/{
                        name:"groupId",
                        value:0
                    },{
                        name:"roundJudge",
                        value:$("#judgeRound").val()
                    },{
                        name:"status",
                        value:$("#status").val()
                    })
                },
                "fnServerData": function(sSource, aoData, fnCallback) {

                    //回调函数
                    $.ajax({
                        "dataType":'json',
                        "type":"post",
                        "url":sSource,
                        "data":aoData,
                        "success": function (response) {
                            if(response.success===false){
                                functions.ajaxReturnErrorHandler(response.message);
                            }else{
                                var json = {
                                    "sEcho" : response.sEcho
                                };

                                for (var i = 0, iLen = response.aaData.length; i < iLen; i++) {
                                    response.aaData[i].opt="opt";
                                }

                                json.aaData=response.aaData;
                                json.iTotalRecords = response.iTotalRecords;
                                json.iTotalDisplayRecords = response.iTotalDisplayRecords;
                                fnCallback(json);
                            }

                        }
                    });
                },
                "fnFormatNumber":function(iIn){
                    return iIn;
                }
            });

            return ownTable;
        }
    })
    $("#myTable").on("click", ".setWorkStatus", function () {
        worksMgr.setStatusOfWork($(this).data("id"),$(this).val());
    })
    $("#searchBtn").click(function(e){
        worksMgr.dataTable.tableRedraw();
    });
    $("#searchByJudgeRound").change(function(){
        worksMgr.dataTable.tableRedraw();
    });
    $("#searchByStatus").change(function(){
        worksMgr.dataTable.tableRedraw();
    })
});

