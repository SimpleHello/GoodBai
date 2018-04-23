<template>
    <div>
        <shareTitle :idx='77' :code="code"></shareTitle>
        <div class="charts" id="id" :style="{width:width, height:height}" style="top: 20px" ref="myEchart">
        </div>
        <ul class="borrowList">
            <li>
                <p class="w60P col6">名称:<span>{{name}}</span></p>
                <p class="col6">代码:<span>{{code}}</span></p>
            </li>
        </ul>
        <shareInfoNoList v-if='hasBorrow' :items='detailItem'></shareInfoNoList>
        <pageError v-if='!hasBorrow' :msg='borrowMsg' :class='borrowCls'></pageError>
    </div>
</template>
<script type="text/ecmascript-6">
    import echarts from 'echarts';
    import ajax from '../../config/ajax.js';
    export default {
        data() {
            return {
                chart: null,
                name:'',
                code:'',
                hasBorrow:true,	//开关
                borrowMsg:'当前没有任何数据',
                borrowCls:'error-txt',
                detailItem:[],
                width: document.documentElement.clientWidth + 'px',
                height: document.documentElement.clientHeight * 0.5 + 'px'
            }
        },
        mounted() {
            this.initChart();
        },
        beforeDestroy() {
            if (!this.chart) {
                return
            }
            this.chart.dispose();
            this.chart = null;
        },
        methods: {
            initChart() {
                var _this= this;
                this.chart = echarts.init(this.$refs.myEchart);
                // 把配置和数据放这里
                var option = {
                    title: {
                        text: ''
                    },
                    tooltip: {},
                    legend: {
                        data: ['价格']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            magicType: {show: true, type: ['line', 'bar']}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: []
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '价格',
                            type: 'line',
                            data: [],
//                            markPoint: {
//                                data: []
//                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        }
                    ]
                };
                this.chart.setOption(option);
                var dates=[];    //日期数组（实际用来盛放X轴坐标值）
                var nums=[];    //价格数组（实际用来盛放Y坐标值）
                var mask=[];    //标记数组
                let param = new Object();
                var code = this.$route.params.code;
                this.code = code;
                var datalist = [];
                this.name = this.$route.params.name;
                param.code=code;
                ajax.post('/share/getShareHis.do',param).then(data => {
                    if(data.error<0){
                    this.callDialog('获取列表失败');
                    this.hasBorrow = false;
                    return false;
                }else{
                    var result =  data.rows;
                    for(var i=0;i<result.length;i++){;
                        dates.push(result[i].day);    //挨个取出类别并填入类别数组
                        nums.push(result[i].value);    //挨个取出销量并填入销量数组
                        let id = result[i].id;
                        var maskNode = new Object();
                        if(id>0){
                            maskNode.name='当日推荐';
                            maskNode.value=id;
                            maskNode.xAxis=result[i].day+'';
                            maskNode.yAxis=result[i].value;
                            maskNode.code = code;
                            maskNode.day = result[i].day;
                            mask.push(maskNode)
                        }
                    }
                    this.chart.setOption({        //加载数据图表
                        title: {
                            text:param.code
                        },
                        xAxis: {
                            data: dates,
                            min: 'dataMin'*0.7
                        },
                        yAxis: {
                            max: 'dataMax'*1.4
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            markPoint: {
                                data:mask
                            },
                            name: '推荐次数',
                            data: nums
                        }]
                    });
                    return true
                }
                }).catch(function (err) {
                    return err;
                });
                this.chart.on('click', function (params) {
                    _this.clickNode(params);
                });

            },
            clickNode(params){
                ajax.post('/share/getDetail.do',params.data).then(data => {
                    if(data.error<0){
                        this.hasBorrow = false;
                        return false;
                    }else{
                        this.hasBorrow = true;
                        this.detailItem =  data.rows;
                        return true
                    }
                }).catch(function (err) {
                    return err;
                });
            }
        }
    }
</script>