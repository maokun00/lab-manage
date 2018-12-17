/**
 * 菜单详情对话框
 */
var RoleInfoDlg = {
    menuInfoData: {},
};

/**
 * 清除数据
 */
RoleInfoDlg.clearData = function () {
    this.menuInfoData = {};
}


/**
 * 关闭此对话框
 */
RoleInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
}


/**
 * 提交添加用户
 */
RoleInfoDlg.addSubmit = function () {
    var form = $("#roleForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/sys/role/add",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Role.table.refresh();
                    RoleInfoDlg.close();
                });
            }else{
                layer.msg(data.message)
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
RoleInfoDlg.editSubmit = function () {
    var form = $("#roleForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/sys/role/edit",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Role.table.refresh();
                    RoleInfoDlg.close();
                });
            }else{
                layer.msg(data.message)
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


$(function () {

});
