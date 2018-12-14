/**
 * 角色管理的单例
 */
var Role = {
    id: "RoleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
Role.initColumn = function () {
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
Role.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Role.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Role.openAddRole = function () {

    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys/Role/Role_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Role.openChangeRole = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys/Role/Role_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Role.delRole = function () {
    if (this.check()) {
        var id = Role.seItem.id;
        layer.confirm('确定要删除该菜单？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                type : "POST",
                url : "/sys/Role/remove/" + id,
                async : false,
                success : function(data) {
                    if(data.status == 10000){
                        layer.msg('操作成功');
                        Role.table.refresh();
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
Role.search = function () {
    var queryData = {};

    queryData['RoleName'] = $("#RoleName").val();
    queryData['level'] = $("#level").val();

    Role.table.refresh({query: queryData});
}



$(function () {
    var defaultColunms = Role.initColumn();
    var table = new BSTable("RoleTable", "/sys/role/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Role.table = table;
});
