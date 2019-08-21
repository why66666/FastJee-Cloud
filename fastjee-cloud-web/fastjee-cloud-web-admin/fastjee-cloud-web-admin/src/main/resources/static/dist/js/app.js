var App = function(){

    /**
     * 初始化页数控制和模糊搜索框
     */
    var initSearchbox = function () {
        $("#dataTable_length").parent().removeClass();
        $("#dataTable_filter").parent().removeClass();
        $("#dataTable_length").parent().addClass("col-sm-2");
        $("#dataTable_filter").parent().addClass("col-sm-2");
    };

    /**
     * 追加上方总控制按钮
     */
    var initTopCheck = function (addUrl,deleteUrl) {
        $("#dataTable_filter").parent().after("<div class=\"col-sm-offset-4 col-sm-4\" id='top-check-box'>\n" +
            "                            <a href=\""+addUrl+"\" type=\"button\" class=\"btn btn-sm btn-default\"><i class=\"fa fa-plus\"></i> 新增</a>&nbsp;&nbsp;&nbsp;\n" +
            "                            <button type=\"button\" class=\"btn btn-sm btn-default\" onclick=\"deleteMulti('"+deleteUrl+"')\"><i class=\"fa fa-trash-o\"></i> 删除</button>&nbsp;&nbsp;&nbsp;\n" +
            "                            <a href=\"#\" type=\"button\" class=\"btn btn-sm btn-default\"><i class=\"fa fa-download\"></i> 导入</a>&nbsp;&nbsp;&nbsp;\n" +
            "                            <a href=\"#\" type=\"button\" class=\"btn btn-sm btn-default\"><i class=\"fa fa-upload\"></i> 导出</a>&nbsp;&nbsp;&nbsp;\n" +
            "                            <button type=\"button\" class=\"btn btn-sm btn-primary\" onclick=\"$('#search-box').css('display') == 'none' ? $('#search-box').show('fast') : $('#search-box').hide('fast')\"><i class=\"fa fa-search\"></i> 高级搜索</button>\n" +
            "                        </div>");
    };
    /**
     * 私有方法，初始化 ICheck 插件
     */
    var handlerInitCheckbox = function () {
        // 激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        $('#checkAll').iCheck('uncheck');
    };
    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        //全选
        $('#checkAll').on('ifChecked', function(event){
            $('input').iCheck('check');
        });
        //反选
        $('#checkAll').on('ifUnchecked',function (event) {
            $('input').iCheck('uncheck');
        })
    };
    /**
     * 初始化 DataTables
     */
    var handlerInitDataTables = function (url, columns,addUrl,deleteUrl) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": true,
            "ordering": false,
            "processing": true,
            "searching": true,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "模糊搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                initSearchbox();
                if ($("#top-check-box").length<=0){
                    initTopCheck(addUrl,deleteUrl);
                }
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });

        return _dataTable;
    };


    /**
     * sysGenForward初始化 DataTables
     */
    var sysGenHandlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": false,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "模糊搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                if ($("#top-check-box").length<=0){
                    sysGenInitTopCheck();
                }
                handlerInitCheckbox();
                //handlerCheckboxAll();
            }
        });

        return _dataTable;
    };

    /**
     * sysGen追加上方总控制按钮
     */
    var sysGenInitTopCheck = function () {
        $("#dataTable").before("<div class=\"col-sm-2\" id='top-check-box'>\n" +
            "                            <a type=\"button\" onclick=\"showEditModal() \" class=\"btn btn-sm btn-default\"><i class=\"fa fa-plus\"></i> 新增</a>&nbsp;&nbsp;&nbsp;\n"  );
    };



    /**
     * sysGenRe初始化 DataTables
     */
    var sysGenReHandlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "模糊搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });

        return _dataTable;
    };

    /**
     * sysGenRe初始化 DataTables1
     */
    var sysGenReHandlerInitDataTables1 = function (url, columns) {
        var _dataTable1 = $("#dataTable1").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "模糊搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });

        return _dataTable1;
    };


    return {
        /**
         * 初始化
         *
         * 解决翻页后渲染问题
         */
        init: function(addUrl,deleteUrl) {
            initSearchbox();
            if ($("#top-check-box").length<=0){
                initTopCheck(addUrl,deleteUrl);
            }
            handlerInitCheckbox();
        },
        /**
         * 初始化commons DataTables（顶部控制按钮：新增，删除，导入，导出，高级搜索）
         * @param url
         * @param columns
         * @param addUrl
         * @param deleteUrl
         * @returns {jQuery|*}
         */
        initDataTables : function (url, columns,addUrl,deleteUrl) {
            console.log("进入app.js");
            return handlerInitDataTables(url, columns,addUrl,deleteUrl);
        },

        /**
         * 初始化正向代码生成器Datatables（顶部控制按钮：新增）
         * @param url
         * @param columns
         * @returns {jQuery|*}
         */
        sysGenInitDataTables : function(url,columns){
            return sysGenHandlerInitDataTables(url,columns);
        },

        sysGenInit : function () {
            if ($("#top-check-box").length<=0){
                sysGenInitTopCheck();
            }
            handlerInitCheckbox();
        },

        /**
         * 初始化反向代码生成器Datatables（无顶部控制按钮）
         * @param url
         * @param columns
         * @returns {jQuery|*}
         */
        sysGenReInitDataTables : function (url,columns) {
            return sysGenReHandlerInitDataTables(url,columns);
        },

        sysGenReInitDataTables1 : function (url,columns) {
            return sysGenReHandlerInitDataTables1(url,columns);
        }

    }
}();
