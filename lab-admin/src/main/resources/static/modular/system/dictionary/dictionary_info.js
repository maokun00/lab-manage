/**
 * 菜单详情对话框
 */
var DictionaryInfoDlg = {
    menuInfoData: {},
};

/**
 * 清除数据
 */
DictionaryInfoDlg.clearData = function () {
    this.menuInfoData = {};
}


/**
 * 关闭此对话框
 */
DictionaryInfoDlg.close = function () {
    parent.layer.close(window.parent.Dictionary.layerIndex);
}


/**
 * 提交添加用户
 */
DictionaryInfoDlg.addSubmit = function () {
    var form = $("#dictionaryForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/sys/dictionary/add",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Dictionary.table.refresh();
                    DictionaryInfoDlg.close();
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
DictionaryInfoDlg.editSubmit = function () {
    var form = $("#dictionaryForm");
    $.ajax({
        type : "POST",
        data : form.serialize(),
        url : "/sys/dictionary/edit",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Dictionary.table.refresh();
                    DictionaryInfoDlg.close();
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
