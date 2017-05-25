var judgeIndex = (function (config, functions) {
    return {
        initPager:function(totalCount){
            var me=this;
            var totalPage = totalCount / config.perLoadCounts.table;
            var totalRecords = totalCount;
            var pageNo = 1;
            kkpager.generPageHtml({
                pno: pageNo,
                isGoPage: false,
                isShowTotalPage: false,
                isShowCurrPage: false,
                mode: 'click', //设置为click模式
                //总页码
                total: totalPage,
                //总数据条数
                totalRecords: totalRecords,
                //点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
                //适用于不刷新页面，比如ajax
                click: function (n) {
                    //这里可以做自已的处理
                    //...
                    //处理完后可以手动条用selectPage进行页码选中切换
                    this.selectPage(n);

                    me.loadData((n - 1) * config.perLoadCounts.table);

                }
            });
        },
        loadData: function (start, callback) {
            $.ajax({
                url: config.ajaxUrls.judgeToScoreList,
                type: "post",
                data: {
                    userId: judgeId,
                    offset: start,
                    scoreSign:$("#zyFilter .zyActive").data("value"),
                    limit:config.perLoadCounts.table
                },
                success: function (response) {
                    if (response.success) {
                        var results = response.aaData,
                            totalCount = response.iTotalRecords, listTpl;

                        listTpl = $("#zyListTpl").html();
                        $("#ZyList").html(juicer(trTpl, {
                            items: results
                        }));

                        if (callback) {
                            callback(totalCount);
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

$(document).ready(function () {
    //重新定义juicer的取变量标签，因为和jstl的重复了
    juicer.set({
        'tag::interpolateOpen': '$ZY{'
    });

    $("#zyFilter .zyItem").click(function(){
        $("#zyFitler .zyActive").removeClass("zyActive");
        $(this).addClass("zyActive");
        judgeIndex.loadData(0, function(totalCount){
            judgeIndex.initPager(totalCount);
        });
    });

    judgeIndex.loadData(0, function (totalCount) {
        judgeIndex.initPager(totalCount);
    });
});