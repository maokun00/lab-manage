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
        {title: 'id', field: 'pictureId', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'pictureName', align: 'center', valign: 'middle', sortable: true},
        {title: '分类', field: 'categoryStr', align: 'center', valign: 'middle', sortable: true},
        {title: '图片', field: 'path',
            formatter: function(value,row,index){
                var img = '<img style="height: 50px;width: 50px" src='+ value + ' class="img-rounded" >';
                return img;
            },
            align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusStr', align: 'center', valign: 'middle', sortable: true},
        {title: '访问量', field: 'visitCount', align: 'center', valign: 'middle', sortable: true},
        {title: '上传时间', field: 'uploadTimeStr', align: 'center', valign: 'middle', sortable: true}];
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
        title: '上传图片',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/upload/file'
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
            title: '编辑图片内容',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/upload/picture_edit/' + this.seItem.pictureId
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
        var pictureId = this.seItem.pictureId;
        var ajax = new $ax(Feng.ctxPath + "/upload/disable/" + pictureId, function (data) {
            if(data.status == 10000){
                Feng.success("禁用成功!");
                MgrUser.table.refresh();
            }else{
                Feng.error("禁用失败!" + data.message + "!");
            }
        });
        ajax.set("userId", pictureId);
        ajax.start();
    }
};

/**
 * 启用
 * @param userId
 */
MgrUser.unfreeze = function () {
    if (this.check()) {
        var pictureId = this.seItem.pictureId;
        var ajax = new $ax(Feng.ctxPath + "/upload/enable/" + pictureId, function (data) {
            if(data.status == 10000){
                Feng.success("启用成功!");
                MgrUser.table.refresh();
            }else{
                Feng.error("启用失败!" + data.message + "!");
            }
        });
        ajax.set("userId", pictureId);
        ajax.start();
    }
}

MgrUser.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    MgrUser.search();
}

MgrUser.search = function () {
    var queryData = {};

    queryData['deptid'] = MgrUser.deptid;
    queryData['name'] = $("#name").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();

    MgrUser.table.refresh({query: queryData});
}

MgrUser.onClickDept = function (e, treeId, treeNode) {
    MgrUser.deptid = treeNode.id;
    MgrUser.search();
};

$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable("managerTable", "/upload/listData", defaultColunms);
    table.setPaginationType("server");
    MgrUser.table = table.init();
});
