<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">学生姓名：
                <Input v-model="name" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button></Col>
            </Row>
        </div>
        <div>
            <ul>
                <li>
                    <Button type="primary" icon="plus-round" @click="openNewModal()">新建</Button>
                    <Button type="success" icon="wrench" @click="openModifyModal()">修改</Button>
                    <Button type="error" icon="trash-a" @click="del()">删除</Button>
                </li>
                <li>
                    <div style="padding: 10px 0;">
                        <Table border :columns="columns1"  :data="data1":height="400" @on-selection-change="s=>{change(s)}" @on-row-dblclick="s=>{dblclick(s)}"></Table>
                    </div>
                </li>
                <li>
                    <div style="text-align: right;">
                        <Page :total="total" :page-size="pageInfo.pageSize" show-elevator show-total @on-change="e=>{pageSearch(e)}"></Page>
                    </div>
                </li>
            </ul>
        </div>
        <!--添加modal-->
        <Modal :mask-closable="false" :visible.sync="newModal" :loading = "loading" v-model="newModal" width="600" title="新建" @on-ok="newOk('signNew')" @on-cancel="cancel()">
            <Form ref="signNew" :model="signNew" :rules="ruleNew" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="学生学号:" prop="studentId">
                        <Input v-model="signNew.studentId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="学生姓名:" prop="name">
                        <Input v-model="signNew.name" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="课程名称:" prop="courseName">
                        <Input v-model="signNew.courseName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="签到次数:" prop="signNum">
                        <Input v-model="signNew.signNum"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading = "loading" v-model="modifyModal" width="600" title="修改" @on-ok="modifyOk('signModify')" @on-cancel="cancel()">
            <Form ref="signModify" :model="signModify" :rules="ruleModify" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="学生学号:" prop="studentId">
                        <Input v-model="signModify.studentId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="学生姓名:" prop="name">
                        <Input v-model="signModify.name" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="课程名称:" prop="courseName">
                        <Input v-model="signModify.courseName"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="签到次数:" prop="signNum">
                        <Input v-model="signModify.signNum" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>

            </Form>
        </Modal>
        <!--配置角色modal-->
        <!--<Modal v-model="roleModal" width="500" title="角色配置" @on-ok="roleOk()" @on-cancel="cancel()">-->
        <!--<div>-->
        <!--<Table border :columns="columns2" :data="data2" :height="260"  @on-selection-change="s=>{change2(s)}"></Table>-->
        <!--</div>-->
        <!--</Modal>-->
    </div>
</template>
<script>
    export default {
        data () {
            return {
                /*用于查找的登录名*/
                name:null,
                /*选择的数量*/
                count:null,
                /*选中的组数据*/
                groupId:null,
                /*新建modal的显示参数*/
                newModal:false,
                /*修改modal的显示参数*/
                modifyModal:false,
                /*角色配置modal的显示参数*/
                //roleModal:false,
                /*分页total属性绑定值*/
                total:0,
                /*loading*/
                loading: true,
                /*pageInfo实体*/
                pageInfo:{
                    page:0,
                    pageSize:10
                },
                /*user实体*/
                sign:{
                    id:null,
                    name:null,
                    studentId:null,
                    courseName:null,
                    signNum :null,

                },
                /*用于添加的user实体*/
                signNew:{
                    id:null,
                    name:null,
                    studentId:null,
                    courseName:null,
                    signNum :null,
                },
                /*用于修改的user实体*/
                signModify:{
                    id:null,
                    name:null,
                    studentId:null,
                    courseName:null,
                    signNum :null,
                },
                /*新建验证*/
                ruleNew:{
                    studentId: [
                        { type:'string',required: true, message: '输入学生学号', trigger: 'blur' }
                    ],
                    name: [
                        { type:'string',required: true, message: '输入学生姓名', trigger: 'blur' }
                    ],
                    courseName: [
                        { type:'string',required: true, message: '输入课程名称', trigger: 'blur' }
                    ],
                    signNum: [
                        { type:'string',required: true, message: '输入签到次数', trigger: 'blur' }
                    ],
                },
                /*修改验证*/
                ruleModify:{
                    studentId: [
                        { type:'string',required: true, message: '输入学生学号', trigger: 'blur' }
                    ],
                    name: [
                        { type:'string',required: true, message: '输入学生姓名', trigger: 'blur' }
                    ],
                    courseName: [
                        { type:'string',required: true, message: '输入课程名称', trigger: 'blur' }
                    ],
                    signNum: [
                        { type:'string',required: true, message: '输入签到次数', trigger: 'blur' }
                    ],
                },
                /*表显示字段*/
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '学生学号',
                        key: 'studentId'
                    },
                    {
                        title: '学生姓名',
                        key: 'name'
                    },
                    {
                        title: '课程名',
                        key: 'courseName'
                    },
                    {
                        title: '签到次数',
                        key: 'signNum'
                    },
                ],
                // 表数据
                data1: [],
//                /*表显示字段*/
//                columns2: [
//                    {
//                        type: 'selection',
//                        width: 60,
//                        align: 'center'
//                    },
//                    {
//                        title: '角色名称',
//                        width: 120,
//                        key: 'name'
//                    },
//                    {
//                        title: '描述',
//                        key: 'describe'
//                    }
//                ],
                /*表数据*/
                //data2:[],
                /*data2的临时存储*/
                //data2Temp:[],
                /*用户与角色关系列表*/
                relationList:null
            }
        },
        mounted(){
            /*页面初始化调用方法*/
            this.getTable({
                "pageInfo":this.pageInfo,
                "name":this.name
            });
            this.axios({
                method: 'get',
                url: '/signs/all'
            }).then(function (response) {
                //this.data2Temp = response.data;
                this.data1 = response.data;
                //console.log(this.data1[0]);
                //alert(this.data1[0].pmAfterclasstime);

            }.bind(this)).catch(function (error) {
                alert(error);
            });
        },
        methods:{
            /*pageInfo实体初始化*/
            initPageInfo(){
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
            },
            /*user实体初始化*/
            initSign(){
                this.sign.id = null;
                this.sign.name = null;
                this.sign.studentId = null;
                this.sign.courseName = null;
                this.sign.signNum = null;

            },
            /*userNew实体初始化*/
            initSignNew(){
                this.signNew.id = null;
                this.signNew.name = null;
                this.signNew.studentId = null;
                this.signNew.courseName = null;
                this.signNew.signNum = null;
            },
            /*userModify实体初始化*/
            initSignModify(){
                this.signModify.id = null;
                this.signModify.name = null;
                this.signModify.studentId = null;
                this.signModify.courseName = null;
                this.signModify.signNum = null;
            },
            /*userNew设置*/
            signSet(e){
                this.sign.id = e.id;
                this.sign.name = e.name;
                this.sign.studentId = e.studentId;
                this.sign.courseName = e.courseName;
                this.sign.signNum = e.signNum;

            },
            /*userNew设置*/
            signNewSet(e){
                this.signNew.id = e.id;
                this.signNew.name = e.name;
                this.signNew.studentId = e.studentId;
                this.signNew.courseName = e.courseName;
                this.signNew.signNum = e.signNum;
            },
            /*userModify设置*/
            signModifySet(e){
                this.signModify.id = e.id;
                this.signModify.name = e.name;
                this.signModify.studentId = e.studentId;
                this.signModify.courseName = e.courseName;
                this.signModify.signNum = e.signNum;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/signs',
                    params: {
                        'page':e.pageInfo.page,
                        'pageSize':e.pageInfo.pageSize,
                        'name':e.name
                    }
                }).then(function (response) {
                    this.data1=response.data.data;
                    this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
                //console.log(e.name);
            },
            /*搜索按钮点击事件*/
            search(){
                this.initPageInfo();
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "name":this.name
                });
                //console.log(this.name);
            },
            /*分页点击事件*/
            pageSearch(e){
                this.pageInfo.page = e-1;
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "name":this.name
                });
            },
            /*新建点击触发事件*/
            openNewModal(){
                this.newModal = true;
                this.initSignNew();
                this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk (signNew) {
                this.$refs[signNew].validate((valid) => {
                    if (valid) {
                        //if(this.signNew.password == this.signNew.passwordAgain){
                        this.initSign();
                        this.signSet(this.signNew);
                        //
                        // console.log(this.signNew);
                        this.axios({
                            method: 'post',
                            url: '/signs/insert',
                            data: this.sign

                        }).then(function (response) {

                            this.initSignNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "name":this.name
                            });
                            this.$Message.info('新建成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });
                        this.newModal = false;

                        // }
                    }else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                })
            },
            /*点击修改时判断是否只选择一个*/
            openModifyModal(){
                if(this.count > 1 || this.count < 1){
                    this.modifyModal= false;
                    this.$Message.warning('请至少选择一项(且只能选择一项)');
                }else{
                    this.modifyModal = true;
                }
            },
            /*修改modal的modifyOk点击事件*/
            modifyOk (signModify) {
                this.$refs[signModify].validate((valid) => {
                    if (valid) {
                        this.initSign();
                        this.signSet(this.signModify);
                        //console.log(this.sign.id);
                        this.axios({
                            method: 'put',
                            url:'/signs/'+this.sign.id,
                            data: this.sign
                        }).then(function (response) {
                            this.initSignNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "name":this.name
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });

                        this.modifyModal = false;
                        //console.log(this.sign);
                    }else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                })
            },
            /*modal的cancel点击事件*/
            cancel () {
                this.$Message.info('点击了取消');
            },
            /*table选择后触发事件*/
            change(e){
                if(e.length==1){
                    this.signModifySet(e['0']);
                }
                //console.log( this.signModify);
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e){
                this.groupId=[];
                this.count=e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].id);
                }
                //console.log(this.groupId);
            },
            /*删除table中选中的数据*/
            del(){
                if(this.groupId!=null && this.groupId!=""){
                    //console.log(this.groupId)
                    this.axios({
                        method: 'delete',
                        url: '/signs/delete',
                        data: this.groupId
                    }).then(function (response) {
                        this.getTable({
                            "pageInfo":this.pageInfo,
                            "name":this.name
                        });
                        this.groupId=null;
                        this.count=0;
                        this.$Message.info('删除成功');
                    }.bind(this)).catch(function (error) {
                        alert(error);
                    });
                }
            },
            /*表格中双击事件*/
            dblclick(e){
                this.signModifySet(e);
                this.modifyModal = true;
                this.data1.sort();
            },

        }
    }
</script>