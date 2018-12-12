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
        // {field: 'selectItem', radio: true},
        {checkbox: true},
        {title: '图片id', field: 'pictureId', visible: false, align: 'center', valign: 'middle'},
        {title: '图片名称', field: 'pictureName', align: 'center', valign: 'middle'},
        {title: '图片', field: 'path',
            formatter: function(value,row,index){
                var img = '<img style="height: 50px;width: 60px" src='+ value + ' class="img-rounded" >';
                return img;
            },
            align: 'center', valign: 'middle', sortable: true},
        {title: '作者', field: 'userName', align: 'center', valign: 'middle', sortable: true},
        // {title: '存放路径', field: 'path', align: 'center', valign: 'middle', sortable: true},
        // {title: '图片格式', field: 'pictureFormat', align: 'center', valign: 'middle', sortable: true},
        {title: '上传时间', field: 'uploadTime', align: 'center', valign: 'middle', sortable: true},
        {title: '审核情况 ', field: 'auditingStr', align: 'center', valign: 'middle', sortable: true},
        {title: '访问量', field: 'visitCount', align: 'center', valign: 'middle', sortable: true},
        {title: '类别', field: 'categoryStr', align: 'center', valign: 'middle', sortable: true},
        {title: '是否推荐', field: 'recommendStr', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};

/**
 * 检查是否选中
 */
MgrUser.check = function () {
    var selected = $('#' + MgrUser.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrUser.seItem = selected[0];
        return true;
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
    queryData['username'] = $("#username").val();
    queryData['startTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['way'] = $("#way").val();
    MgrUser.table.refresh({query: queryData});
}

MgrUser.onClickDept = function (e, treeId, treeNode) {
    MgrUser.deptid = treeNode.id;
    MgrUser.search();
};
//加载数据
$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable("managerTable", "/pictureAuditing/listData", defaultColunms);
    table.setPaginationType("server");
    MgrUser.table = table.init();
});

//单个更改
MgrUser.openChangePicture = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '审核图片',
            area: ['400px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/pictureAuditing/pictureAuditing_edit/' + this.seItem.pictureId
        });
        this.layerIndex = index;
    }
};
//批量更改
MgrUser.changePicture = function (auditing) {
    if (this.check()) {
        var selected = $('#' + MgrUser.id).bootstrapTable('getSelections');
        var arr = [];
        for(var i = 0; i < selected.length; i++){//遍历json数组时，这么写p为索引，0,1
            arr.push(selected[i].pictureId);
        }
        var ids = arr.join(",");
        var url= Feng.ctxPath +"/pictureAuditing/batchUpdate";
        $.ajax({
            dataType: "json",
            traditional:true,//这使json格式的字符不会被转码
            data: {ids:ids,auditing:auditing},
            type: "post",
            url: url,
            success : function () {
                Feng.success("更改成功!");
                MgrUser.table.refresh();
            },
            error : function (){
                Feng.success("更改失败!");
            }
        });
    }
};
