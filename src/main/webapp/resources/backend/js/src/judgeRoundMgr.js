var judgeRoundMgr = (function (config, functions) {

    return {
        judgeRoundMap: {},
        judgeTrTpl: ["{@each items as i}<tr>",
            "<td><input type='checkbox' checked='${i.checked}' data-id=''${i.id}'></td>",
            "<td>${i.name}</td></tr>{@/each}"].join(""),
        initJudgeTable: function (list) {
            var html = juicer(this.judgeTrTpl, list);
            $("#judgeTable tbody").html(html);
        },
        setJudge: function (id) {
            $("#judgeRoundName").text(this.judgeRoundMap[id].content);
            $("#judgeTable tbody").html(this.initJudgeTable(this.judgeRoundMap[id].judgeList));
            $("#saveJudgeOfRound").data("id",id);
            $("#setJudgeModal").modal("show");
        },

        saveJudgeOfRound: function (data) {
            var me=this;
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.judgeRoundSetJudge,
                type:"post",
                data:data,
                success: function (response) {
                    if (response.success) {
                        $().toastmessage("showSuccessToast", config.messages.optSuccess);
                        me.dataTable.tableRedraw();
                        $("#setJudgeModal").modal("hide");
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

    judgeRoundMgr.dataTable = new ZYTableHandler({
        ownTable: function () {
            var ownTable = $("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.judgeRoundGetByPage,
                "bInfo": true,
                "bLengthChange": false,
                "bFilter": false,
                "bProcessing": true,
                "bSort": false,
                "bAutoWidth": false,
                "iDisplayLength": 100,
                "sPaginationType": "full_numbers",
                "oLanguage": {
                    "sUrl": config.dataTable.langUrl
                },
                "aoColumns": [
                    { "mDataProp": "name"},
                    { "mDataProp": "judgeName"},
                    { "mDataProp": "opt",
                        "fnRender": function (oObj) {
                            return '<a href="' + oObj.aData.id + '">设置评委</a>&nbsp;&nbsp;' +
                                '<a href="' + config.viewUrls.judgeUpdate.replace(":id", oObj.aData.id) + '">修改</a>&nbsp;&nbsp;' +
                                '<a href="' + oObj.aData.id + '" class="delete">删除</a>';
                        }
                    }
                ],
                "fnServerParams": function (aoData) {
                    /*aoData.push({
                     name:"content",
                     value:$("#searchContent").val()
                     })*/
                },
                "fnServerData": function (sSource, aoData, fnCallback) {

                    //回调函数
                    $.ajax({
                        "dataType": 'json',
                        "type": "post",
                        "url": sSource,
                        "data": aoData,
                        "success": function (response) {
                            if (response.success === false) {
                                functions.ajaxReturnErrorHandler(response.message);
                            } else {
                                var json = {
                                    "sEcho": response.sEcho
                                    },judgeNames;

                                var dataList=response.aaData.rjList,judgeList=response.aaData.jList;

                                for (var i = 0, iLen =dataList.length; i < iLen; i++) {
                                    judgeNames=[];

                                    for(var j = 0, jLen = judgeList; j<jLen; j++){
                                        if(dataList[i].judge.indexOf(judgeList[j].id)!=-1){
                                            judgeNames.push(judgeList[j].name);
                                            judgeList[j].checked=true;
                                        }else{
                                            judgeList[j].checked=false;
                                        }
                                    }

                                    dataList[i].opt = "opt";
                                    dataList[i].judgeName=judgeNames.join(",");
                                    dataList[i].judgeList= $.extend(true,[],judgeList);


                                    judgeRoundMgr.judgeRoundMap[dataList[i].id] = response.aaData[i];
                                }

                                json.aaData = dataList;
                                json.iTotalRecords = response.iTotalRecords;
                                json.iTotalDisplayRecords = response.iTotalDisplayRecords;
                                fnCallback(json);
                            }

                        }
                    });
                },
                "fnFormatNumber": function (iIn) {
                    return iIn;
                }
            });

            return ownTable;
        }
    })

    $("#searchBtn").click(function (e) {
        judgeRoundMgr.dataTable.tableRedraw();
    });

    $("#myTable").on("click", "a.delete", function () {
        if (confirm(config.messages.confirmDelete)) {
            judgeRoundMgr.dataTable.remove(config.ajaxUrls.judgeRoundRemove.replace(":id", $(this).attr("href")));
        }
        return false;
    }).on("click", "a.setJudge", function () {

            return false;
        });

    $("#saveJudgeOfRound").click(function(){
        var judges=[],roundId=$(this).data("id");
        $("#judgeTable tbody input:checked").each(function(index,el){
            judges.push($(this).data("id"));
        });
        judgeRoundMgr.saveJudgeOfRound({
            roundId:roundId,
            judge:judges.join(',')
        });
    });
    $("#checkAll").click(function(){
        var flag = $(this).prop("checked");
        $("#judgeTable tbody input").prop("checked",flag);
    });
});
