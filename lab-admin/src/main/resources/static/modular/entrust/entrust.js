/**
 * 角色管理的单例
 */
var Entrust = {
    id: "EntrustTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
Entrust.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '委托单位', field: 'companyName', align: 'center', valign: 'middle', sortable: true},
        {title: '项目名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '签订日期', field: 'signTimeStr', align: 'center', valign: 'middle', sortable: true},
        {title: '服务类型', field: 'serviceTypeStr', align: 'center', valign: 'middle', sortable: true},
        {title: '检测类型', field: 'detectionTypeStr', align: 'center', valign: 'middle', sortable: true},
        {title: '采样类型', field: 'sampleTypeStr', align: 'center', valign: 'middle', sortable: true},
        {title: '应出报告日期', field: 'entTimeStr', align: 'center', valign: 'middle', sortable: true},
        {title: '下单时间', field: 'createTimeStr', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Entrust.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Entrust.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Entrust.openAddEntrust = function () {

    var index = layer.open({
        type: 2,
        title: '新增委托',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/entrust/entrust_add'
    });
    this.layerIndex = index;
};

Entrust.openSample = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '检测项目',
            area: ['1200px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/entrust/entrust_sample/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击修改
 */
Entrust.openChangeEntrust = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改委托',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/entrust/entrust_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 搜索
 */
Entrust.search = function () {
    var queryData = {};

    queryData['EntrustName'] = $("#EntrustName").val();
    queryData['level'] = $("#level").val();

    Entrust.table.refresh({query: queryData});
}



$(function () {
    var defaultColunms = Entrust.initColumn();
    var table = new BSTable("EntrustTable", "/entrust/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Entrust.table = table;
});
