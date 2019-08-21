<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>FastJee-Cloud | ${titil}编辑页</title>
    <div th:replace="common/form_head :: form_head"></div>
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
                ${titil}编辑页
                <small>Sev</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/index"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="/${camelCase}/list">${titil}管理</a></li>
                <li class="active">${titil}编辑</li>
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
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${titil}表单</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form class="form-horizontal" id="formData">
                            <div class="box-body">
                                <input type="hidden" name="keyId" th:value="${r"${"}rowData.getKeyId()${r"}"}"/>
                                <#list columnsList as column>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">${column.columnComment}</label>
                                    <div class="col-sm-7">
                                        <#if column.formShow == 'select'>
                                            <select class="form-control select2" name="${column.coNaCamelCase}" style="width: 100%;">
                                                <option>select占位</option>
                                                <option>select占位</option>
                                            </select>
                                        </#if>
                                        <#if column.formShow == 'datetime-local'>
                                            <input type="text" name="${column.coNaCamelCase}" th:value="${r"${"}#dates.format(rowData.${column.coNaCamelCase},'yyyy-MM-dd HH:mm:ss')${r"}"}" class="form-control DT" readonly>
                                        </#if>
                                        <#if column.formShow != 'select' && column.formShow != 'datetime-local'>
                                        <input type="${column.formShow}" name="${column.coNaCamelCase}" th:value="${r"${"}rowData.${column.coNaCamelCase}${r"}"}" class="form-control <#if column.isNullable?? && column.isNullable != 'on'>required</#if> <#if column.formShow == 'checkbox'> flat-red</#if> <#if column.dataType == 'int' || column.dataType == 'tinyint'>digits</#if> <#if column.dataType == 'float' || column.dataType == 'double'>number</#if>" <#if column.formShow == 'text'>placeholder="${column.columnComment}"</#if> <#if column.formShow == 'checkbox'>checked</#if>/>
                                        </#if>
                                    </div>
                                </div>
                                </#list>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <a href="/${camelCase}/list" type="button" class="btn btn-default">返回</a>
                                <button type="reset" class="btn btn-default pull-right">重置</button>
                                <button type="button" class="btn btn-info pull-right" id="submitForm">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!--/.col (right) -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>


    <!-- Main Footer -->
    <div th:replace="common/common_footer :: common_footer"></div>
    <!-- Control Sidebar -->
    <div th:replace="common/common_controlSidebar :: common_controlSidebar"></div>

</div>
<!-- ./wrapper -->

<!--java script-->
<div th:replace="common/form_js :: form_js"></div>


<script>

    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()

        $(".DT").daterangepicker({
            "singleDatePicker": true,
            "timePicker": true,
            "timePicker24Hour": true,
            locale: {
                format : 'YYYY-MM-DD HH:mm:ss'
            }
        });
        $("#submitForm").click(function () {


            //初始化表单验证
            Validate.init("formData");
            //执行验证
            if (! $("#formData").valid()) {
                return;
            }
            //获取表单数据
            var formData = new FormData($("#formData")[0]);

            var objData = {};
            formData.forEach((value, key) => objData[key] = value);
            //发送请求
            FormAjax.formAjax("/${camelCase}/save","Post",objData);
        })
    })
</script>

</body>
</html>
