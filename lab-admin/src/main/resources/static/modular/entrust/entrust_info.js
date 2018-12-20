/**
 * 菜单详情对话框
 */
var EntrustInfoDlg = {
    menuInfoData: {},
};

/**
 * 清除数据
 */
EntrustInfoDlg.clearData = function () {
    this.menuInfoData = {};
}


/**
 * 关闭此对话框
 */
EntrustInfoDlg.close = function () {
    parent.layer.close(window.parent.Entrust.layerIndex);
}


/**
 * 提交添加用户
 */
EntrustInfoDlg.addSubmit = function () {
    var form = $("#entrustForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/entrust/add",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Entrust.table.refresh();
                    EntrustInfoDlg.close();
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
EntrustInfoDlg.editSubmit = function () {
    var form = $("#entrustForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/entrust/edit",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Entrust.table.refresh();
                    EntrustInfoDlg.close();
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
