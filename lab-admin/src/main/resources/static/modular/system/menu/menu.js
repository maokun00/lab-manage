/**
 * 角色管理的单例
 */
var Menu = {
    id: "menuTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '授权', field: 'perms', align: 'center', valign: 'middle', sortable: true},
        {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'orderNum', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Menu.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Menu.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Menu.openAddMenu = function () {

    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys/menu/menu_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Menu.openChangeMenu = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys/menu/menu_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Menu.delMenu = function () {
    if (this.check()) {
        var id = Menu.seItem.id;
        layer.confirm('确定要删除该菜单？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                type : "POST",
                url : "/sys/menu/remove/" + id,
                async : false,
                success : function(data) {
                    if(data.status == 10000){
                        layer.msg('操作成功');
                        Menu.table.refresh();
                    }else{
                        layer.alert(data.message);
                    }
                },
                error : function(error) {
                    layer.msg('系统错误！', {
                        icon : 2,
                        time : 1500
                    })
                }
            })
        }, function(){});
    }
};

/**
 * 搜索
 */
Menu.search = function () {
    var queryData = {};

    queryData['menuName'] = $("#menuName").val();
    queryData['level'] = $("#level").val();

    Menu.table.refresh({query: queryData});
}



$(function () {
    var defaultColunms = Menu.initColumn();
    var table = new BSTable("menuTable", "/sys/menu/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Menu.table = table;
});
