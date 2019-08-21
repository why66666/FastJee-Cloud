<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>FastJee-Cloud | ${titil}列表</title>
    <div th:replace="common/table_head :: table_head"></div>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!--Header-->
    <div th:replace="common/common_mainHeader :: common_mainHeader"></div>
    <!--Sidebar-->
    <div th:replace="common/common_mainSidebar :: common_mainSidebar"></div>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Data Tables
                <small>advanced tables</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Tables</a></li>
                <li class="active">Data tables</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div id="alert" class="alert alert-success alert-dismissible col-md-12 col-sm-12 col-xs-12" style="display: none">
                <button type="button" class="close" onclick="$('#alert').hide('fast')">×</button>
                <h4 id="msg"></h4>
                <span id="msgContent"></span>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-default" id="search-box" style="display:none">
                        <div class="box-header with-border">
                            <h3 class="box-title">高级搜索</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                <button type="button" class="btn btn-box-tool" onclick="$('#search-box').hide('fast')"><i class="fa fa-remove"></i></button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <form id="formData">
                            <div class="box-body">
                                <div class="row">
                            <#list columnsList as column>
                                <#if column.searchPlus?? && column.searchPlus == 'on'>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>${column.columnComment}</label>
                                            <input type="${column.formShow}" class="form-control <#if column.dataType == 'int' || column.dataType == 'tinyint'>digits</#if> <#if column.dataType == 'float' || column.dataType == 'double'>number</#if>" placeholder="${column.columnComment}" name="${column.coNaCamelCase}">
                                        </div>
                                    </div>
                                </#if>
                            </#list>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>创建时间</label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="bwCreateDate" readonly placeholder="点击这里编辑">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>更新时间</label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="bwUpdateDate" readonly placeholder="点击这里编辑">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->
                            </div>
                        </form>
                        <div class="box-footer">
                            <button type="button" class="btn btn-primary pull-right" onclick="M.searchPlus()"><i class="fa fa-fw fa-search"></i>搜索</button>
                        </div>
                    </div>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">${titil}列表</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="dataTable" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th><input id="checkAll" type="checkbox" class="minimal"></th>
                            <#list columnsList as column>
                                <#if column.listShow?? && column.listShow != ''>
                                    <th>${column.columnComment}</th>
                                </#if>
                            </#list>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->


    <!-- Main Footer -->
    <div th:replace="common/common_footer :: common_footer"></div>
    <!-- Control Sidebar -->
    <div th:replace="common/common_controlSidebar :: common_controlSidebar"></div>
    <!-- /.control-sidebar -->

</div>
<!-- ./wrapper -->


<!-- 查看 modal -->
<div id="infoModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header" id="infoModalHeader">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看</h4>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- ./查看 modal -->
<!-- 删除提示 modal -->
<div class="modal modal-danger fade" id="delModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <p>确认删除吗？&hellip;</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-outline" id="doDel">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- ./删除提示 modal -->
<!--java script-->
<div th:replace="common/table_js :: table_js"></div>

<!-- page script -->
<script>
    console.log($("[data-layout='fixed']").html());
    var _dataTable;

    var _idArray;

    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()

        //页面初始化
        App.init("/${camelCase}/form/0","/${camelCase}/delete");

        //Date range picker
        $('.reservation').daterangepicker({
            autoUpdateInput : false, //可以为空
            locale: {
                cancelLabel: 'Clear',
                format : 'YYYY-MM-DD'
            }
        });
        $('.reservation').on('apply.daterangepicker', function(ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
        });

        $('.reservation').on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });
        //./Date range picker
        var _columns = [
            {
                "data": function (row, type, val, meta) {
                    return '<input type="checkbox" id="'+row.keyId+'" class="minimal" />';
                }
            },
<#list columnsList as column>
    <#if column.listShow?? && column.listShow != ''>
        <#if column.listShow == 'datetime'>
            {
                "data": function (row, type, val, meta) {
                    return DateTime.format(row.${column.coNaCamelCase}, "yyyy-MM-dd HH:mm:ss");
                }
            },
        </#if>
        <#if column.listShow != 'datetime'>
            {"data": "${column.coNaCamelCase}"},
        </#if>
    </#if>
</#list>
            {
                "data": function (row, type, val, meta) {
                    return DateTime.format(row.updateDate, "yyyy-MM-dd HH:mm:ss");
                }
            },
            {
                "data": function (row, type, val, meta) {
                    var deleteUrl = "/${camelCase}/delete";
                    return '<button onclick="M.initModal('+meta.row+')" class="btn btn-sm btn-default"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;' +
                        '<a href="/${camelCase}/form/'+row.keyId+'" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;' +
                        '<button onclick="M.deleteSingle(\'' + deleteUrl + '\', \'' + row.keyId+'\')" class="btn btn-sm btn-danger"><i class="fa fa-trash-o"></i> 删除</button>'
                }
            }
        ];
        _dataTable = App.initDataTables("/${camelCase}/pageList",_columns,"/${camelCase}/form/0","/${camelCase}/delete");
    });

    var M = function () {
        var del = function (url) {
            var data = {"ids" : _idArray.toString()};
            FormAjax.formAjax(url,"Post",data);
            $("#delModal").modal('hide');
        };
        var showModal = function (data) {
            $("#infoModalContent").remove();
            $("#infoModalHeader").after("<div class=\"box\" id=\"infoModalContent\">\n" +
                "                <div class=\"box-header\">\n" +
                "                    <h3 class=\"box-title\">"+data.account+"详情</h3>\n" +
                "                </div>\n" +
                "                <!-- /.box-header -->\n" +
                "                <div class=\"box-body no-padding\">\n" +
                "                    <table class=\"table\">\n" +
                "                        <tbody>\n" +
            <#list columnsList as column>
                "                        <tr>\n" +
                "                            <td>${column.columnComment}</td>\n" +
                "                            <td>\n" +
                "\n" +data.${column.coNaCamelCase}+
                "                            </td>\n" +
                "                        </tr>\n" +
            </#list>
                "                        <tr>\n" +
                "                            <td>创建时间</td>\n" +
                "                            <td>\n" +
                "\n" +data.createDate+
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td>创建者</td>\n" +
                "                            <td>\n" +
                "\n" +data.createBy+
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td>更新时间</td>\n" +
                "                            <td>\n" +
                "\n" +data.updateDate+
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td>更新者</td>\n" +
                "                            <td>\n" +
                "\n" +data.updateBy+
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody></table>\n" +
                "                </div>\n" +
                "                <!-- /.box-body -->\n" +
                "            </div>");
            $("#infoModal").modal('show');
        };
        return{
            /**
             * 点击查看获得该行数据
             * @param row
             */
            initModal : function (row) {
                var data= $('#dataTable').DataTable().rows(row).data()[0];
                return showModal(data);
            },
            deleteSingle : function(url,keyId){
                _idArray = new Array();
                _idArray.push(keyId);
                $("#delModal").modal('show');
                // 绑定删除事件
                $("#doDel").bind("click", function () {
                    del(url);
                });
            },
            toDel : function(url){
                return del(url);
            },
            searchPlus : function () {
                //初始化表单验证
                Validate.init("formData");
                //执行验证
                if (! $("#formData").valid()) {
                    return;
                }
                //获取表单数据
                var formData = new FormData($("#formData")[0]);
                for(var i of formData.entries()){
                    console.log(i[0]+ ', '+ i[1]);//i[0],i[1],下标为零是键，为1是值
                }
                /*
                 *formdata封装属性为json
                 */
                var objData = {};
                formData.forEach((value, key) => objData[key] = value);
                console.log(objData);
                //发送请求
                _dataTable.settings()[0].ajax.data = objData;
                _dataTable.ajax.reload();
            }
        }
    }();

    function deleteMulti(url) {
        _idArray = new Array();
        // 将选中元素的 ID 放入数组中
        $('input[type="checkbox"].minimal').each(function () {
            var _id =$(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });
        // 判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#alert").attr("class","alert alert-warning alert-dismissible col-md-12 col-sm-12 col-xs-12");
            $("#msg").html("<i class=\"icon fa fa-warning\"></i>您还没有选择任何数据项，请至少选择一项！");
        }
        else {
            $("#delModal").modal('show');
            // 绑定删除事件
            $("#doDel").bind("click", function () {
                M.toDel(url);
            });
        }
    }

</script>
</body>
</html>
