/**
 * ajax请求
 * alert
 */
var FormAjax = function () {
    var rsAjax = function (url, method,formData) {
        $.ajax({
            type: method,                             //请求的类型
            url: url,                  //请求的路径
            data: formData,                    //请求的参数
            dataType: "json",
            success: function (result) {                 //成功返回触发的方法
                if (result.status === 500){
                    $("#alert").attr("class","alert alert-warning alert-dismissible col-md-12 col-sm-12 col-xs-12");
                    $("#msg").html("<i class=\"icon fa fa-warning\"></i>提交失败！");
                }
                if (result.status === 200) {
                    $("#alert").attr("class","alert alert-success alert-dismissible col-md-12 col-sm-12 col-xs-12");
                    $("#msg").html("<i class=\"icon fa fa-check\"></i>提交成功！");
                }
                $("#msgContent").html(result.message);
                if($("#dataTable").length > 0) {
                    $('#dataTable').DataTable().draw(false);
                }
                $("#alert").show('fast');
                /* 定时关闭alert */
                var flagHideparas = false;
                $("#alert").mouseover(function(){
                    flagHideparas = false;
                });
                //鼠标移出详情页
                $("#alert").mouseleave(function(){
                    hide();
                });
                //定时隐藏
                function hide() {
                    flagHideparas = true;
                    setTimeout(function(){
                        if(flagHideparas){
                            $('#alert').hide('fast');
                        };
                    },2000);
                }
                /* ./定时关闭alert */
            },
            //请求失败触发的方法
            error:function(XMLHttpRequest, textStatus, errorThrown){
                $("#alert").attr("class","alert alert-warning alert-dismissible col-md-12 col-sm-12 col-xs-12");
                $("#msg").html("<i class=\"icon fa fa-warning\"></i>请求失败！");
                $("#msgContent").html("请求对象XMLHttpRequest: "+XMLHttpRequest+"错误类型textStatus: "+textStatus+"异常对象errorThrown: "+errorThrown);
            }
        })
    };
    var rAjax = function (url, method, data,redirectUrl) {
        $.ajax({
            type: method,                             //请求的类型
            url: url,                  //请求的路径
            data: data,                    //请求的参数
            dataType: "json",
            success: function (result) {                 //成功返回触发的方法
                if (result.status === 500){
                    $("#alert").attr("class","alert alert-warning alert-dismissible col-md-12 col-sm-12 col-xs-12");
                    $("#msg").html("<i class=\"icon fa fa-warning\"></i>失败！");
                    $("#msgContent").html(result.message);
                    $("#alert").show('fast');
                    /* 定时关闭alert */
                    var flagHideparas = false;
                    $("#alert").mouseover(function(){
                        flagHideparas = false;
                    });
                    //鼠标移出详情页
                    $("#alert").mouseleave(function(){
                        hide();
                    });
                    //定时隐藏
                    function hide() {
                        flagHideparas = true;
                        setTimeout(function(){
                            if(flagHideparas){
                                $('#alert').hide('fast');
                            };
                        },2000);
                    }
                    /* ./定时关闭alert */
                }
                if (result.status === 200) {
                    window.location.href = redirectUrl;
                }
            },
            //请求失败触发的方法
            error:function(XMLHttpRequest, textStatus, errorThrown){
                $("#alert").attr("class","alert alert-warning alert-dismissible col-md-12 col-sm-12 col-xs-12");
                $("#msg").html("<i class=\"icon fa fa-warning\"></i>请求失败！");
                $("#msgContent").html("请求对象XMLHttpRequest: "+XMLHttpRequest+"错误类型textStatus: "+textStatus+"异常对象errorThrown: "+errorThrown);
            }
        })
    };
    return{
        formAjax : function (url, method,formData) {
            rsAjax(url, method,formData);
        },
        redirectAjax : function (url,method,data,redirectUrl) {
            rAjax(url,method,data,redirectUrl);
        }
    }
}();