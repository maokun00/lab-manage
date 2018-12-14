/**
 * 角色管理的单例
 */
var Company = {
    id: "companyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
Company.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '公司名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '公司负责人', field: 'person', align: 'center', valign: 'middle', sortable: true},
        {title: '负责人电话', field: 'personMobile', align: 'center', valign: 'middle', sortable: true},
        {title: '过期时间', field: 'endTimeStr', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Company.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Company.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Company.openAddCompany = function () {

    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys/company/company_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Company.openChangeCompany = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys/company/company_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 搜索
 */
Company.search = function () {
    var queryData = {};

    queryData['CompanyName'] = $("#CompanyName").val();
    queryData['level'] = $("#level").val();

    Company.table.refresh({query: queryData});
}


$(function () {
    var defaultColunms = Company.initColumn();
    var table = new BSTable("companyTable", "/sys/company/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Company.table = table;
});
