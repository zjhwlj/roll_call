<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">学校名称：
                <Input v-model="schoolName" placeholder="请输入..." style="width:200px"></Input>
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
        <Modal :mask-closable="false" :visible.sync="newModal" :loading = "loading" v-model="newModal" width="600" title="新建" @on-ok="newOk('schoolNew')" @on-cancel="cancel()">
            <Form ref="schoolNew" :model="schoolNew" :rules="ruleNew" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="学校名称:" prop="schoolName">
                        <Input v-model="schoolNew.schoolName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上午上课时间:" prop="amClasstime">
                        <Input v-model="schoolNew.amClasstime" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="上午下课时间:" prop="amAfterclasstime">
                        <Input v-model="schoolNew.amAfterclasstime" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="下午上课时间:" prop="pmClasstime">
                        <Input v-model="schoolNew.pmClasstime"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="下午下课时间:" prop="pmAfterclasstime">
                        <Input v-model="schoolNew.pmAfterclasstime" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading = "loading" v-model="modifyModal" width="600" title="修改" @on-ok="modifyOk('schoolModify')" @on-cancel="cancel()">
            <Form ref="schoolModify" :model="schoolModify" :rules="ruleModify" :label-width="80" >
                <Row>
                    <Col span="12">
                    <Form-item label="学校名称:" prop="schoolName">
                        <Input v-model="schoolModify.schoolName" style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="上课时间:" prop="amClasstime">
                        <Input v-model="schoolModify.amClasstime" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="上午下课时间:" prop="amAfterclasstime">
                        <Input v-model="schoolModify.amAfterclasstime"  style="width: 204px"/>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="下午上课时间:" prop="pmClasstime">
                        <Input v-model="schoolModify.pmClasstime" style="width: 204px"/>
                    </Form-item>
                    </Col>
                </Row>

                <Row>
                    <Col span="12">
                    <Form-item label="下午下课时间:" prop="pmAfterclasstime">
                        <Input v-model="schoolModify.pmAfterclasstime" style="width: 204px"/>
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
                schoolName:null,
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
                school:{
                    schoolId:null,
                    schoolName:null,
                    amClasstime:null,
                    amAfterclasstime:null,
                    pmClasstime:null,
                    pmAfterclasstime:null
                },
                /*用于添加的user实体*/
                schoolNew:{
                    schoolId:null,
                    schoolName:null,
                    amClasstime:null,
                    amAfterclasstime:null,
                    pmClasstime:null,
                    pmAfterclasstime:null
                },
                /*用于修改的user实体*/
                schoolModify:{
                    schoolId:null,
                    schoolName:null,
                    amClasstime:null,
                    amAfterclasstime:null,
                    pmClasstime:null,
                    pmAfterclasstime:null
                },
                /*新建验证*/
                ruleNew:{
                    schoolName: [
                        { type:'string',required: true, message: '输入学校名称', trigger: 'blur' }
                    ],
                    amClasstime: [
                        { type:'string',required: true, message: '输入上午上课时间', trigger: 'blur' }
                    ],
                    amAfterclasstime: [
                        { type:'string',required: true, message: '输入上午下课时间', trigger: 'blur' }
                    ],
                    pmClasstime: [
                        { type:'string',required: true, message: '输入下午上课时间', trigger: 'blur' }
                    ],
                    pmAfterclasstime: [
                        { type:'string',required: true, message: '输入下午下课时间', trigger: 'blur' }
                    ]
                },
                /*修改验证*/
                ruleModify:{
                    schoolName: [
                        { type:'string',required: true, message: '输入学校名称', trigger: 'blur' }
                    ],
                    amClasstime: [
                        { type:'string',required: true, message: '输入上午上课时间', trigger: 'blur' }
                    ],
                    amAfterclasstime: [
                        { type:'string',required: true, message: '输入上午下课时间', trigger: 'blur' }
                    ],
                    pmClasstime: [
                        { type:'string',required: true, message: '输入下午上课时间', trigger: 'blur' }
                    ],
                    pmAfterclasstime: [
                        { type:'string',required: true, message: '输入下午下课时间', trigger: 'blur' }
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
                        title: '学校名称',
                        key: 'schoolName'
                    },
                    {
                        title: '上午上课时间',
                        key: 'amClasstime'
                    },
                    {
                        title: '上午下课时间',
                        key: 'amAfterclasstime'
                    },
                    {
                        title: '下午上课时间',
                        key: 'pmClasstime'
                    },
                    {
                        title: '下午下课时间',
                        key: 'pmAfterclasstime'
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
                "schoolName":this.schoolName
            });
            this.axios({
                method: 'get',
                url: '/schools/all'
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
            initSchool(){
                this.school.schoolId = null;
                this.school.schoolName = null;
                this.school.amClasstime = null;
                this.school.amAfterclasstime = null;
                this.school.pmClasstime = null;
                this.school.pmAfterclasstime = null;
            },
            /*userNew实体初始化*/
            initSchoolNew(){
                this.schoolNew.schoolId = null;
                this.schoolNew.schoolName = null;
                this.schoolNew.amClasstime = null;
                this.schoolNew.amAfterclasstime = null;
                this.schoolNew.pmClasstime = null;
                this.schoolNew.pmAfterclasstime = null;
            },
            /*userModify实体初始化*/
            initSchoolModify(){
                this.schoolModify.schoolId = null;
                this.schoolModify.schoolName = null;
                this.schoolModify.amClasstime = null;
                this.schoolModify.amAfterclasstime = null;
                this.schoolModify.pmClasstime = null;
                this.schoolModify.pmAfterclasstime = null;
            },
            /*userNew设置*/
            schoolSet(e){
                this.school.schoolId = e.schoolId;
                this.school.schoolName = e.schoolName;
                this.school.amClasstime = e.amClasstime;
                this.school.amAfterclasstime = e.amAfterclasstime;
                this.school.pmClasstime = e.pmClasstime;
                this.school.pmAfterclasstime = e.pmAfterclasstime;
            },
            /*userNew设置*/
            schoolNewSet(e){
                this.schoolNew.schoolId = e.schoolId;
                this.schoolNew.schoolName = e.schoolName;
                this.schoolNew.amClasstime = e.amClasstime;
                this.schoolNew.amAfterclasstime = e.amAfterclasstime;
                this.schoolNew.pmClasstime = e.pmClasstime;
                this.schoolNew.pmAfterclasstime = e.pmAfterclasstime;
            },
            /*userModify设置*/
            schoolModifySet(e){
                this.schoolModify.schoolId = e.schoolId;
                this.schoolModify.schoolName = e.schoolName;
                this.schoolModify.amClasstime = e.amClasstime;
                this.schoolModify.amAfterclasstime = e.amAfterclasstime;
                this.schoolModify.pmClasstime = e.pmClasstime;
                this.schoolModify.pmAfterclasstime = e.pmAfterclasstime;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/schools',
                    params: {
                        'page':e.pageInfo.page,
                        'pageSize':e.pageInfo.pageSize,
                        'schoolName':e.schoolName
                    }
                }).then(function (response) {
                    this.data1=response.data.data;
                    this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
                //console.log(e.schoolName);
            },
            /*搜索按钮点击事件*/
            search(){
                this.initPageInfo();
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "schoolName":this.schoolName
                });
                //console.log(this.schoolName);
            },
            /*分页点击事件*/
            pageSearch(e){
                this.pageInfo.page = e-1;
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "schoolName":this.schoolName
                });
            },
            /*新建点击触发事件*/
            openNewModal(){
                this.newModal = true;
                this.initSchoolNew();
                this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk (schoolNew) {
                this.$refs[schoolNew].validate((valid) => {
                    if (valid) {
                        //if(this.schoolNew.password == this.schoolNew.passwordAgain){
                        this.initSchool();
                        this.schoolSet(this.schoolNew);
                        //console.log(this.schoolNew);
                        this.axios({
                            method: 'post',
                            url: '/schools/insert',
                            data: this.school

                        }).then(function (response) {

                            this.initSchoolNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "schoolName":this.schoolName
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
            modifyOk (schoolModify) {
                this.$refs[schoolModify].validate((valid) => {
                    if (valid) {
                        this.initSchool();
                        this.schoolSet(this.schoolModify);
                       // console.log(this.school.schoolId);
                        this.axios({
                            method: 'put',
                            url:'/schools/'+this.school.schoolId,
                            data: this.school
                        }).then(function (response) {
                            this.initSchoolNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "schoolName":this.schoolName
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });

                        this.modifyModal = false;
                        //console.log(this.school);
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
                    this.schoolModifySet(e['0']);
                }
                //console.log( this.schoolModify);
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e){
                this.groupId=[];
                this.count=e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].schoolId);
                }
               // console.log(this.groupId);
            },
            /*删除table中选中的数据*/
            del(){
                if(this.groupId!=null && this.groupId!=""){
                    //console.log(this.groupId)
                    this.axios({
                        method: 'delete',
                        url: '/schools/delete',
                        data: this.groupId
                    }).then(function (response) {
                        this.getTable({
                            "pageInfo":this.pageInfo,
                            "schoolName":this.schoolName
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
                this.schoolModifySet(e);
                this.modifyModal = true;
                this.data1.sort();
            },

        }
    }
</script>