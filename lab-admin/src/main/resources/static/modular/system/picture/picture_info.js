/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
    userInfoData: {},
};


/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, value) {
    if(typeof value == "undefined"){
        if(typeof $("#" + key).val() =="undefined"){
            var str="";
            var ids="";
            $("input[name='"+key+"']:checkbox").each(function(){
                if(true == $(this).is(':checked')){
                    str+=$(this).val()+",";
                }
            });
            if(str){
                if(str.substr(str.length-1)== ','){
                    ids = str.substr(0,str.length-1);
                }
            }else{
                $("input[name='"+key+"']:radio").each(function(){
                    if(true == $(this).is(':checked')){
                        ids=$(this).val()
                    }
                });
            }
            this.userInfoData[key] = ids;
        }else{
            this.userInfoData[key]= $("#" + key).val();
        }
    }

    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrUser.layerIndex);
};

/**
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    this.set("pictureId")
};

/**
 * 验证数据是否为空
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

UserInfoDlg.upload = function () {
    var formData = new FormData()
    formData.append("file", $("#uploadFile")[0].files[0]);
    formData.append('sizeid',123);
    $.ajax({
        url : "/upload/file/upload",
        data : formData,
        type : "POST",
        async : false,
        processData : false,
        contentType : false,
        success : function (data) {
            if(data.status == 10000){
                $("#fileDetail").show();
                $("#filePath").attr("src",data.data[0]);
                $("#path").val(data.data[1])
            }else{
                Feng.error("上传失败!" + data.message + "!");
            }
        }
    })
}

/**
 * 提交添加用户
 */
UserInfoDlg.submit = function (pictureId,auditing) {

    this.clearData();
    //this.collectData();
    this.userInfoData = {pictureId:pictureId,auditing:auditing};

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pictureAuditing/updateAuditing", function (data) {
        if(data.status == 10000){
            Feng.success("更改成功!");
            window.parent.MgrUser.table.refresh();
            UserInfoDlg.close();
        }else{
            Feng.error("更改失败!" + data.message + "!");
        }
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        UserInfoDlg.hideDeptSelectTree();
    }
}

$(function () {
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);

    //初始化性别选项
    $("#sex").val($("#sexValue").val());

    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});
