/**
 * 系统管理--用户管理的单例对象
 */
var MgrUser = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
MgrUser.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '统计日', field: 'time', visible: true, align: 'center', valign: 'middle'},
        {title: '当日活跃人数统计', field: 'count', visible: true,align: 'center', valign: 'middle'},
        {title: '当日用户活跃度统计', field: 'activityCount',visible: true, align: 'center', valign: 'middle'},
        {title: '当日签到人数统计', field: 'signCount', visible: true,align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true,align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusStr', visible: true,align: 'center', valign: 'middle'}];
    return columns;
};

/**
 * 检查是否选中
 */
MgrUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加管理员
 */
MgrUser.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加管理员',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/user/user_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
MgrUser.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑管理员',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/user/user_edit/' + this.seItem.userId
        });
        this.layerIndex = index;
    }
};

/**
 * 点击角色分配
 * @param
 */
MgrUser.roleAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '角色分配',
            area: ['300px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mgr/role_assign/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
MgrUser.delMgrUser = function () {
    if (this.check()) {
        var operation = function(){
            var userId = MgrUser.seItem.userId;
            var ajax = new $ax(Feng.ctxPath + "/user/delete/" + userId, function (data) {
                if(data.status == 10000){
                    Feng.success("删除成功!");
                    MgrUser.table.refresh();
                }else{
                    Feng.error("删除失败!" + data.message + "!");
                }
            });
            ajax.set("userId", userId);
            ajax.start();
        };

        Feng.confirm("是否删除用户" + MgrUser.seItem.username + "?",operation);
    }
};

/**
 * 禁用
 * @param userId
 */
MgrUser.freezeAccount = function () {
    if (this.check()) {
        var userId = this.seItem.userId;
        var ajax = new $ax(Feng.ctxPath + "/user/disable/" + userId, function (data) {
            if(data.status == 10000){
                Feng.success("禁用成功!");
                MgrUser.table.refresh();
            }else{
                Feng.error("禁用失败!" + data.message + "!");
            }
        });
        ajax.set("userId", userId);
        ajax.start();
    }
};

/**
 * 启用
 * @param userId
 */
MgrUser.unfreeze = function () {
    if (this.check()) {
        var userId = this.seItem.userId;
        var ajax = new $ax(Feng.ctxPath + "/user/enable/" + userId, function (data) {
            if(data.status == 10000){
                Feng.success("启用成功!");
                MgrUser.table.refresh();
            }else{
                Feng.error("启用失败!" + data.message + "!");
            }
        });
        ajax.set("userId", userId);
        ajax.start();
    }
}

/**
 * 重置密码
 */
MgrUser.resetPwd = function () {
    if (this.check()) {
        var userId = this.seItem.userId;
        parent.layer.confirm('是否重置密码为{ 12345678 } ？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            var ajax = new $ax(Feng.ctxPath + "/user/reset/" + userId, function (data) {
                if(data.status == 10000){
                    Feng.success("成功!");
                    MgrUser.table.refresh();
                }else{
                    Feng.error("失败!" + data.message + "!");
                }
            });
            ajax.set("userId", userId);
            ajax.start();
        });
    }
};

MgrUser.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    MgrUser.search();
}

MgrUser.search = function () {
    var queryData = {};
    // queryData['username'] = $("#username").val();
    queryData['startTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    MgrUser.table.refresh({query: queryData});
}

MgrUser.onClickDept = function (e, treeId, treeNode) {
    MgrUser.deptid = treeNode.id;
    MgrUser.search();
};

$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable("managerTable", "/userActivityCount/listData", defaultColunms);
    table.setPaginationType("server");
    MgrUser.table = table.init();
});
