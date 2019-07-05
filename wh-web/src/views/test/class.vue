<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">课程名称：
                <Input v-model="courseName" placeholder="请输入..." style="width:200px"></Input>
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
        <Modal :mask-closable="false" :visible.sync="newModal" :loading = "loading" v-model="newModal" width="600" title="新建" @on-ok="newOk('courseNew')" @on-cancel="cancel()">
            <Form ref="courseNew" :model="courseNew" :rules="ruleNew" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="课程名称:" prop="courseName">
                        <Input v-model="courseNew.courseName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="课程ID:" prop="courseId">
                        <Input v-model="courseNew.courseId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="授课老师:" prop="teacherName">
                        <Input v-model="courseNew.teacherName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上课时间:" prop="classWeek">
                        <Input v-model="courseNew.classWeek"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="第一节上课时间:" prop="firstClasstime">
                        <Input v-model="courseNew.firstClasstime" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上课节数:" prop="classNum">
                        <Input v-model="courseNew.classNum"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="学分:" prop="credit">
                        <Input v-model="courseNew.credit" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上课地点:" prop="classroom">
                        <Input v-model="courseNew.classroom" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading = "loading" v-model="modifyModal" width="600" title="修改" @on-ok="modifyOk('courseModify')" @on-cancel="cancel()">
            <Form ref="courseModify" :model="courseModify" :rules="ruleModify" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="课程名称:" prop="courseName">
                        <Input v-model="courseModify.courseName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="课程ID:" prop="courseId">
                        <Input v-model="courseModify.courseId" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="授课老师:" prop="teacherName">
                        <Input v-model="courseModify.teacherName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上课时间:" prop="classWeek">
                        <Input v-model="courseModify.classWeek"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="第一节上课时间:" prop="firstClasstime">
                        <Input v-model="courseModify.firstClasstime" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上课节数:" prop="classNum">
                        <Input v-model="courseModify.classNum"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="学分:" prop="credit">
                        <Input v-model="courseModify.credit" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上课地点:" prop="classroom">
                        <Input v-model="courseModify.classroom" style="width: 204px"/>
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
                courseName:null,
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
                course:{
                    id:null,
                    courseName:null,
                    courseId:null,
                    teacherName:null,
                    classWeek:null,
                    firstClasstime:null,
                    classNum:null,
                    credit:null,
                    classroom:null
                },
                /*用于添加的user实体*/
                courseNew:{
                    id:null,
                    courseName:null,
                    courseId:null,
                    teacherName:null,
                    classWeek:null,
                    firstClasstime:null,
                    classNum:null,
                    credit:null,
                    classroom:null
                },
                /*用于修改的user实体*/
                courseModify:{
                    id:null,
                    courseName:null,
                    courseId:null,
                    teacherName:null,
                    classWeek:null,
                    firstClasstime:null,
                    classNum:null,
                    credit:null,
                    classroom:null
                },
                /*新建验证*/
                ruleNew:{
                    courseId: [
                        { type:'string',required: true, message: '课程ID', trigger: 'blur' }
                    ],
                    courseName: [
                        { type:'string',required: true, message: '课程名称', trigger: 'blur' }
                    ],
                    teacherName: [
                        { type:'string',required: true, message: '授课老师', trigger: 'blur' }
                    ],
                    classWeek: [
                        { type:'string',required: true, message: '上课时间', trigger: 'blur' }
                    ],
                    firstClasstime: [
                        { type:'string',required: true, message: '第一节上课时间', trigger: 'blur' }
                    ],
                    classNum: [
                        { type:'string',required: true, message: '上课节数', trigger: 'blur' }
                    ],
                    credit: [
                        { type:'string',required: true, message: '学分', trigger: 'blur' }
                    ],
                    classroom: [
                        { type:'string',required: true, message: '上课地点', trigger: 'blur' }
                    ]
                },
                /*修改验证*/
                ruleModify:{
                    courseId: [
                        { type:'string',required: true, message: '课程ID', trigger: 'blur' }
                    ],
                    courseName: [
                        { type:'string',required: true, message: '课程名称', trigger: 'blur' }
                    ],
                    teacherName: [
                        { type:'string',required: true, message: '授课老师', trigger: 'blur' }
                    ],
                    classWeek: [
                        { type:'string',required: true, message: '上课时间', trigger: 'blur' }
                    ],
                    firstClasstime: [
                        { type:'string',required: true, message: '第一节上课时间', trigger: 'blur' }
                    ],
                    classNum: [
                        { type:'string',required: true, message: '上课节数', trigger: 'blur' }
                    ],
                    credit: [
                        { type:'string',required: true, message: '学分', trigger: 'blur' }
                    ],
                    classroom: [
                        { type:'string',required: true, message: '上课地点', trigger: 'blur' }
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
                        title: '课程ID',
                        key: 'courseId'
                    },
                    {
                        title: '课程名称',
                        key: 'courseName'
                    },
                    {
                        title: '授课老师',
                        key: 'teacherName'
                    },
                    {
                        title: '上课时间',
                        key: 'classWeek'
                    },
                    {
                        title: '第一节上课时间',
                        key: 'firstClasstime'
                    },
                    {
                        title: '上课节数',
                        key: 'classNum'
                    },
                    {
                        title: '学分',
                        key: 'credit'
                    },
                    {
                        title: '上课教室',
                        key: 'classroom'
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
                "courseName":this.courseName
            });
            this.axios({
                method: 'get',
                url: '/course/all'
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
            initCourse(){
                this.course.id = null;
                this.course.courseName = null;
                this.course.courseId = null;
                this.course.teacherName = null;
                this.course.classWeek = null;
                this.course.firstClasstime = null;
                this.course.classNum = null;
                this.course.credit = null;
                this.course.classroom = null;
            },
            /*userNew实体初始化*/
            initCourseNew(){
                this.courseNew.id = null;
                this.courseNew.courseName = null;
                this.courseNew.courseId = null;
                this.courseNew.teacherName = null;
                this.courseNew.classWeek = null;
                this.courseNew.firstClasstime = null;
                this.courseNew.classNum = null;
                this.courseNew.credit = null;
                this.courseNew.classroom = null;
            },
            /*userModify实体初始化*/
            initCourseModify(){
                this.courseModify.id = null;
                this.courseModify.courseName = null;
                this.courseModify.courseId = null;
                this.courseModify.teacherName = null;
                this.courseModify.classWeek = null;
                this.courseModify.firstClasstime = null;
                this.courseModify.classNum = null;
                this.courseModify.credit = null;
                this.courseModify.classroom = null;
            },
            /*userNew设置*/
            courseSet(e){
                this.course.id = e.id,
                this.course.courseName = e.courseName;
                this.course.courseId = e.courseId;
                this.course.teacherName = e.teacherName;
                this.course.classWeek = e.classWeek;
                this.course.firstClasstime = e.firstClasstime;
                this.course.classNum = e.classNum;
                this.course.credit = e.credit;
                this.course.classroom = e.classroom;
            },
            /*userNew设置*/
            courseNewSet(e){
                this.courseNew.id = e.id;
                this.courseNew.courseName = e.courseName;
                this.courseNew.courseId = e.courseId;
                this.courseNew.teacherName = e.teacherName;
                this.courseNew.classWeek = e.classWeek;
                this.courseNew.firstClasstime = e.firstClasstime;
                this.courseNew.classNum = e.classNum;
                this.courseNew.credit = e.credit;
                this.courseNew.classroom = e.classroom;
            },
            /*userModify设置*/
            courseModifySet(e){
                this.courseModify.id = e.id;
                this.courseModify.courseName = e.courseName;
                this.courseModify.courseId = e.courseId;
                this.courseModify.teacherName = e.teacherName;
                this.courseModify.classWeek = e.classWeek;
                this.courseModify.firstClasstime = e.firstClasstime;
                this.courseModify.classNum = e.classNum;
                this.courseModify.credit = e.credit;
                this.courseModify.classroom = e.classroom;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/course',
                    params: {
                        'page':e.pageInfo.page,
                        'pageSize':e.pageInfo.pageSize,
                        'courseName':e.courseName
                    }
                }).then(function (response) {
                    this.data1=response.data.data;
                    this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
                console.log(e.courseName);
            },
            /*搜索按钮点击事件*/
            search(){
                this.initPageInfo();
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "courseName":this.courseName
                });
                console.log(this.courseName);
            },
            /*分页点击事件*/
            pageSearch(e){
                this.pageInfo.page = e-1;
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "courseName":this.courseName
                });
            },
            /*新建点击触发事件*/
            openNewModal(){
                this.newModal = true;
                this.initCourseNew();
                this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk (courseNew) {
                this.$refs[courseNew].validate((valid) => {
                    if (valid) {
                        //if(this.courseNew.password == this.courseNew.passwordAgain){
                        this.initCourse();
                        this.courseSet(this.courseNew);
                        console.log(this.courseNew);
                        this.axios({
                            method: 'post',
                            url: '/course/insert',
                            data: this.course

                        }).then(function (response) {

                            this.initCourseNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "courseName":this.courseName
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
            modifyOk (courseModify) {
                this.$refs[courseModify].validate((valid) => {
                    if (valid) {
                        this.initCourse();
                        this.courseSet(this.courseModify);
                        console.log(this.course.id);
                        this.axios({
                            method: 'put',
                            url:'/course/'+this.course.id,
                            data: this.course
                        }).then(function (response) {
                            this.initCourseNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "courseName":this.courseName
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });

                        this.modifyModal = false;
                        //console.log(this.course);
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
                    this.courseModifySet(e['0']);
                }
                console.log( this.courseModify);
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e){
                this.groupId=[];
                this.count=e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].id);
                }
                console.log(this.groupId);
            },
            /*删除table中选中的数据*/
            del(){
                if(this.groupId!=null && this.groupId!=""){
                    //console.log(this.groupId)
                    this.axios({
                        method: 'delete',
                        url: '/course/delete',
                        data: this.groupId
                    }).then(function (response) {
                        this.getTable({
                            "pageInfo":this.pageInfo,
                            "courseName":this.courseName
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
                this.courseModifySet(e);
                this.modifyModal = true;
                this.data1.sort();
            },

        }
    }
</script>