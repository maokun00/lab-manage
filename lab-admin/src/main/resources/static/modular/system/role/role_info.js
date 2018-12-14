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
                    window.parent.Menu.table.refresh();
                    RoleInfoDlg.close();
                });
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
    var parentId;
    var zTree = $.fn.zTree.getZTreeObj("parent");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        parentId = $("#parentId").val();
    }else {
        parentId = nodes[0].id;
    }
    var id = $("#id").val();
    var name = $("#menuName").val();
    var type = $("#ismenu").val();
    var perms = $("#perms").val();
    var url = $("#url").val();
    var orderNum = $("#orderNum").val();
    var parent = $("#parentId").val();

    var form = {
        id : id,
        parentId : parentId,
        name : name,
        type : type,
        orderNum : orderNum,
        perms :perms,
        url : url
    }

    $.ajax({
        type : "POST",
        data : form,
        url : "/sys/menu/edit",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Menu.table.refresh();
                    RoleInfoDlg.close();
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
