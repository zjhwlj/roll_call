<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">学生姓名：
                <Input v-model="studentName" placeholder="请输入..." style="width:200px"></Input>
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
        <Modal :mask-closable="false" :visible.sync="newModal" :loading = "loading" v-model="newModal" width="600" title="新建" @on-ok="newOk('studentNew')" @on-cancel="cancel()">
            <Form ref="studentNew" :model="studentNew" :rules="ruleNew" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="学生姓名:" prop="studentName">
                        <Input v-model="studentNew.studentName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="学生学号:" prop="studentId">
                        <Input v-model="studentNew.studentId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="所在学校:" prop="schoolName">
                        <Input v-model="studentNew.schoolName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="专业名称:" prop="major">
                        <Input v-model="studentNew.major"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>

            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading = "loading" v-model="modifyModal" width="600" title="修改" @on-ok="modifyOk('studentModify')" @on-cancel="cancel()">
            <Form ref="studentModify" :model="studentModify" :rules="ruleModify" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="学生姓名:" prop="studentName">
                        <Input v-model="studentModify.schoolName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="学生学号:" prop="studentId">
                        <Input v-model="studentModify.studentId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="所在学校:" prop="schoolName">
                        <Input v-model="studentModify.schoolName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="专业名称:" prop="major">
                        <Input v-model="studentModify.major"  style="width: 204px"/>
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
                studentName:null,
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
                student:{
                    id:null,
                    studentName:null,
                    studentId:null,
                    schoolName:null,
                    major:null,
                },
                /*用于添加的user实体*/
                studentNew:{
                    id:null,
                    studentName:null,
                    studentId:null,
                    schoolName:null,
                    major:null,
                },
                /*用于修改的user实体*/
                studentModify:{
                    id:null,
                    studentName:null,
                    studentId:null,
                    schoolName:null,
                    major:null,
                },
                /*新建验证*/
                ruleNew:{
                    studentlName: [
                        { type:'string',required: true, message: '输入学生姓名', trigger: 'blur' }
                    ],
                    studentId: [
                        { type:'string',required: true, message: '输入学生学号', trigger: 'blur' }
                    ],
                    schoolName: [
                        { type:'string',required: true, message: '输入所在学校名称', trigger: 'blur' }
                    ],
                    major: [
                        { type:'string',required: true, message: '输入专业名称', trigger: 'blur' }
                    ]
                },
                /*修改验证*/
                ruleModify:{
                    studentlName: [
                        { type:'string',required: true, message: '输入学生姓名', trigger: 'blur' }
                    ],
                    studentId: [
                        { type:'string',required: true, message: '输入学生学号', trigger: 'blur' }
                    ],
                    schoolName: [
                        { type:'string',required: true, message: '输入所在学校名称', trigger: 'blur' }
                    ],
                    major: [
                        { type:'string',required: true, message: '输入专业名称', trigger: 'blur' }
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
                        title: '学生姓名',
                        key: 'studentName'
                    },
                    {
                        title: '学生学号',
                        key: 'studentId'
                    },
                    {
                        title: '所在学校',
                        key: 'schoolName'
                    },
                    {
                        title: '专业名称',
                        key: 'major'
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
                "studentName":this.studentName
            });
            this.axios({
                method: 'get',
                url: '/student/all'
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
            initStudent(){
                this.student.id = null;
                this.student.studentName = null;
                this.student.studentId = null;
                this.student.schoolName = null;
                this.student.major = null;

            },
            /*userNew实体初始化*/
            initStudentNew(){
                this.studentNew.id = null;
                this.studentNew.studentName = null;
                this.studentNew.studentId = null;
                this.studentNew.schoolName = null;
                this.studentNew.major = null;

            },
            /*userModify实体初始化*/
            initStudentModify(){
                this.studentModify.id = null;
                this.studentModify.studentName = null;
                this.studentModify.studentId = null;
                this.studentModify.schoolName = null;
                this.studentModify.major = null;

            },
            /*userNew设置*/
            studentSet(e){
                this.student.id = e.id;
                this.student.studentName = e.studentName;
                this.student.studentId = e.studentId;
                this.student.schoolName = e.schoolName;
                this.student.major = e.major;

            },
            /*userNew设置*/
            studentNewSet(e){
                this.studentNew.id = e.id;
                this.studentNew.studentName = e.studentName;
                this.studentNew.studentId = e.studentId;
                this.studentNew.schoolName = e.schoolName;
                this.studentNew.major = e.major;
            },
            /*userModify设置*/
            studentModifySet(e){
                this.studentModify.id = e.id;
                this.studentModify.studentName = e.studentName;
                this.studentModify.studentId = e.studentId;
                this.studentModify.schoolName = e.schoolName;
                this.studentModify.major = e.major;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/student',
                    params: {
                        'page':e.pageInfo.page,
                        'pageSize':e.pageInfo.pageSize,
                        'studentName':e.studentName
                    }
                }).then(function (response) {
                    this.data1=response.data.data;
                    this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
                //console.log(e.studentName);
            },
            /*搜索按钮点击事件*/
            search(){
                this.initPageInfo();
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "studentName":this.studentName
                });
                //console.log(this.studentName);
            },
            /*分页点击事件*/
            pageSearch(e){
                this.pageInfo.page = e-1;
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "studentName":this.studentName
                });
            },
            /*新建点击触发事件*/
            openNewModal(){
                this.newModal = true;
                this.initStudentNew();
                this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk (studentNew) {
                this.$refs[studentNew].validate((valid) => {
                    if (valid) {
                        //if(this.studentNew.password == this.studentNew.passwordAgain){
                        this.initStudent();
                        this.studentSet(this.studentNew);
                       // console.log(this.studentNew);
                        this.axios({
                            method: 'post',
                            url: '/student/insert',
                            data: this.student

                        }).then(function (response) {

                            this.initStudentNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "studentName":this.studentName
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
            modifyOk (studentModify) {
                this.$refs[studentModify].validate((valid) => {
                    if (valid) {
                        this.initStudent();
                        this.studentSet(this.studentModify);
                        //console.log(this.student.id);
                        this.axios({
                            method: 'put',
                            url:'/student/'+this.student.id,
                            data: this.student
                        }).then(function (response) {
                            this.initStudentNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "studentName":this.studentName
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });

                        this.modifyModal = false;
                        //console.log(this.student);
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
                    this.studentModifySet(e['0']);
                }
                //console.log( this.studentModify);
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
                        url: '/student/delete',
                        data: this.groupId
                    }).then(function (response) {
                        this.getTable({
                            "pageInfo":this.pageInfo,
                            "studentName":this.studentName
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
                this.studentModifySet(e);
                this.modifyModal = true;
                this.data1.sort();
            },

        }
    }
</script>