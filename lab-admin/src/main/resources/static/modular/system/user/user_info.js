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
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    // this.set('id').set('account').set('sex').set('password').set('avatar')
    //     .set('email').set('name').set('birthday').set('rePassword').set('deptid').set('phone');
    this.set("nickname").set("id").set("password").set("confirmPassword").set("username")
};

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
 * 验证两个密码是否一致
 */
UserInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("confirmPassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证数据是否为空
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
UserInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    if (!this.validatePwd()) {
        Feng.error("两次密码输入不一致");
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys/user/submit", function (data) {
        if(data.status == 10000){
            Feng.success("添加成功!");
            window.parent.MgrUser.table.refresh();
            UserInfoDlg.close();
        }else{
            layer.msg(data.message)
        }
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys/user/submit", function (data) {
        if(data.status == 10000){
            Feng.success("修改成功");
            window.parent.MgrUser.table.refresh();
            UserInfoDlg.close();
        }else{
            layer.msg(data.message);
        }

    });
    ajax.set(this.userInfoData);
    ajax.start();
};


$(function () {
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);
    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});
