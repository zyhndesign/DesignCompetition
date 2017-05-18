var worksMgr=(function(config,functions){

    return {

    }

})(config,functions);

$(document).ready(function(){

    var dataTable= new ZYTableHandler({
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
                            return config.workType[oObj.aData.groupId];
                    }},
                    { "mDataProp": "realname"},
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
                                functions.ajaxReturnErrorHandler(response.error_code);
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

    $("#searchBtn").click(function(e){
        dataTable.tableRedraw();
    });
});

