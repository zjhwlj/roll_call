<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">教师姓名：
                <Input v-model="teacherName" placeholder="请输入..." style="width:200px"></Input>
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
        <Modal :mask-closable="false" :visible.sync="newModal" :loading = "loading" v-model="newModal" width="600" title="新建" @on-ok="newOk('teacherNew')" @on-cancel="cancel()">
            <Form ref="teacherNew" :model="teacherNew" :rules="ruleNew" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="教师姓名:" prop="teacherName">
                        <Input v-model="teacherNew.teacherName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="教工号:" prop="teacherId">
                        <Input v-model="teacherNew.teacherId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="所在学校:" prop="schoolName">
                        <Input v-model="teacherNew.schoolName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="所教课程数:" prop="courseNum">
                        <Input v-model="teacherNew.courseNum"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>

            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading = "loading" v-model="modifyModal" width="600" title="修改" @on-ok="modifyOk('teacherModify')" @on-cancel="cancel()">
            <Form ref="teacherModify" :model="teacherModify" :rules="ruleModify" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="教师姓名:" prop="teacherName">
                        <Input v-model="teacherModify.teacherName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="教工号:" prop="teacherId">
                        <Input v-model="teacherModify.teacherId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="所在学校:" prop="schoolName">
                        <Input v-model="teacherModify.schoolName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="所教课程数:" prop="courseNum">
                        <Input v-model="teacherModify.courseNum"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>

    </div>
</template>
<script>
    export default {
        data () {
            return {
                /*用于查找的登录名*/
                teacherName:null,
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
                teacher:{
                    id:null,
                    teacherName:null,
                    teacherId:null,
                    schoolName:null,
                    courseNum:null,
                },
                /*用于添加的user实体*/
                teacherNew:{
                    id:null,
                    teacherName:null,
                    teacherId:null,
                    schoolName:null,
                    courseNum:null,
                },
                /*用于修改的user实体*/
                teacherModify:{
                    id:null,
                    teacherName:null,
                    teacherId:null,
                    schoolName:null,
                    courseNum:null,
                },
                /*新建验证*/
                ruleNew:{
                    teacherName: [
                        { type:'string',required: true, message: '输入教师姓名', trigger: 'blur' }
                    ],
                    teacherId: [
                        { type:'string',required: true, message: '输入教工号', trigger: 'blur' }
                    ],
                    schoolName: [
                        { type:'string',required: true, message: '输入所在学校名称', trigger: 'blur' }
                    ],
                    courseNum: [
                        { type:'string',required: true, message: '输入教授课程数', trigger: 'blur' }
                    ]
                },
                /*修改验证*/
                ruleModify:{
                    teacherName: [
                        { type:'string',required: true, message: '输入教师姓名', trigger: 'blur' }
                    ],
                    teacherId: [
                        { type:'string',required: true, message: '输入教工号', trigger: 'blur' }
                    ],
                    schoolName: [
                        { type:'string',required: true, message: '输入所在学校名称', trigger: 'blur' }
                    ],
                    courseNum: [
                        { type:'string',required: true, message: '输入教授课程数', trigger: 'blur' }
                    ]
                },
                /*表显示字段*/
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '教师姓名',
                        key: 'teacherName'
                    },
                    {
                        title: '教工号',
                        key: 'teacherId'
                    },
                    {
                        title: '所在学校',
                        key: 'schoolName'
                    },
                    {
                        title: '教授课程数',
                        key: 'courseNum'
                    },
                ],
                // 表数据
                data1: [],

                relationList:null
            }
        },
        mounted(){
            /*页面初始化调用方法*/
            this.getTable({
                "pageInfo":this.pageInfo,
                "teacherName":this.teacherName
            });
            this.axios({
                method: 'get',
                url: '/teacher/all'
            }).then(function (response) {
                //this.data2Temp = response.data;
                this.data1 = response.data;
                //console.log(this.data1[1]);
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
            initTeacher(){
                this.teacher.id = null;
                this.teacher.teacherName = null;
                this.teacher.teacherId = null;
                this.teacher.schoolName = null;
                this.teacher.courseNum = null;

            },
            /*userNew实体初始化*/
            initTeacherNew(){
                this.teacherNew.id = null;
                this.teacherNew.teacherName = null;
                this.teacherNew.teacherId = null;
                this.teacherNew.schoolName = null;
                this.teacherNew.courseNum = null;

            },
            /*userModify实体初始化*/
            initTeacherModify(){
                this.teacherModify.id = null;
                this.teacherModify.teacherName = null;
                this.teacherModify.teacherId = null;
                this.teacherModify.schoolName = null;
                this.teacherModify.courseNum = null;
            },
            /*userNew设置*/
            teacherSet(e){
                this.teacher.id = e.id;
                this.teacher.teacherName = e.teacherName;
                this.teacher.teacherId = e.teacherId;
                this.teacher.schoolName = e.schoolName;
                this.teacher.courseNum = e.courseNum;

            },
            /*userNew设置*/
            teacherNewSet(e){
                this.teacherNew.id = e.id;
                this.teacherNew.teacherName = e.teacherName;
                this.teacherNew.teacherId = e.teacherId;
                this.teacherNew.schoolName = e.schoolName;
                this.teacherNew.courseNum = e.courseNum;
            },
            /*userModify设置*/
            teacherModifySet(e){
                this.teacherModify.id = e.id;
                this.teacherModify.teacherName = e.teacherName;
                this.teacherModify.teacherId = e.teacherId;
                this.teacherModify.schoolName = e.schoolName;
                this.teacherModify.courseNum = e.courseNum;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/teacher',
                    params: {
                        'page':e.pageInfo.page,
                        'pageSize':e.pageInfo.pageSize,
                        'teacherName':e.teacherName
                    }
                }).then(function (response) {
                    this.data1=response.data.data;
                    this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
               // console.log(e.teacherName);
            },
            /*搜索按钮点击事件*/
            search(){
                this.initPageInfo();
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "teacherName":this.teacherName
                });
                //console.log(this.teacherName);
            },
            /*分页点击事件*/
            pageSearch(e){
                this.pageInfo.page = e-1;
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "teacherName":this.teacherName
                });
            },
            /*新建点击触发事件*/
            openNewModal(){
                this.newModal = true;
                this.initTeacherNew();
                this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk (teacherNew) {
                this.$refs[teacherNew].validate((valid) => {
                    if (valid) {
                        //if(this.teacherNew.password == this.teacherNew.passwordAgain){
                        this.initTeacher();
                        this.teacherSet(this.teacherNew);
                       // console.log(this.teacherNew);
                        this.axios({
                            method: 'post',
                            url: '/teacher/insert',
                            data: this.teacher

                        }).then(function (response) {

                            this.initTeacherNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "teacherName":this.teacherName
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
            modifyOk (teacherModify) {
                this.$refs[teacherModify].validate((valid) => {
                    if (valid) {
                        this.initTeacher();
                        this.teacherSet(this.teacherModify);
                       // console.log(this.teacher.id);
                        this.axios({
                            method: 'put',
                            url:'/teacher/'+this.teacher.id,
                            data: this.teacher
                        }).then(function (response) {
                            this.initTeacherNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "teacherName":this.teacherName
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });

                        this.modifyModal = false;
                        //console.log(this.teacher);
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
                    this.teacherModifySet(e['0']);
                }
                //console.log( this.teacherModify);
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e){
                this.groupId=[];
                this.count=e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].id);
                }
                //
                // console.log(this.groupId);
            },
            /*删除table中选中的数据*/
            del(){
                if(this.groupId!=null && this.groupId!=""){
                    //console.log(this.groupId)
                    this.axios({
                        method: 'delete',
                        url: '/teacher/delete',
                        data: this.groupId
                    }).then(function (response) {
                        this.getTable({
                            "pageInfo":this.pageInfo,
                            "teacherName":this.teacherName
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
                this.teacherModifySet(e);
                this.modifyModal = true;
                this.data1.sort();
            },

        }
    }
</script>