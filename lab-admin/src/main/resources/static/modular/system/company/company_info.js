/**
 * 菜单详情对话框
 */
var CompanyInfoDlg = {
    menuInfoData: {},
};

/**
 * 清除数据
 */
CompanyInfoDlg.clearData = function () {
    this.menuInfoData = {};
}


/**
 * 关闭此对话框
 */
CompanyInfoDlg.close = function () {
    parent.layer.close(window.parent.Company.layerIndex);
}


/**
 * 提交添加用户
 */
CompanyInfoDlg.addSubmit = function () {
    var form = $("#roleForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/sys/company/add",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Company.table.refresh();
                    CompanyInfoDlg.close();
                });
            }else{
                layer.msg(data.message);
            }
        },
        error : function(error) {
            layer.msg('系统错误！', {
                icon : 2,
                time : 1500
            })
        }
    })
}

/**
 * 提交修改
 */
CompanyInfoDlg.editSubmit = function () {
    var form = $("#roleForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/sys/company/edit",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Company.table.refresh();
                    CompanyInfoDlg.close();
                });
            }else{
                layer.alert(data.message)
            }
        },
        error : function(error) {
            layer.msg('系统错误！', {
                icon : 2,
                time : 1500
            })
        }
    })
}


var setting = {
    view: {
        selectedMulti: false //是否允许多选
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback:{
        onClick: onClick
    }
};

$(function () {

});
