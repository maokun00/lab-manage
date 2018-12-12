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
        {title: '用户id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '昵称', field: 'nickname', align: 'center', valign: 'middle', sortable: true},
        {title: '用户名', field: 'username', align: 'center', valign: 'middle', sortable: true},
        {title: '会员等级', field: 'level', align: 'center', valign: 'middle', sortable: true},
        {title: '活跃度', field: 'activityCount', align: 'center', valign: 'middle', sortable: true},
        {title: '访问量', field: 'accessCount', align: 'center', valign: 'middle', sortable: true},
        {title: '注册时间', field: 'registerStr', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusStr', align: 'center', valign: 'middle', sortable: true}];
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
            content: Feng.ctxPath + '/member/editLevel/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 禁用
 * @param userId
 */
MgrUser.freezeAccount = function () {
    if (this.check()) {
        var id = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/member/disable/" + id, function (data) {
            if(data.status == 10000){
                Feng.success("禁用成功!");
                MgrUser.table.refresh();
            }else{
                Feng.error("禁用失败!" + data.message + "!");
            }
        });
        ajax.set("userId", id);
        ajax.start();
    }
};

/**
 * 启用
 * @param userId
 */
MgrUser.unfreeze = function () {
    if (this.check()) {
        var id = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/member/enable/" + id, function (data) {
            if(data.status == 10000){
                Feng.success("启用成功!");
                MgrUser.table.refresh();
            }else{
                Feng.error("启用失败!" + data.message + "!");
            }
        });
        ajax.set("userId", id);
        ajax.start();
    }
}

/**
 * 重置密码
 */
MgrUser.resetPwd = function () {
    if (this.check()) {
        var id = this.seItem.id;
        parent.layer.confirm('是否重置密码为{ 12345678 } ？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            var ajax = new $ax(Feng.ctxPath + "/member/reset/" + id, function (data) {
                if(data.status == 10000){
                    Feng.success("成功!");
                    MgrUser.table.refresh();
                }else{
                    Feng.error("失败!" + data.message + "!");
                }
            });
            ajax.set("userId", id);
            ajax.start();
        });
    }
};

MgrUser.resetSearch = function () {
    $("#username").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    $("#Province option:first").prop("selected", 'selected');
    $("#City option:first").prop("selected", 'selected');
    $("#County option:first").prop("selected", 'selected');
    MgrUser.search();
}

$("#Province").change(function () {
    $("#City option:first").prop("selected", 'selected');
    $("#City").html("");
    $("#City").append('<option value="">==请选择市===</option>');
    var id = $("#Province").val();
    $.ajax({
        url:"/member/findCity",
        type:"POST",
        data:{provinceId:id},
        success:function (data) {
            if(data.status == 10000){
                data.data.forEach(function (val, index) {
                    $("#City").append('<option value="' + val.id + '">'+ val.name +'</option>');
                })
            }
        }
    })
})

$("#City").change(function () {
    $("#County option:first").prop("selected", 'selected');
    $("#County").html("");
    $("#County").append('<option value="">==请选择区/县===</option>');
    var id = $("#City").val();
    $.ajax({
        url:"/member/findCounty",
        type:"POST",
        data:{cityId:id},
        success:function (data) {
            if(data.status == 10000){
                data.data.forEach(function (val, index) {
                    $("#County").append('<option value="' + val.id + '">'+ val.name +'</option>');
                })
            }
        }
    })
})

MgrUser.search = function () {
    var queryData = {};
    queryData['username'] = $("#username").val();
    queryData['startTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    var ads = $("#Province").val();
    if(ads && ads != ''){
        var city = $("#City").val();
        if(city && city != ''){
            ads = city;
            var county = $("#County").val();
            if(county && county != ''){
                ads = county;
            }
        }
    }
    queryData['locationId'] = ads;
    MgrUser.table.refresh({query: queryData});
}

MgrUser.onClickDept = function (e, treeId, treeNode) {
    MgrUser.deptid = treeNode.id;
    MgrUser.search();
};

$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable("managerTable", "/memberCount/listData", defaultColunms);
    table.setPaginationType("server");
    MgrUser.table = table.init();
});