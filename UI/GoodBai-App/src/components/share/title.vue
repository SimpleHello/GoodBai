<template>
	<div class="top">
		<span class="back" @click='goBack'>返回</span>
		<h1  v-if="idx==0">
			最新推荐
		</h1>
		<h1  v-else-if="idx==12">
			{{code}} 历史推荐
		</h1>
		<h1  v-else-if="idx==77">
			{{code}} 折线图
		</h1>
		<h1  v-else-if="idx==99">
			最新增的推荐
		</h1>
		<h1  v-else>
			最近{{idx}}天汇总
		</h1>
	</div>
	<!--<ul class="tabTitle">-->
		<!--<span v-if="idx==0">-->
			<!--实时数据-->
		<!--</span>-->
		<!--<span v-else="">-->
			<!--最近{{idx}} 天数据-->
		<!--</span>-->
		<!--&lt;!&ndash;<li v-for='(item,index) of items' :class='{on : index === idx }' @click="$router.push(item.push)">{{item.name}}</li>&ndash;&gt;-->
	<!--</ul>-->
</template>
<script>
	export default {
	    data () {
	        return {
	            items:[
	            	{name:"最新推荐",	push:"/share/detailList"}
//	            	{name:"申请中",	push:"/user/borrowList/apply"},
//	            	{name:"待归还",	push:"/user/borrowList/repay"},
//	            	{name:"已结束",	push:"/user/borrowList/over"}
	            ]
	        }
	    },
		methods :{
			goBack () {
				this.$router.back();
			}
		},
	    props:['idx','code']
	}
</script>