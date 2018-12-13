/**
 * 菜单详情对话框
 */
var MenuInfoDlg = {
    menuInfoData: {},
};

/**
 * 清除数据
 */
MenuInfoDlg.clearData = function () {
    this.menuInfoData = {};
}


/**
 * 关闭此对话框
 */
MenuInfoDlg.close = function () {
    parent.layer.close(window.parent.Menu.layerIndex);
}


/**
 * 提交添加用户
 */
MenuInfoDlg.addSubmit = function () {

    var zTree = $.fn.zTree.getZTreeObj("parent");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        layer.alert('请选择父级菜单')
        return;
    }
    var parentId = nodes[0].id;
    var name = $("#menuName").val();
    var type = $("#ismenu").val();
    var perms = $("#perms").val();
    var url = $("#url").val();
    var orderNum = $("#orderNum").val();

    var form = {
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
        url : "/sys/menu/add",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                layer.alert('操作成功', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0
                }, function(){
                    window.parent.Menu.table.refresh();
                    MenuInfoDlg.close();
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
MenuInfoDlg.editSubmit = function () {
    var parentId;
    var zTree = $.fn.zTree.getZTreeObj("parent");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        parentId = $("#parentId").val();
    }else {
        parentId = nodes[0].id;
    }
    var name = $("#menuName").val();
    var type = $("#ismenu").val();
    var perms = $("#perms").val();
    var url = $("#url").val();
    var orderNum = $("#orderNum").val();
    var parent = $("#parentId").val();

    var form = {
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
                    MenuInfoDlg.close();
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

$().ready(function() {
    var divTree = $("#treeDiv");
    $("#partInput").click(function() {
        // var x = $(this).offset().left;
        // var y = $(this).offset().top;
        // divTree.css({
        //     left : x + "px",
        //     top : y + "px"
        // });
        divTree.slideDown("slow");// 滑动方式显示元素
    });

    divTree.hover(function() {

    }, function() {
        divTree.slideUp("slow");// 滑动方式隐藏元素
    });
    GetTree();
});

function GetTree() {
    $.ajax({
        type : "POST",
        dataType : "json",
        url : "/sys/menu/tree",
        async : false,
        success : function(data) {
            if(data.status == 10000){
                zTree = $.fn.zTree.init($("#parent"), setting, data.data);
                //展开所有节点
                zTree.expandAll(zTree);
            }
        },
        error : function(error) {
            layer.msg('系统错误！', {
                icon : 2,
                time : 1500
            })
        }
    });
}

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("parent"),
        nodes = zTree.getSelectedNodes(),
        v = "";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#partInput");
    cityObj.attr("value", v);
}

$(function () {

});
