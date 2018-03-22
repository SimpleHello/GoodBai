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
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
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
                let param = new Object();
                var code = this.$route.params.code;
                this.code = code;
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
                    }
                    for(var i=0;i<result.length;i++){
                        nums.push(result[i].value);    //挨个取出销量并填入销量数组
                    }
                    this.chart.setOption({        //加载数据图表
                        title: {
                            text:param.code
                        },
                        xAxis: {
                            data: dates,
                            min: 'dataMin'*0.9
                        },
                        yAxis: {
                            max: 'dataMax'*1.2
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '价格',
                            data: nums
                        }]
                    });
                    return true
                }
            }).catch(function (err) {
                    return err;
                });
            },
            change(){
                this.callDialog("123123");
            }
        }
    }
</script>