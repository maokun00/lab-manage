/**
 * 角色管理的单例
 */
var Dictionary = {
    id: "DictionaryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
Dictionary.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'label', align: 'center', valign: 'middle', sortable: true},
        {title: '分类', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '关键字', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '编码值', field: 'value', align: 'center', valign: 'middle', sortable: true},
        {title: '创建人', field: 'createBy', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Dictionary.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Dictionary.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Dictionary.openAddDictionary = function () {

    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys/dictionary/dictionary_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Dictionary.openChangeDictionary = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys/dictionary/dictionary_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 搜索
 */
Dictionary.search = function () {
    var queryData = {};

    queryData['DictionaryName'] = $("#DictionaryName").val();
    queryData['level'] = $("#level").val();

    Dictionary.table.refresh({query: queryData});
}



$(function () {
    var defaultColunms = Dictionary.initColumn();
    var table = new BSTable("DictionaryTable", "/sys/dictionary/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Dictionary.table = table;
});
